package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.entity.Profession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProfessionRepository extends JpaRepository<Profession,Integer> {

    Profession getByName(String name);

    @Query(value = "SELECT * from profession WHERE id in (select pu.profession_id from profession_user pu where pu.user_id=?1 and status=1)",nativeQuery = true)
    Page<Profession> findByUserFocus(Integer uid, Pageable pageable);

    @Query(value = "SELECT * from profession WHERE id in (select pu.profession_id from profession_user pu where pu.user_id=?1 and status=1)",nativeQuery = true)
    List<Profession> findAllByUserFocus(Integer uid);

    @Query(value = "SELECT * from profession WHERE id not in (select pu.profession_id from profession_user pu where pu.user_id=?1 and status=1)",nativeQuery = true)
    Page<Profession> findProfessionByNotFocus(Integer uid,Pageable pageable);

    @Query(value = "insert into profession_user(user_id, profession_id,status) value(?1,?2,1)", nativeQuery = true)
    @Modifying
    void insertUserProfession(Integer uid,Integer professionId);

    @Query(value = "SELECT status FROM profession_user WHERE user_id=?1 AND profession_id=?2", nativeQuery = true)
    Map selectCombine(Integer userId, Integer professionId);

    @Query(value = "update profession_user set status=?1 where profession_id=?2 AND user_id=?3", nativeQuery = true)
    @Modifying
    void updateUserProfession(Integer status,Integer professionId,Integer userId);
}
