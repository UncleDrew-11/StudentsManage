package jsj.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
@ToString
public class Response {

    private boolean success;
    private String code;
    private String message;
    private Object data;
    private String stackTrace;

    private Response() {}

    public static Response ok() {
        return ok(null);
    }

    public static Response ok(Object data) {
        Response response = new Response();
        response.success = true;
        response.code = "200";
        response.data = data;
        return response;
    }

    public static Response error(Exception e) {
        Response response = new Response();
        response.success = false;
        response.code = "500";
        response.message = e.getMessage();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        response.stackTrace = sw.toString();
        return response;
    }
}
