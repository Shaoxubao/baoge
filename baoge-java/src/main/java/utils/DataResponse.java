package utils;

/**
 * @Author shaoxubao
 * @Date 2019/10/16 14:50
 */
public class DataResponse<T> {
    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = 1;
    private Integer code;
    private Integer errorCode;
    private String msg;
    private T data;

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public DataResponse() {
        this.code = 0;
        this.errorCode = 0;
        this.msg = "";
    }

    public DataResponse(Integer code, String msg, T t) {
        this.code = 0;
        this.errorCode = 0;
        this.msg = "";
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public DataResponse(T data) {
        this(0, "", data);
    }

    public static DataResponse builder() {
        return new DataResponse();
    }

    public static DataResponse builder(Integer code, String msg) {
        DataResponse dataResponse = builder();
        dataResponse.setCode(code);
        dataResponse.setMsg(msg);
        return dataResponse;
    }

    public static DataResponse builder(Integer code, String msg, Object data) {
        DataResponse dataResponse = builder(code, msg);
        dataResponse.setData(data);
        return dataResponse;
    }

    public static DataResponse builderSuccess() {
        DataResponse dataResponse = builder();
        dataResponse.setCode(0);
        dataResponse.setMsg("请求成功");
        return dataResponse;
    }

    public static DataResponse builderSuccess(Object data) {
        DataResponse dataResponse = builderSuccess();
        dataResponse.setData(data);
        return dataResponse;
    }

    public static DataResponse builderFailed() {
        return builderFailed("请求失败");
    }

    public static DataResponse builderFailed(String msg) {
        DataResponse dataResponse = builder();
        dataResponse.setCode(1);
        dataResponse.setMsg(msg);
        return dataResponse;
    }

    public static DataResponse builderFailed(String msg, Integer code) {
        DataResponse dataResponse = builder();
        dataResponse.setCode(code);
        dataResponse.setMsg(msg);
        return dataResponse;
    }

    public static DataResponse builderFailed(DataResponse arg) {
        DataResponse dataResponse = builder();
        dataResponse.setCode(arg.getCode());
        dataResponse.setMsg(arg.getMsg());
        return dataResponse;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
