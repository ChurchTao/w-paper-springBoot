package com.github.churchtao.wpaper.dao;

import com.github.churchtao.wpaper.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 78663
 */
public interface AlbumRepository extends JpaRepository<Album,Integer> {
}
