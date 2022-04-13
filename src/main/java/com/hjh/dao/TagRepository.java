package com.hjh.dao;

import com.hjh.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 洪锦辉
 * 2021/8/11 16:16
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
