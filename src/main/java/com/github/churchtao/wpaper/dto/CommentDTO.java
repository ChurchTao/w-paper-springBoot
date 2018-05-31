package com.github.churchtao.wpaper.dto;

import java.util.Date;

public class CommentDTO {
    private String name;
    private String response;
    private String content;
    private boolean isThumb;
    private int userId;
    private Date time;
    private String avatar;

    public CommentDTO(String name, String response, String content, int userId, Date time, String avatar) {
        this.name = name;
        this.response = response;
        this.content = content;
        this.userId = userId;
        this.time = time;
        this.avatar = avatar;
        this.isThumb = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isThumb() {
        return isThumb;
    }

    public void setThumb(boolean thumb) {
        isThumb = thumb;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
