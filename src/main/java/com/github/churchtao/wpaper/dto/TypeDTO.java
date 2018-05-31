package com.github.churchtao.wpaper.dto;

/**
 * @author taojiacheng
 * @create 2018-04-23 17:13
 **/
public class TypeDTO {
    private String url;
    private String typeName;
    private String type;
    private int id;

    public TypeDTO(String url, String typeName, String type, int id) {
        this.url = url;
        this.typeName = typeName;
        this.type = type;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
