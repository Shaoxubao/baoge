package utils;

public class Response {
    private Integer code = Integer.valueOf(0);
    private String desc;
    private String message = "操作成功！";
    private Long time = System.currentTimeMillis();
    private String version = "1.0.0";
    private Object body;

    public Response() {
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Object body) {
        this.body = body;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getBody() {
        return this.body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Response ok() {
        return new Response();
    }

    public static Response build(Object result) {
        return new Response(result);
    }
}
