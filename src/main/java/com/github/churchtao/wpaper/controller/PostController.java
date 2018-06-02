package com.github.churchtao.wpaper.controller;


import com.github.churchtao.wpaper.dao.PostRepository;
import com.github.churchtao.wpaper.dao.UserRepository;
import com.github.churchtao.wpaper.entity.Post;
import com.github.churchtao.wpaper.entity.User;
import com.github.churchtao.wpaper.service.PostService;
import com.github.churchtao.wpaper.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author taojiacheng
 * @create 2018-04-24 10:06
 **/
@RestController
public class PostController extends BaseController {
    @Autowired
    private PostRepository postDAO;
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userDAO;

    @RequestMapping(value = "/post/getHomePost",method = RequestMethod.GET)
    public RestResponse getHomePost(@RequestParam Integer id){
        return RestResponse.ok(postService.findPostMostHot(id),200,"获取成功~");
    }

    @RequestMapping(value = "/post/getUserPost",method = RequestMethod.GET)
    public RestResponse getUserPost(@RequestParam(name = "id") Integer id,
                                    @RequestParam(name = "pageNum") Integer pageNum,
                                    @RequestParam(name = "pageSize") Integer pageSize){
        return RestResponse.ok(postService.findUserPost(pageNum,pageSize, id),200,"获取成功~");
    }

//    @RequestMapping(value = "/post/countPost",method = RequestMethod.GET)
//    public RestResponse countComment(Param param){
//        int userId = param.getInt("uid");
//        int type = param.getInt("type");
//        switch (type){
//            case 1:return RestResponse.ok(postService.countUserPost(userId),200);
//            case 2:return RestResponse.ok(postService.countUserPost(userId),200);
//            case 3:return RestResponse.ok(postService.countUserPost(userId),200);
//            case 4:return RestResponse.ok(postService.countUserPost(userId),200);
//            default: return RestResponse.fail(500,"请给出正确的type");
//        }
//    }
//    @RequestMapping(value = "/post/countByType",method = RequestMethod.GET)
//    public RestResponse countByType(Param param){
//        int type = param.getInt("type");
//        return RestResponse.ok(postService.countPostByType(type),200);
//    }

    @RequestMapping(value = "/post/getPostByType",method = RequestMethod.GET)
    public RestResponse getPostByType(@RequestParam(name = "id") Integer id,
                                      @RequestParam(name = "typeId") Integer typeId,
                                      @RequestParam(name = "pageNum") Integer pageNum,
                                      @RequestParam(name = "pageSize") Integer pageSize){
        return RestResponse.ok(postService.findPostByPageAndKind(pageNum,pageSize,typeId),200,"获取成功~");
    }
    @RequestMapping(value = "/post/getPostById",method = RequestMethod.GET)
    public RestResponse getPostById(@RequestParam(name = "id") Integer id){
        Optional<Post> postOptional = postDAO.findById(id);
        return postOptional.map(post ->
                RestResponse.ok(post, 200, "获取成功~"))
                .orElseGet(() ->
                        RestResponse.fail(-1));
    }
    @RequestMapping(value = "/post/getAuthor",method = RequestMethod.GET)
    public RestResponse getAuthor(@RequestParam(name = "id") Integer id){
        Post post = postDAO.findById(id).get();
        return userDAO.findById(post.getUserId()).map(user->{
            user.setToken("");
            user.setLastIp("");
            user.setPassword("");
            user.setLoginName("");
            return RestResponse.ok(user,200,"获取成功~");
        }).orElseGet(() -> RestResponse.fail(-1));
    }

    @RequestMapping(value = "/post/publish",method = RequestMethod.POST)
    public RestResponse publishPost(@RequestBody PostNew postNew){
        return RestResponse.ok(postService.savePost(postNew.getTitle(),postNew.getContent(),postNew.getUserId(),postNew.getUserName(),postNew.getKind(),postNew.getKindName()),200,"发表成功");
    }

}
class PostNew{
    private String title;
    private String content;
    private Integer userId;
    private String userName;
    private Integer kind;
    private String kindName;

    public PostNew() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}
