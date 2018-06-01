package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query(value = "select * from post p where p.id in (select pp.post_id from profession_post pp where pp.profession_id=?1) and p.status=1",nativeQuery = true)
    Page<Post> findPostByProfessionId(Integer professionId, Pageable pageable);

    Post getByUuid(String uuid);

    Page<Post> findByKindAndStatus(Integer kind,Integer status,Pageable pageable);

    Page<Post> findByUserIdAndStatus(Integer userId,Integer status,Pageable pageable);

    Page<Post> findByStatus(Integer status,Pageable pageable);

    @Query(value = "insert into profession_post(post_id, profession_id,status) value(?1,?2,1)", nativeQuery = true)
    @Modifying
    public void insertPostProfession(Integer postId,Integer professionId);


}
