package com.hjh.dao;

import com.hjh.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

/**
 * Created by 洪锦辉
 * 2021/8/11 14:48
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    @Query("select t from Type as t")
    List<Type> findTop(Pageable pageable);
}
