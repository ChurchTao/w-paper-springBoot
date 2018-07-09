package com.github.churchtao.wpaper.service;


import com.github.churchtao.wpaper.constant.StatusConst;
import com.github.churchtao.wpaper.dao.PostRepository;
import com.github.churchtao.wpaper.dao.util.PageableTools;
import com.github.churchtao.wpaper.dto.PageObjectDTO;
import com.github.churchtao.wpaper.dto.PostSimpleInfoDTO;
import com.github.churchtao.wpaper.entity.Post;
import com.github.churchtao.wpaper.exception.ServerException;
import com.github.churchtao.wpaper.util.CodecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author taojiacheng
 * @create 2018-04-11 11:17
 **/
@Service
public class PostService {
    @Autowired
    private PostRepository postDAO;

    public PageObjectDTO findPostMostHot(Integer uid){
        Page<Post> posts;
        if (uid!=0){
            posts=  postDAO.findByUserIdAndStatus(uid,1, PageableTools.sortPageDesc(0,10,"readNum"));
        }else {
            posts=  postDAO.findByStatus(1, PageableTools.sortPageDesc(0,10,"readNum"));
        }
        List<PostSimpleInfoDTO> postSimpleInfoDTOS = new ArrayList<>();
        posts.forEach(one->{
            PostSimpleInfoDTO dto = new PostSimpleInfoDTO("/read/"+one.getId(),one.getTitle(),one.getUserName(),one.getKindName(),one.getKind()+"",
                    one.getReadNum(),new ArrayList<>(),one.getLikeNum(),one.getCommentNum(),one.getCreateTime());
            postSimpleInfoDTOS.add(dto);
        });
        return PageObjectDTO.init(posts.getTotalElements(),posts.getTotalPages(),postSimpleInfoDTOS);
    }

    public PageObjectDTO findUserPost(int pageNum,int pageSize,int userId){
        Page<Post> posts =  postDAO.findByUserIdAndStatus(userId,1,PageableTools.noSortPage(pageNum,pageSize));
        List<PostSimpleInfoDTO> postSimpleInfoDTOS = new ArrayList<>();
        posts.forEach(one->{
            PostSimpleInfoDTO dto = new PostSimpleInfoDTO("/read/"+one.getId(),one.getTitle(),one.getUserName(),one.getKindName(),one.getKind()+"",
                    one.getReadNum(),new ArrayList<>(),one.getLikeNum(),one.getCommentNum(),one.getCreateTime());
            postSimpleInfoDTOS.add(dto);
        });
        return PageObjectDTO.init(posts.getTotalElements(),posts.getTotalPages(),postSimpleInfoDTOS);
    }

    public Post delPost(Integer postId){
        Post post = postDAO.findById(postId).get();
        post.setStatus(StatusConst.OFF);
        return postDAO.save(post);
    }

    public Post readPost(Integer postId){
        Post post = postDAO.findById(postId).get();
        post.setReadNum(post.getReadNum()+1);
        return post;
    }

    @Transactional(rollbackFor = ServerException.class)
    public Post savePost(String title,String content,int userId ,String userName,int kind ,String kindName){
        Post post = new Post();
        Date now  = new Date();
        String uuid = CodecUtil.createUUID();
        post.setUpdateTime(now);
        post.setCreateTime(now);
        post.setUuid(uuid);
        post.setTitle(title);
        post.setContent(content);
        post.setUserId(userId);
        post.setUserName(userName);
        post.setKind(kind);
        post.setKindName(kindName);
        post.setCommentNum(0);
        post.setReadNum(0);
        post.setStarNum(0);
        post.setLikeNum(0);
        post.setStatus(StatusConst.ON);
        Post post1 =postDAO.save(post);
        postDAO.insertPostProfession(post1.getId(),kind);
        return post1;
    }

//    public boolean updatePost(int postId,String content){
//        Post post = postDAO.get(postId);
//        if (post==null) throw new ServerException(404,"不存在该文章!");
//        post.setContent(content);
//        post.setUpdatetime(new Date());
//        return postDAO.update(post);
//    }

    public PageObjectDTO findPostByPageAndKind(int pageNumber, int pageSize, int kind) {
        Page<Post> posts =  postDAO.findByKindAndStatus(kind,1,PageableTools.noSortPage(pageNumber,pageSize));
        List<PostSimpleInfoDTO> postSimpleInfoDTOS = new ArrayList<>();
        posts.forEach(one->{
            PostSimpleInfoDTO dto = new PostSimpleInfoDTO("/read/"+one.getId(),one.getTitle(),one.getUserName(),one.getKindName(),one.getKind()+"",
                    one.getReadNum(),new ArrayList<>(),one.getLikeNum(),one.getCommentNum(),one.getCreateTime());
            postSimpleInfoDTOS.add(dto);
        });
        return PageObjectDTO.init(posts.getTotalElements(),posts.getTotalPages(),postSimpleInfoDTOS);
    }

    public Post countLike (int postId, int num){
        Post post = postDAO.findById(postId).get();
        post.setLikeNum(post.getLikeNum()+num);
        return postDAO.save(post);
    }

    public Post countCommentNum (int postId,int num){
        Post post = postDAO.findById(postId).get();
        post.setCommentNum(post.getCommentNum()+num);
        return postDAO.save(post);
    }
}
