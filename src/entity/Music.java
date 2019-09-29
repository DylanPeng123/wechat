package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ClassName Music
 *
 * @author Dylan
 * @description 音乐实体类
 * @createDate 2019-09-17 13:37
 */
@XStreamAlias("music")
public class Music {

    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;
    @XStreamAlias("MusicURL")
    private String musicURL;
    @XStreamAlias("HQMusicUrl")
    private String hQMusicUrl;
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

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

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
