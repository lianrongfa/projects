package com.lianrf.tierexp.common;

import com.lianrf.tierexp.exception.TierParseException;
import com.lianrf.tierexp.exception.TierRunException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * OperatorNumber
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/14 10:27 上午
 */
public abstract class OperatorNumber {
    public static final byte NUMBER_TYPE_BYTE = 1;
    public static final byte NUMBER_TYPE_SHORT = 2;
    public static final byte NUMBER_TYPE_INT = 3;
    public static final byte NUMBER_TYPE_LONG = 4;
    public static final byte NUMBER_TYPE_FLOAT = 5;
    public static final byte NUMBER_TYPE_DOUBLE = 6;
    public static final byte NUMBER_TYPE_BIG_DECIMAL = 7;

    public static final String TIP_ERROR_DATA_TYPE = "错误的数值格式";

    public static final BigDecimal BIG_DECIMAL_LONG_MAX = new BigDecimal(Long.MAX_VALUE);
    public static final BigDecimal BIG_DECIMAL_LONG_MIN = new BigDecimal(Long.MIN_VALUE);
    public static final BigDecimal BIG_DECIMAL_INTEGER_MAX = new BigDecimal(Integer.MAX_VALUE);
    public static final BigDecimal BIG_DECIMAL_INTEGER_MIN = new BigDecimal(Integer.MIN_VALUE);

    private OperatorNumber() {
    }

    public static void main(String[] args) {
        BigDecimal number = new BigDecimal("10").divide(new BigDecimal("3"), 10, RoundingMode.HALF_UP);

        System.out.println(number);
    }

    /**
     * 解析 文本->BigDecimal数值
     *
     * @param text 数值文本
     * @return BigDecimal数值
     */
    public static Number parseBigDecimal(String text) {

        if (text.charAt(text.length() - 1) == '%') {
            text = text.substring(0, text.length() - 1);
            try {
                BigDecimal decimal = new BigDecimal(text);
                return decimal.divide(BigDecimal.valueOf(100));
            } catch (NumberFormatException e) {
                throw new TierParseException(TIP_ERROR_DATA_TYPE, e);
            }
        } else {
            try {
                return new BigDecimal(text);
            } catch (NumberFormatException e) {
                throw new TierParseException(TIP_ERROR_DATA_TYPE, e);
            }
        }
    }

    /**
     * 解析 文本->数值
     *
     * @param text 数值文本
     * @return number
     */
    public static Number parse(String text) {
        if (text.charAt(text.length() - 1) == '%') {
            return parseNumberPercent(text);
        }
        return parseNumber(text);
    }

    /**
     * 解析 文本->数值
     *
     * @param text 数值文本
     * @return number
     */
    public static Number parseNumber(String text) {
        NumberFormat instance = NumberFormat.getInstance();
        try {
            return instance.parse(text);
        } catch (ParseException e) {
            throw new TierParseException("错误的数值格式", e);
        }
    }

    /**
     * 解析 文本%->数值
     *
     * @param text 数值文本
     * @return number
     */
    public static Number parseNumberPercent(String text) {
        NumberFormat instance = NumberFormat.getPercentInstance();
        try {
            return instance.parse(text);
        } catch (ParseException e) {
            throw new TierParseException("错误的数值格式", e);
        }
    }

    public static Object add(Object op1, Object op2, boolean isPrecise) {
        if (op1 == null) {
            op1 = "null";
        }
        if (op2 == null) {
            op2 = "null";
        }
        if (op1 instanceof String || op2 instanceof String) {
            return op1.toString() + op2.toString();
        }
        if (isPrecise) {
            return PreciseNumberOperator.addPrecise((Number) op1, (Number) op2);
        } else {
            return NormalNumberOperator.addNormal((Number) op1, (Number) op2);
        }
    }

    public static Number subtract(Object op1, Object op2, boolean isPrecise) {
        if (isPrecise) {
            return PreciseNumberOperator.subtractPrecise((Number) op1, (Number) op2);
        } else {
            return NormalNumberOperator.subtractNormal((Number) op1, (Number) op2);
        }
    }

