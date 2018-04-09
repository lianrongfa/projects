package cn.lianrf.poi;



import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static cn.lianrf.poi.ParseStatus.*;

/**
 * Created by lianrongfa on 2018/3/22.
 */
public class WorkParse<T> implements Parse<T>{

    private Class<T> clazz;
    private Workbook workbook;

    private Map<String,Method> names;

    public WorkParse(Workbook workbook, Class<T> clazz) {
        if(clazz==null||workbook==null){
            throw new RuntimeException();
        }
        this.clazz = clazz;
        this.workbook = workbook;

        buildNames(clazz);
    }

    /**
     * 目前支持String类型
     * @param t
     * @return
     */
    public ParseStatus parseWork(T t) {
        if(t==null){
            return new ParseStatus(ParseStatus.CODE_FALSE,"");
        }
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                cell.setCellType(CellType.STRING);
                String name = cell.getStringCellValue();
                if(name==null||name==""){
                    continue;
                }
                Method method = getMethod("get"+name.substring(0,1).toUpperCase()+name.substring(1));
                if(method!=null){
                    try {
                        Object o = method.invoke(t, null);
                        cell.setCellValue((String)o);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    private void buildNames(Class<T> clazz){
        Field[] fields = clazz.getDeclaredFields();
        this.names=new HashMap();
        for (Field field : fields) {
            String name = field.getName();
            name="get"+name.substring(0,1).toUpperCase()+name.substring(1);
            try {
                Method method = clazz.getDeclaredMethod(name, null);
                names.put(name,method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }

    private Method getMethod(String name){
        return names.get(name);
    }

}
