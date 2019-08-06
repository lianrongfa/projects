package cn.lianrf.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianrongfa on 2018/1/11.
 */
public class GcLog {
    public static void main(String[] args) {
        ArrayList<GcObject> gcObjects = new ArrayList<GcObject>();
        for (;;){
            if(gcObjects.size()<100000){
                gcObjects.add(new GcObject());
            }else{
                gcObjects.clear();
                System.gc();
            }
        }
    }
}
class GcObject{
    private String s;
    private List list;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}