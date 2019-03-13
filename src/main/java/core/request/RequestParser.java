package core.request;

import core.request.Request;

import java.io.IOException;
import java.io.InputStream;

public class RequestParser {

    private final static  int BUFFER_SIZE = 2048;


    /*
    返回Request对象
    * @Param inputStream
    * @return Request
    * */
    public Request requestParser(InputStream inputStream) {
        String requestMsg = readInputStream(inputStream);
        Request request = new Request(parseType(requestMsg), parseUrl(requestMsg));
        System.out.println("已生成请求对象"+request.toString());
        return request;
    }

    /*
     * 从输入流中读取信息
     *@param InputStream
     * @ return
     * */
    public String readInputStream(InputStream inputStream) {
        //read buffer
        StringBuffer requestBuffer = new StringBuffer();
        byte[] bytes = new byte[BUFFER_SIZE];
        int readLength;
        try {
            readLength = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            readLength = -1;
        }
        for (int i = 0; i < readLength; i++) {
            requestBuffer.append((char) bytes[i]);
        }
        return requestBuffer.toString();
    }

    /*
    * @param string
    * @return requestType
    * */
    private String parseType(String str){
        int index = 0;
        //type和url以空格分开
        if ((index=str.indexOf(' ')) != -1) {
            return str.substring(0, index);
        }
        return null;
    }

    /*GET /doaction?p=123&k=3343 HTTP/1.1
    * @param str
    * @return url
    * */
    private String  parseUrl(String str) {
        int index1 = str.indexOf(' ');//第一个空格
        int index2 = str.indexOf(' ', index1 + 1);//第二个空格
        return str.substring(index1 + 1, index2);
    }




}
