package com.github.churchtao.wpaper.controller;

import com.github.churchtao.wpaper.service.ProfessionService;
import com.github.churchtao.wpaper.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author taojiacheng
 * @create 2018-04-23 18:56
 **/
@RestController
public class ProfessionController extends BaseController{
    @Autowired
    private ProfessionService professionService;

    @RequestMapping(value = "/profession/getByUid",method = RequestMethod.GET)
    public RestResponse getProfessionByUid(@RequestParam(name = "id") Integer id){
        return RestResponse.ok(professionService.getTypeByUid(id),200,"获取成功~");
    }
    @RequestMapping(value = "/profession/getHot",method = RequestMethod.GET)
    public RestResponse getProfessions(){
        return RestResponse.ok(professionService.getTypes(),200,"获取成功~");
    }

    @RequestMapping(value = "/profession/getTagsFocus",method = RequestMethod.GET)
    public RestResponse getTagsFocus(@RequestParam(name = "userId") Integer userId,
                                @RequestParam(name = "pageNum") Integer pageNum,
                                @RequestParam(name = "pageSize") Integer pageSize){
        return RestResponse.ok(professionService.getTagsByUidFocus(userId,pageNum,pageSize),200,"获取成功~");
    }
    @RequestMapping(value = "/profession/getTagsNotFocus",method = RequestMethod.GET)
    public RestResponse getTagsNotFocus(@RequestParam(name = "userId") Integer userId,
                                @RequestParam(name = "pageNum") Integer pageNum,
                                @RequestParam(name = "pageSize") Integer pageSize){
        return RestResponse.ok(professionService.getTagsByUidNotFocus(userId,pageNum,pageSize),200,"获取成功~");
    }



    @RequestMapping(value = "/profession/add",method = RequestMethod.POST)
    public RestResponse add(@RequestParam(name = "name") String name,
                            @RequestParam(name = "avatar") String avatar,
                            @RequestParam(name = "about") String about){
        return RestResponse.ok(professionService.addProfession(name,avatar,about),200,"添加成功~");
    }


    @RequestMapping(value = "/profession/update",method = RequestMethod.POST)
    public RestResponse update(@RequestParam(name = "id") Integer id,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "avatar") String avatar,
                               @RequestParam(name = "about") String about,
                               @RequestParam(name = "status") Integer status
                               ){
        return RestResponse.ok(professionService.updateProfessionInfo(id,name,avatar,about,status),200,"修改成功~");
    }

    @RequestMapping(value = "/profession/changeFocus",method = RequestMethod.POST)
    public RestResponse focus(@RequestParam(name = "userId") Integer userId,
                              @RequestParam(name = "tagId") Integer tagId,
                              @RequestParam(name = "status") Integer status){
        return RestResponse.ok(professionService.changeFocus(userId,tagId,status),200,"修改成功~");
    }


}
