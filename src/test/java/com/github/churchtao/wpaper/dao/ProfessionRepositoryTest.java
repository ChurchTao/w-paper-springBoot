package com.github.churchtao.wpaper.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfessionRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfessionRepository professionRepository;

    @Test
    public void getByName() throws Exception {
    }

    @Test
    public void findByUserFocus() throws Exception {
    }

    @Test
    public void findAllByUserFocus() throws Exception {
    }

    @Test
    public void findProfessionByNotFocus() throws Exception {
    }

    @Test
    public void insertUserProfession() throws Exception {
    }

    @Test
    public void selectCombine() throws Exception {
        System.out.println(professionRepository.selectCombine(16,1).get("status"));

    }

    @Test
    public void updateUserProfession() throws Exception {
    }

}