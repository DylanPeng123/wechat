package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * ClassName BaseMessage
 *
 * @author Dylan
 * @description 回复、接收消息基础类
 * @createDate 2019-09-14 0:47
 */

public class BaseMessage {

    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;


    //这些信息在交互过程中是不变的
    public BaseMessage(Map<String,String> requestMap){

        this.toUserName = requestMap.get("FromUserName");
        this.fromUserName = requestMap.get("ToUserName");
        this.createTime = System.currentTimeMillis() / 1000 + "";

    }


    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
