package core.DispatchService;

import core.request.Request;
import core.response.Response;

/*
* 根据请求类型和url找到对应的handler
* */
public class DispatchService {
    public void Dispather(Request request, Response response) {
        String handlerInfo = getHandlerInfo(request);//找到对应Handler
        StringBuilder sb = new StringBuilder();
        sb.append("请求类型： " + request.getHttpType());
        sb.append("<br/>请求URI： " + request.getUrl());
        sb.append("<br/>返回结果： " + handlerInfo);
        // 输出控制器返回结果
        System.out.println("response.render(sb.toString());");
        response.render(sb.toString());
    }

    private String getHandlerInfo(Request request) {
        String info = " ";        //Handler信息
        String [] handlerinfo = request.getUrl().split("\\/");//此处可能有bug
        if(handlerinfo.length==0){
            return "404 ERROR URL";
        }
        if ("test1".equalsIgnoreCase(handlerinfo[1])) {
            return info = "已经找到handler---Test1";
        } else if ("test2".equalsIgnoreCase(handlerinfo[1])) {
            return info = "已经找到handler---Test2";
        }else{
            return info = "没有你想请求的方法";
        }
    }
}
