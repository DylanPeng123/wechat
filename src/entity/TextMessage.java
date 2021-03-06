package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * ClassName TextMessage
 *
 * @author Dylan
 * @description 文本消息类
 * @createDate 2019-09-17 10:19
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {

    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String,String> requestMap,String content) {
        super(requestMap);
        //设置文本信息的msgType为text
        this.setMsgType("text");
        this.content=content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
