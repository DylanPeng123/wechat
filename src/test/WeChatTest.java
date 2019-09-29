package test;

import com.thoughtworks.xstream.XStream;
import entity.Button;
import entity.TextMessage;
import net.sf.json.JSONObject;
import org.junit.Test;
import service.WechatService;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName WeChatTest
 *
 * @author Dylan
 * @description TODO
 * @createDate 2019-09-17 13:52
 */
public class WeChatTest {

    @Test
    public void testButton(){
        Button button = new Button();
//        button.getButtons().add(new ClickButton());
//        button.getButtons().add(new AbstractButton());

        JSONObject jsonObject = JSONObject.fromObject(button);
        System.out.println(jsonObject.toString());

    }

    @Test
    public void testToken(){
        System.out.println(WechatService.getAccessToken());
        System.out.println(WechatService.getAccessToken());
    }

    @Test
    public void testMsg(){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("ToUserName","to");
        requestMap.put("FromUserName","from");
        requestMap.put("MsgType","type");
        TextMessage tm = new TextMessage(requestMap,"hello world!");

        XStream xStream = new XStream();
        //设置需要处理的消息类
        xStream.processAnnotations(TextMessage.class);
        String xml = xStream.toXML(tm);
        //System.out.println(xml+"---test---");


    }

}
