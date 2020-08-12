package cn.lianrf.vo;

import lombok.Data;

/**
 * @version: v1.0
 * @date: 2020/7/27
 * @author: lianrf
 */
@Data
public class Result {
    private Integer state;
    private String msg;
    private Item result;

    public static Result ok(Item item){
        Result result = new Result();
        result.setState(1);
        result.setResult(item);
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.setState(0);
        return result;
    }

    public static Result fail2(Item item){
        item.setResponsetime("<font color=red>超时</font>");
        item.setTtl("<font color=red>超时</font>");
        Result result = new Result();
        result.setResult(item);
        result.setState(1);
        return result;
    }

    @Data
    public static class Item{
        private String ip;
        private String ipaddress;
        private String responsetime;
        private String ttl;
        private String bytes;

        public void setResponsetime(int responsetime) {
            this.responsetime = responsetime+"毫秒";
        }
        public void setResponsetime(String responsetime) {
            this.responsetime = responsetime;
        }
    }
}
