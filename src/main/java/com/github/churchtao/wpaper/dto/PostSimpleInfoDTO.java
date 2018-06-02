package com.github.churchtao.wpaper.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author taojiacheng
 * @create 2018-04-23 17:17
 **/
public class PostSimpleInfoDTO {
    private String href;
    private String title;
    private String author;
    private String typeName;
    private String type;
    private int readingtimes;
    private List<Integer> tags;
    private int like;
    private int comment;
    private boolean ishot;
    private boolean isspecialist;
    private String time;

    public PostSimpleInfoDTO(String href, String title, String author, String typeName, String type, int readingtimes, List<Integer> tags, int like, int comment, Timestamp time) {
        this.href = href;
        this.title = title;
        this.author = author;
        this.typeName = typeName;
        this.type = type;
        this.readingtimes = readingtimes;
        this.tags = tags;
        this.like = like;
        this.comment = comment;
        this.ishot = false;
        this.isspecialist = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(time);
        this.time = dateString;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReadingtimes() {
        return readingtimes;
    }

    public void setReadingtimes(int readingtimes) {
        this.readingtimes = readingtimes;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public boolean isIshot() {
        return ishot;
    }

    public void setIshot(boolean ishot) {
        this.ishot = ishot;
    }

    public boolean isIsspecialist() {
        return isspecialist;
    }

    public void setIsspecialist(boolean isspecialist) {
        this.isspecialist = isspecialist;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
