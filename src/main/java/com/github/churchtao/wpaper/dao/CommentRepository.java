package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Page<Comment> findByPostIdAndStatus(Integer postId, Integer status, Pageable pageable);

    Page<Comment> findByUserIdAndStatus(Integer userId,Integer status,Pageable pageable);

}
