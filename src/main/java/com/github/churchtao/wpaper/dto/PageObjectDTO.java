package com.github.churchtao.wpaper.dto;

/**
 * @author taojiacheng
 * @create 2018-05-31 15:23
 **/
public class PageObjectDTO<T> {
    private long total;
    private Integer pages;
    private T data;

    private PageObjectDTO() {
    }

    private PageObjectDTO(long total, Integer pages, T data) {
        this.total = total;
        this.pages = pages;
        this.data = data;
    }

    public static <T> PageObjectDTO init(long total, Integer pages, T data){
       return new PageObjectDTO(total,pages,data);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
