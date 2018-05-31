package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 78663
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByNickname(String nickname);

    User getByEmail(String email);

    User getByLoginName(String loginName);

    User getByPhone(String phone);

}
