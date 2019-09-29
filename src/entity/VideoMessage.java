package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * ClassName VideoMessage
 *
 * @author Dylan
 * @description 视频消息类
 * @createDate 2019-09-17 11:07
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage{

    @XStreamAlias("MediaId")
    private String mediaId;
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;


    public VideoMessage(Map<String, String> requestMap,String mediaId,String title,String description) {
        super(requestMap);
        this.setMsgType("video");
        this.description = description;
        this.mediaId = mediaId;
        this.title = title;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
