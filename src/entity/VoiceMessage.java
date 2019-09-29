package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * ClassName VoiceMessage
 *
 * @author Dylan
 * @description 语音消息类
 * @createDate 2019-09-17 11:00
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {

    @XStreamAlias("MediaId")
    private String mediaId;

    public VoiceMessage(Map<String, String> requestMap,String mediaId) {
        super(requestMap);
        this.setMsgType("voice");
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
