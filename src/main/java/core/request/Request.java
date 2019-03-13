package core.request;

public class Request {
    private String HttpType;//get post delete
    private String url;

    public Request(String httpType, String url) {
        HttpType = httpType;
        this.url = url;
    }

    public String getHttpType() {
        return HttpType;
    }

    public void setHttpType(String httpType) {
        HttpType = httpType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