    public static Number multiply(Object op1, Object op2, boolean isPrecise) {
        if (isPrecise) {
            return PreciseNumberOperator.multiplyPrecise((Number) op1, (Number) op2);
        } else {
            return NormalNumberOperator.multiplyNormal((Number) op1, (Number) op2);
        }
    }

    public static Number divide(Object op1, Object op2, boolean isPrecise) {
        if (isPrecise) {
            return PreciseNumberOperator.dividePrecise((Number) op1, (Number) op2);
        } else {
            return NormalNumberOperator.divideNormal((Number) op1, (Number) op2);
        }
    }

    /**
     * 获取数据类型精度顺序
     *
     * @param aClass c
     * @return mark
     */
    public static int getSeq(Class<?> aClass) {
        if (aClass == Byte.class || aClass == byte.class) return NUMBER_TYPE_BYTE;
        if (aClass == Short.class || aClass == short.class) return NUMBER_TYPE_SHORT;
        if (aClass == Integer.class || aClass == int.class) return NUMBER_TYPE_INT;
        if (aClass == Long.class || aClass == long.class) return NUMBER_TYPE_LONG;
        if (aClass == Float.class || aClass == float.class) return NUMBER_TYPE_FLOAT;
        if (aClass == Double.class || aClass == double.class) return NUMBER_TYPE_DOUBLE;
        if (aClass == BigDecimal.class) return NUMBER_TYPE_BIG_DECIMAL;
        throw new TierRunException("不能处理的数据类型：" + aClass.getName());
    }

    public static class PreciseNumberOperator {

        public static final int SCALE_SIZE = 10;

        private PreciseNumberOperator() {
        }

        /**
         * 高精度 +
         *
         * @param op1 op1
         * @param op2 op2
         * @return result
         */
        public static Number addPrecise(Number op1, Number op2) {
            BigDecimal left = numberToB(op1);
            BigDecimal right = numberToB(op2);
            return toNumber(left.add(right));
        }

        /**
         * 高精度 -
         *
         * @param op1 op1
         * @param op2 op2
         * @return result
         */
        public static Number subtractPrecise(Number op1, Number op2) {
            BigDecimal left = numberToB(op1);
            BigDecimal right = numberToB(op2);
            return toNumber(left.subtract(right));
        }

        /**
         * 高精度 *
         *
         * @param op1 op1
         * @param op2 op2
         * @return result
         */
        public static Number multiplyPrecise(Number op1, Number op2) {
            BigDecimal left = numberToB(op1);
            BigDecimal right = numberToB(op2);
            return toNumber(left.multiply(right));
        }

        /**
         * 高精度 /
         *
         * @param op1 op1
         * @param op2 op2
         * @return result
         */
        public static Number dividePrecise(Number op1, Number op2) {
            BigDecimal left = numberToB(op1);
            BigDecimal right = numberToB(op2);
            return toNumber(left.divide(right, SCALE_SIZE, RoundingMode.HALF_UP));
        }

        private static BigDecimal numberToB(Number op1) {
            BigDecimal result;
            if (op1 instanceof BigDecimal) {
                result = (BigDecimal) op1;
            } else {
                result = new BigDecimal(op1.toString());
            }
            return result;
        }
    }

    public static class NormalNumberOperator {
        private NormalNumberOperator() {
        }

        /**
         * 普通的加法运算
         */
        public static Number addNormal(Number op1, Number op2) {
            int type1 = OperatorNumber.getSeq(op1.getClass());
            int type2 = OperatorNumber.getSeq(op2.getClass());
            int type = Math.max(type1, type2);
            if (type == NUMBER_TYPE_BYTE) return op1.byteValue() + op2.byteValue();
            if (type == NUMBER_TYPE_SHORT) return op1.shortValue() + op2.shortValue();
            if (type == NUMBER_TYPE_INT) return op1.intValue() + op2.intValue();
            if (type == NUMBER_TYPE_LONG) return op1.longValue() + op2.longValue();
            if (type == NUMBER_TYPE_FLOAT) return op1.floatValue() + op2.floatValue();
            if (type == NUMBER_TYPE_DOUBLE) return op1.doubleValue() + op2.doubleValue();
            if (type == NUMBER_TYPE_BIG_DECIMAL)
                return new BigDecimal(op1.toString()).add(new BigDecimal(op2.toString()));
            throw new TierRunException("不支持的对象执行了'+'操作");
        }


