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

    private static ThreadLocal<Integer> PAGE_TOTAL = new ThreadLocal<>();

    private boolean success;
    private String code;
    private String message;
    private Integer total;
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

    public static void setPageTotal(Integer total) {
        PAGE_TOTAL.set(total);
    }

    public static Integer getPageTotal() {
        return PAGE_TOTAL.get();
    }
}
