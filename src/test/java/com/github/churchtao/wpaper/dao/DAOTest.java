package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.dao.util.PageableTools;
import com.github.churchtao.wpaper.entity.Post;
import com.github.churchtao.wpaper.entity.User;
import com.github.churchtao.wpaper.util.CodecUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author taojiacheng
 * @create 2018-05-30 13:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DAOTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LogsRepository logsRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfessionRepository professionRepository;
    @Test
    public void daoTest(){
        System.out.println(userRepository.findAll());
        System.out.println(albumRepository.findAll());
        System.out.println(commentRepository.findAll());
        System.out.println(logsRepository.findById(40));
        System.out.println(postRepository.findById(15));
        System.out.println(professionRepository.findAll());
    }

    @Test
    public void pageTest(){
        logsRepository.findByUserId(16, PageableTools.noSortPage(2,10)).forEach(one->{
            System.out.println(one.getData());
        });
    }

    @Test
    public void sqlTest(){
        postRepository.findPostByProfessionId(1,PageableTools.noSortPage(1,10)).forEach(one->{
            System.out.println(one.getTitle());
        });
    }

    @Test
    public void getTest(){
        System.out.println(professionRepository.getByName("Androidx")==null);
    }
//    @Test
//    public void oneToManyTest(){
//
//        User user = userRepository.findById(17).get();
//        Set posts = user.getPosts();
//        Post post = new Post();
//        post.setContent("#####");
//        post.setKind(1);
//        post.setReadNum(0);
//        post.setLikeNum(0);
//        post.setKindName("Android");
//        post.setTitle("测试插入");
//        post.setUserName(user.getLoginName());
//        post.setUuid(CodecUtil.createUUID());
//        post.setCreateTime(new Date());
//        post.setUpdateTime(new Date());
//        post.setStarNum(0);
//        post.setStatus(1);
//        post.setCommentNum(0);
//        post.setUser(user);
//        posts.add(post);
//        user.setPosts(posts);
//        User user1 = userRepository.save(user);
//    }

}