        public static Number subtractNormal(Number op1, Number op2) {
            int type1 = OperatorNumber.getSeq(op1.getClass());
            int type2 = OperatorNumber.getSeq(op2.getClass());
            int type = Math.max(type1, type2);
            if (type == NUMBER_TYPE_BYTE) return op1.byteValue() - op2.byteValue();
            if (type == NUMBER_TYPE_SHORT) return op1.shortValue() - op2.shortValue();
            if (type == NUMBER_TYPE_INT) return op1.intValue() - op2.intValue();
            if (type == NUMBER_TYPE_LONG) return op1.longValue() - op2.longValue();
            if (type == NUMBER_TYPE_FLOAT) return op1.floatValue() - op2.floatValue();
            if (type == NUMBER_TYPE_DOUBLE) return op1.doubleValue() - op2.doubleValue();
            if (type == NUMBER_TYPE_BIG_DECIMAL)
                return new BigDecimal(op1.toString()).subtract(new BigDecimal(op2.toString()));
            throw new TierRunException("不支持的对象执行了'-'操作");
        }

        public static Number multiplyNormal(Number op1, Number op2) {
            int type1 = OperatorNumber.getSeq(op1.getClass());
            int type2 = OperatorNumber.getSeq(op2.getClass());
            int type = Math.max(type1, type2);
            if (type == NUMBER_TYPE_BYTE) return op1.byteValue() * op2.byteValue();
            if (type == NUMBER_TYPE_SHORT) return op1.shortValue() * op2.shortValue();
            if (type == NUMBER_TYPE_INT) return op1.intValue() * op2.intValue();
            if (type == NUMBER_TYPE_LONG) return op1.longValue() * op2.longValue();
            if (type == NUMBER_TYPE_FLOAT) return op1.floatValue() * op2.floatValue();
            if (type == NUMBER_TYPE_DOUBLE) return op1.doubleValue() * op2.doubleValue();
            if (type == NUMBER_TYPE_BIG_DECIMAL)
                return new BigDecimal(op1.toString()).multiply(new BigDecimal(op2.toString()));
            throw new TierRunException("不支持的对象执行了'*'操作");
        }

        public static Number divideNormal(Number op1, Number op2) {
            int type1 = OperatorNumber.getSeq(op1.getClass());
            int type2 = OperatorNumber.getSeq(op2.getClass());
            int type = Math.max(type1, type2);
            if (type == NUMBER_TYPE_BYTE) return op1.byteValue() / op2.byteValue();
            if (type == NUMBER_TYPE_SHORT) return op1.shortValue() / op2.shortValue();
            if (type == NUMBER_TYPE_INT) return op1.intValue() / op2.intValue();
            if (type == NUMBER_TYPE_LONG) return op1.longValue() / op2.longValue();
            if (type == NUMBER_TYPE_FLOAT) return op1.floatValue() / op2.floatValue();
            if (type == NUMBER_TYPE_DOUBLE) return op1.doubleValue() / op2.doubleValue();
            if (type == NUMBER_TYPE_BIG_DECIMAL) {
                return new BigDecimal(op1.toString()).divide(new BigDecimal(op2.toString())
                        , PreciseNumberOperator.SCALE_SIZE, RoundingMode.HALF_UP);
            }
            throw new TierRunException("不支持的对象执行了'/'操作");
        }
    }

    /**
     * BigDecimal to number
     *
     * @param number BigDecimal
     * @return number
     */
    protected static Number toNumber(BigDecimal number) {
        if (number.scale() == 0) {
            if (number.compareTo(BIG_DECIMAL_INTEGER_MAX) < 1
                    && number.compareTo(BIG_DECIMAL_INTEGER_MIN) > -1) {
                return number.intValue();
            } else if (number.compareTo(BIG_DECIMAL_LONG_MAX) < 1
                    && number.compareTo(BIG_DECIMAL_LONG_MIN) > -1) {
                return number.longValue();
            }
        }
        return number;
    }
}
