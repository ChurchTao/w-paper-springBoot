package com.github.churchtao.wpaper.controller;


import com.github.churchtao.wpaper.dao.CommentRepository;
import com.github.churchtao.wpaper.dao.UserRepository;
import com.github.churchtao.wpaper.dto.CommentDTO;
import com.github.churchtao.wpaper.dto.PageObjectDTO;
import com.github.churchtao.wpaper.entity.Comment;
import com.github.churchtao.wpaper.entity.User;
import com.github.churchtao.wpaper.exception.ServerException;
import com.github.churchtao.wpaper.service.CommentService;
import com.github.churchtao.wpaper.service.UserService;
import com.github.churchtao.wpaper.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taojiacheng
 * @create 2018-04-24 17:51
 **/
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userDAO;

    @RequestMapping(value = "/comment/publish",method = RequestMethod.POST)
    public RestResponse publishComment(@RequestBody RequestObj requestObj){
        return RestResponse.ok(commentService.addComment(requestObj.getPostId(),requestObj.getParentId(),requestObj.getUserId(),requestObj.getContent()),200,"评论成功");
    }

    @RequestMapping(value = "/comment/delete",method = RequestMethod.GET)
    public RestResponse delete(@RequestParam(name = "commentId") Integer commentId){
        return RestResponse.ok(commentService.delComment(commentId),200,"删除成功");
    }

    @RequestMapping(value = "/comment/getByUid",method = RequestMethod.GET)
    public RestResponse getByUid(@RequestParam(name = "userId") Integer userId,
                                 @RequestParam(name = "pageNum") Integer pageNum,
                                 @RequestParam(name = "pageSize") Integer pageSize){
        return RestResponse.ok(commentService.findCommentByUserId(pageNum,pageSize,userId),200,"获取成功");
    }

    @RequestMapping(value = "/comment/getByPostId",method = RequestMethod.GET)
    public RestResponse getByPostId(@RequestParam(name = "postId") Integer postId,
                                    @RequestParam(name = "pageNum") Integer pageNum,
                                    @RequestParam(name = "pageSize") Integer pageSize){
        Page<Comment> comments = commentService.findCommentByPostId(pageNum,pageSize,postId);
        List<CommentDTO> commentDTOs = new ArrayList<>(comments.getSize());
        comments.forEach(one->{
            User author = userDAO.findById(one.getUserId()).get();
            User parent=null;
            if (one.getParentId()!=0){
                parent = userDAO.findById(one.getParentId()).get();
            }
            CommentDTO commentDTO = new CommentDTO(author.getNickname(),parent==null?null:parent.getNickname(),one.getContent(),one.getUserId(),one.getCreateTime(),author.getAvatar());
            commentDTOs.add(commentDTO);
        });
        return RestResponse.ok(PageObjectDTO.init(comments.getTotalElements(),comments.getTotalPages(),commentDTOs),200,"获取成功");
    }
}

class RequestObj{
    private String content;

    private Integer userId;

    private Integer postId;

    private Integer parentId;

    public RequestObj() {
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
