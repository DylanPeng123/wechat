package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * ClassName MusicMessage
 *
 * @author Dylan
 * @description 音乐消息类
 * @createDate 2019-09-17 11:18
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage{

    private Music music;

    public MusicMessage(Map<String, String> requestMap,Music music) {
        super(requestMap);
        this.setMsgType("music");
        this.music = music;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
