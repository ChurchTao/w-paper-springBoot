package com.github.churchtao.wpaper.controller;

import com.github.churchtao.wpaper.service.ProfessionService;
import com.github.churchtao.wpaper.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public RestResponse update(@RequestBody ProfessionObj obj
                               ){
        return RestResponse.ok(professionService.updateProfessionInfo(obj.getId(),obj.getName(),obj.getAvatar(),obj.getAbout(),obj.getStatus()),200,"修改成功~");
    }

    @RequestMapping(value = "/profession/changeFocus",method = RequestMethod.POST)
    public RestResponse focus(@RequestBody ProfessionObj obj){
        return RestResponse.ok(professionService.changeFocus(obj.getUserId(),obj.getTagId(),obj.getStatus()),200,"修改成功~");
    }


}
class ProfessionObj{
    private Integer userId;
    private Integer tagId;
    private Integer id;
    private String name;
    private String avatar;
    private String about;
    private Integer status;

    public ProfessionObj() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
