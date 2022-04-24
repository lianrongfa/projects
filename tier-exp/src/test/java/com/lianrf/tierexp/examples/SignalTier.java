package com.lianrf.tierexp.examples;

import com.lianrf.tierexp.TierExpEngineImpl;
import com.lianrf.tierexp.context.DefaultContext;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * SignalTier
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/18 4:06 下午
 */
public class SignalTier {

    private final TierExpEngineImpl engine = new TierExpEngineImpl(false);

    private final DefaultContext map = new DefaultContext();

    public static void main(String[] args) {
        Byte a=1;
        Short b=1;
        Integer c=1;
        Long d=1L;
        Float e=1F;
        Double f=1D;
    }

    @Test
    public void testNot(){
        String exp="!a";

        map.put("a",true);
        Assert.assertEquals(false, engine.run(exp, map));

        map.put("a",false);
        Assert.assertEquals(true, engine.run(exp, map));
    }


    @Test
    public void testDot(){
        String exp="a.b";

        map.put("a",new TestItem());
        Assert.assertEquals(TestItem.class, engine.run(exp, map).getClass());

        String exp2="a.test()";

        map.put("a",new TestItem());
        engine.run(exp2, map);

    }

    /**
     * 取反 — +
     */
    @Test
    public void testNegate(){
        String exp="-a";

        map.put("a",1);
        Assert.assertEquals(new BigDecimal("-1"), engine.run(exp, map));


        String exp2="--a";

        map.put("a",1);
        Assert.assertEquals(new BigDecimal("1"), engine.run(exp2, map));

        exp2="+-a";

        map.put("a",1);
        Assert.assertEquals(new BigDecimal("-1"), engine.run(exp2, map));
    }

    /**
     * 取模 mod == %
     */
    @Test
    public void testMod(){
        String exp="a mod 3";

        map.put("a",10);
        Assert.assertEquals(1L, engine.run(exp, map));



        map.put("a",11);
        Assert.assertEquals(2L, engine.run(exp, map));


        map.put("a",12);
        Assert.assertEquals(0L, engine.run(exp, map));
    }


    static class TestItem {

        public static Object getB(){
            return new TestItem();
        }

        public static void test(){
            System.out.println("my test");
        }
    }
}
