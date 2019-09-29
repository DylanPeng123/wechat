package servlet;

import service.WechatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "WechatServlet" ,urlPatterns = "/wx")
public class WechatServlet extends HttpServlet {
    /**
     signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     timestamp	时间戳
     nonce	随机数
     echostr	随机字符串
     */

    /**
     * 接受消息和事件推送
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post 请求");
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        //处理消息和事件推送
        Map<String, String> requestMap = WechatService.parseRequest(request.getInputStream());
        //准备回复的数据包
        String respXml = WechatService.getResponse(requestMap);
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get 请求");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println(signature+","+nonce+","+timestamp+","+echostr+
                "---test---");
        if (WechatService.check(signature,timestamp,nonce)){
            System.out.println("校验成功 ---test---");
            PrintWriter out = response.getWriter();
            //原样返回echostr
            out.print(echostr);
            out.flush();
            out.close();

        }else{
            System.out.println("校验失败 ---test---");
        }

    }
}
