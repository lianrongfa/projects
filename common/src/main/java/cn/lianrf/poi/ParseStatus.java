package cn.lianrf.poi;

/**
 * Created by lianrongfa on 2018/3/22.
 */
public class ParseStatus {
    public static final boolean CODE_TRUE=true;
    public static final boolean CODE_FALSE=false;

    private boolean code;
    private String message;

    /**
     * @param code 解析状态码
     * @param message 提示信息
     */
    public ParseStatus(boolean code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
