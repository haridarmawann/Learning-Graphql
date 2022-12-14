package hari.core.graphql.helper;

public class ResponseBody {

    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseBody(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}