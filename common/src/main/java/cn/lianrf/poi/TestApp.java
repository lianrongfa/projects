package cn.lianrf.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * Created by lianrongfa on 2018/3/23.
 */
public class TestApp {

    public static void main(String[] args) {
        URL resource = TestApp.class.getClassLoader().getResource("");
        String path = resource.getPath();
        File file = new File(path+"test.xlsx");

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            WorkParse<TestEntity> workParse = new WorkParse<TestEntity>(workbook,TestEntity.class);
            TestEntity testEntity = new TestEntity();
            testEntity.setAge("111351");
            testEntity.setName("gweg");
            testEntity.setNum("165165");

            workParse.parseWork(testEntity);

            File file1 = new File("C:\\Users\\lianrongfa\\Desktop\\test\\test.xlsx");
            FileOutputStream outputStream = new FileOutputStream(file1);
            workbook.write(outputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
