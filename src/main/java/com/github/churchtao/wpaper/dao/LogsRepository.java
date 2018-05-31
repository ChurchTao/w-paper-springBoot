package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.entity.Logs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs,Integer> {

    Page<Logs> findByUserId(Integer userId, Pageable pageable);

}
