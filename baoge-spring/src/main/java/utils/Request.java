package utils;

public class Request {
    private String client;
    private String appKey;
    private String signature;
    private String time;
    private String version;
    private String format;
    private String body;

    public Request() {
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
