package com.github.churchtao.wpaper.dto;


public class TagDTO {
    private int id;
    private String avatar;
    private String name ;
    private int likeNum;
    private int postNum;
    private boolean focus;

    public TagDTO(int id, String avatar, String name, int likeNum, int postNum, boolean focus) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.likeNum = likeNum;
        this.postNum = postNum;
        this.focus = focus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }
}
