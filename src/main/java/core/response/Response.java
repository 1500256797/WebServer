package core.response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

public class Response {
    private OutputStream output;
    private static Logger log = Logger.getLogger(String.valueOf(Response.class.getClass()));
    public Response(OutputStream outputStream) {
        this.output = outputStream;
    }
    //向Client响应信息
    public void render(String string) {
        try {
            System.out.println("返回结果");
            output.write("HTTP/1.1 200 OK\n".getBytes());
            output.write("Content-Type: text/html; charset=UTF-8\n\n".getBytes());
            output.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            log.info("cann't response");
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
