package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * ClassName ImageMessage
 *
 * @author Dylan
 * @description 图片消息类
 * @createDate 2019-09-17 10:23
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{

    @XStreamAlias("MidiaId")
    private String midiaId;


    public ImageMessage(Map<String, String> requestMap,String midiaId) {
        super(requestMap);
        this.setMsgType("image");
        this.midiaId = midiaId;
    }

    public String getMidiaId() {
        return midiaId;
    }

    public void setMidiaId(String midiaId) {
        this.midiaId = midiaId;
    }
}
