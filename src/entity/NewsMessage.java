package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName NewsMessage
 *
 * @author Dylan
 * @description 图文消息类
 * @createDate 2019-09-17 11:25
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{

    @XStreamAlias("Articles")
    private List<Article> articles = new ArrayList<>();
    @XStreamAlias("ArticleCount")
    private String articleCount;

    public NewsMessage(Map<String, String> requestMap, List<Article> articles) {
        super(requestMap);
        this.articles = articles;
        this.articleCount = articles.size()+"";
        this.setMsgType("news");
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }
}
