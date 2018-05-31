package com.github.churchtao.wpaper.service;

import com.github.churchtao.wpaper.constant.StatusConst;
import com.github.churchtao.wpaper.dao.CommentRepository;
import com.github.churchtao.wpaper.dao.util.PageableTools;
import com.github.churchtao.wpaper.entity.Comment;
import com.github.churchtao.wpaper.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @author taojiacheng
 * @create 2018-04-12 17:09
 **/
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentDAO;
    @Autowired
    private PostService postService;

    public Page<Comment> findCommentByPostId(int pageNum, int pageSize, int postId){
        return commentDAO.findByPostIdAndStatus(postId,StatusConst.ON, PageableTools.noSortPage(pageNum,pageSize));
    }

    public Page<Comment> findCommentByUserId(int pageNum,int pageSize,int userId){
        return commentDAO.findByUserIdAndStatus(userId,StatusConst.ON, PageableTools.noSortPage(pageNum,pageSize));
    }
    @Transactional(rollbackFor = ServerException.class)
    public Comment addComment(int postId, int parentId, int userId, String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setParentId(parentId);
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setStatus(StatusConst.ON);
        postService.countCommentNum(postId,1);
        return commentDAO.save(comment);
    }

    @Transactional(rollbackFor = ServerException.class)
    public Comment delComment(int commentId){
        Optional<Comment> comment = commentDAO.findById(commentId);
        if (!comment.isPresent()){
            throw new ServerException(404,"不存在该评论!");
        }
        Comment comment1 = comment.get();
        comment1.setStatus(StatusConst.OFF);
        postService.countCommentNum(comment1.getPostId(),-1);
        return commentDAO.save(comment1);
    }
}
