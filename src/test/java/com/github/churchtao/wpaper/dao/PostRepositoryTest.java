package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.dao.util.PageableTools;
import com.github.churchtao.wpaper.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Test
    public void findPostByProfessionId() throws Exception {
        postRepository.findByUserIdAndStatus(16,1, PageableTools.noSortPage(1,10)).forEach(post ->
                System.out.println(post.getTitle())
        );
    }
    @Test
    public void setPostService() throws Exception {
    }

    @Test
    public void findByKindAndStatus() throws Exception {
    }

    @Test
    public void findByUserIdAndStatus() throws Exception {
    }

    @Test
    public void insertPostProfession() throws Exception {
    }

}