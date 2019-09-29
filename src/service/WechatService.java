package service;

import com.thoughtworks.xstream.XStream;
import entity.*;
import net.sf.json.JSONObject;
import org.apache.log4j.net.SocketNode;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.JuheInterfaceUtil;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * ClassName WechatService
 *
 * @author Dylan
 * @description 微信消息回复
 * @createDate 2019-09-13 23:22
 */
public class WechatService {

    //和微信配置里面的token对应，降低耦合可以写在配置文件
    private static final String TOKEN = "bearbear";
    //调用聚合数据接口的标示
    private static final String APPKEY = "cdc602c026be848f021f94758e1994e3";


    //API调用所需的access_token的请求地址
    private static final String GET_TOKEN_URL = "https://api.weixin.qq" +
            ".com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //测试号信息 wx55f9c91e8105383a a99d940f7b2715476e0352fb858f66ec
    //公众号信息 wx61d0f931d7208ca8 9a5d682ef35128060270fe4dbff78861
    private static final String APPID = "wx55f9c91e8105383a";
    private static final String APPSECRET = "a99d940f7b2715476e0352fb858f66ec";

    public static AccessToken aToken;

    /**
     * 获取令牌
     */
    private static void getToken(){
        String url = GET_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET"
                ,APPSECRET);
        String tokenStr = JuheInterfaceUtil.get(url);
        System.out.println(tokenStr);
        //解析json对象
        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
        //token
        String accessToken = jsonObject.getString("access_token");
        //有效时间
        String expiresIn = jsonObject.getString("expires_in");

        aToken = new AccessToken(accessToken,expiresIn);

    }

    /**
     * 向外暴露的获取token的方法
     * @return
     */
    public static String getAccessToken(){
        if(aToken == null || aToken.isExpired()){
            getToken();
        }
        return aToken.getAccessToken();
    }


    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean check(String signature, String timestamp, String nonce) {

        // 1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[]{TOKEN, timestamp, nonce};
        System.out.println(strs);
        Arrays.sort(strs);
        System.out.println(strs);
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0] + strs[1] + strs[2];
        String mysig = sha1(str);
        System.out.println(mysig + "---test---");
        System.out.println(signature + "---test--");
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信

        return mysig.equals(signature);
    }

    /**
     * 进行sha1加密
     *
     * @param str
     * @return
     */
    private static String sha1(String str) {
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //获取加密后的数组
            byte[] bytes = md.digest(str.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
                    'c', 'd', 'e', 'f'};
            StringBuilder stringBuilder = new StringBuilder();
            //处理加密结果
            for (byte b : bytes) {
                stringBuilder.append(chars[(b >> 4) & 15]);
                stringBuilder.append(chars[b & 15]);
            }

            System.out.println(stringBuilder.toString() + "---test---");
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> parseRequest(InputStream inputStream) {
        HashMap<String, String> hashMap = new HashMap<>();

        SAXReader saxReader = new SAXReader();
        try {
            //读取输入流获取文档
            Document document = saxReader.read(inputStream);
            //根据文档获取根节点
            Element rootElement = document.getRootElement();
            //获取根节点的所有子节点
            List<Element> list = rootElement.elements();

            for (Element e : list) {
                hashMap.put(e.getName(), e.getStringValue());

            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return hashMap;
    }

    /**
     * 处理所有的事件和消息回复
     *
     * @param requestMap
     * @return xml数据包
     */
    public static String getResponse(Map<String, String> requestMap) {
        BaseMessage msg = null;
        //获取需要处理消息的类型
        String msgType = requestMap.get("MsgType");

        switch (msgType) {
            case "text":
                msg = dealTextMessage(requestMap);
                System.out.println(msg + "---test---text");
                break;
            case "image":
                msg = dealNewsMessage(requestMap);
                System.out.println(msg + "---test---news");
                break;
            default:
                break;
        }
        if (msg != null) {
            System.out.println(beanToXml(msg) + "---test1----");
            return beanToXml(msg);
        }
        return null;
    }



    /**
     * 处理图文消息
     * 目前就是用户发送一个图片，则回复一个图文消息
     * @param requestMap
     * @return
     */
    private static BaseMessage dealNewsMessage(Map<String, String> requestMap) {
        //用户发来的消息
        String msg = requestMap.get("PicUrl");
        //调用方法返回聊天的内容
        System.out.println(msg + "---用户发送过来的图片链接");

        List<Article> articles = new ArrayList<>();
        //articles.add(new Article("这是标题","这是内容","图片路径","点击访问的路径"));
        articles.add(new Article("这是标题", "这是内容", "http://mmbiz.qpic" +
                ".cn/mmbiz_jpg/xucCw8QCh27s3VBlKrQfFM4OgzNYvokenGYu7lb4SMBNOXCtYHV3jGZUbtZ8ac0" +
                "scQnoSzYyE0JgcxUn6JqnOA/0",
                "https://www.baidu.com"));
        NewsMessage newsMessage = new NewsMessage(requestMap,articles);
        return newsMessage;
    }

    /**
     * 处理文本消息
     *
     * @param requestMap
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        //用户发来的消息
        String msg = requestMap.get("Content");
        //调用方法返回聊天的内容
        System.out.println(msg + "---用户发来的消息");
        //如果用户发送过来 图文 则回复一个图文消息
        if("图文".equals(msg)){
            List<Article> articles = new ArrayList<>();
            //articles.add(new Article("这是标题","这是内容","图片路径","点击访问的路径"));
            articles.add(new Article("这是标题", "这是内容", "http://mmbiz.qpic" +
                    ".cn/mmbiz_jpg/xucCw8QCh27s3VBlKrQfFM4OgzNYvokenGYu7lb4SMBNOXCtYHV3jGZUbtZ8ac0scQnoSzYyE0JgcxUn6JqnOA/0", "https://www.baidu.com"));
            NewsMessage newsMessage = new NewsMessage(requestMap,articles);
            return newsMessage;
        }//此处使用聊天机器人处理用户发送的文本消息
        String content = chat(msg);
        TextMessage tm = new TextMessage(requestMap, content);
        return tm;
    }

    /**
     * 将数据处理成需要的格式
     *
     * @param msg
     * @return
     */
    private static String beanToXml(BaseMessage msg) {
        XStream xStream = new XStream();
        //设置需要处理的类
        xStream.processAnnotations(TextMessage.class);
        xStream.processAnnotations(ImageMessage.class);
        xStream.processAnnotations(MusicMessage.class);
        xStream.processAnnotations(NewsMessage.class);
        xStream.processAnnotations(VideoMessage.class);
        xStream.processAnnotations(VoiceMessage.class);
        xStream.processAnnotations(NewsMessage.class);
        //转为需要的格式
        String xml = xStream.toXML(msg);
        return xml;
    }

    /**
     * 调用聚合数据中的聊天api
     *
     * @param msg 用户发过来的消息
     * @return
     */
    private static String chat(String msg) {
        String result = null;
        //"http://op.juhe.cn/robot/index"
        String url = "http://op.juhe.cn/iRobot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info", msg);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype", "");//返回的数据的格式，json或xml，默认为json
        params.put("loc", "");//地点，如北京中关村
        params.put("lon", "");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat", "");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid", "");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        try {
            result = JuheInterfaceUtil.net(url, params, "GET");
            //解析json
            JSONObject jsonObject = JSONObject.fromObject(result);
            //取出error_code
            int code = jsonObject.getInt("error_code");
            if (code != 0) {
                return null;
            }
            //取出返回的消息的内容
            String resp = jsonObject.getJSONObject("result").getString("text");
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
