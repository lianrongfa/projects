package cn.lianrf.gc;

/**
 * Created by lianrongfa on 2018/1/9.
 */
public class FinalizeGc {

    public static FinalizeGc CONSTANT;

    public static void main(String[] args) {
        CONSTANT=new FinalizeGc();
        CONSTANT=null;
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(CONSTANT!=null){
            CONSTANT.get();
        }else{
            System.out.println("over !!!");
        }

        CONSTANT=null;
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(CONSTANT!=null){
            CONSTANT.get();
        }else{
            System.out.println("over !!!");
        }

    }

    public void get(){
        System.out.println("exist!!!!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        CONSTANT=this;
    }
}
