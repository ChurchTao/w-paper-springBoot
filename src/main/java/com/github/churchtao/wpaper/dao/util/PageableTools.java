package com.github.churchtao.wpaper.dao.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author taojiacheng
 * @create 2018-05-31 10:52
 **/
public class PageableTools {


    public static Pageable noSortPage(Integer page,Integer size){
        return PageRequest.of(page,size);
    }


    public static Pageable sortPage(Integer page,Integer size,String... sortStr){
        Sort sort = Sort.by(sortStr);
        return PageRequest.of(page,size,sort);
    }

    public static Pageable sortPageDesc(Integer page,Integer size,String... sortStr){
        Sort sort = Sort.by(Sort.Direction.DESC,sortStr);
        return PageRequest.of(page,size,sort);
    }
}
