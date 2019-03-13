package core;

import core.DispatchService.DispatchService;
import core.request.Request;
import core.request.RequestParser;
import core.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class WebServer {
    private static Logger log = Logger.getLogger(String.valueOf(WebServer.class.getClass()));
    //port
    private static int LISTEN_PORT =49990;
    //socket
    private static ServerSocket webSocket;
    public WebServer(){
        initServer();
    }
    public static void initServer(){
        try {
            webSocket = new ServerSocket(LISTEN_PORT);
            log.info("服务器已经启动，等待用户进行链接。。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void StartServer() {
        //采用线程池管理线程
        ExecutorService exec = Executors.newCachedThreadPool();
        while(true){
            try {
                Socket socket = webSocket.accept();
                exec.submit(new service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class service implements Runnable {
        private Socket socket;
        private InputStream inputStream  = null;
        private OutputStream outputStream =null;

        public service(Socket s) {
            socket = s;
            System.out.println("线程1正在处理请求");
        }

        public void run() {
            dispathchRequest();
            System.out.println("收到客户端：" + socket.getInetAddress() + ":" + socket.getPort());

        }

        public void dispathchRequest() {
            // 读取请求信息内容
            Request request = new RequestParser().requestParser(inputStream);
            Response response = new Response(outputStream);
            DispatchService dispatchService = new DispatchService();
            System.out.println("dispatchService.Dispather(request, response);。。。。");
            dispatchService.Dispather(request, response);
        }
    }

}
