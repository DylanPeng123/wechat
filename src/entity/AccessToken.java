package entity;

/**
 * ClassName AccessToken
 *
 * @author Dylan
 * @description 封装accesstoken的对象
 * @createDate 2019-09-19 10:45
 */
public class AccessToken {
    private String accessToken;
    private long expireTime;

    public AccessToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        this.expireTime =
                System.currentTimeMillis()+Integer.parseInt(expiresIn)*1000;
    }

    /**
     * 判断token是否过期
     * @return
     */
    public boolean isExpired(){
        return System.currentTimeMillis() > expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
