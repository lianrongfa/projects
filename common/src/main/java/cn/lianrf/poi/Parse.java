package cn.lianrf.poi;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by lianrongfa on 2018/3/22.
 */
public interface Parse<T> {
    ParseStatus parseWork(T t);
}
