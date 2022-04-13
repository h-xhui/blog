package com.hjh.dao;

import com.hjh.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 洪锦辉
 * 2021/8/12 14:52
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    @Query("select b from Blog b where b.isRecommend = true and b.isPublish = true")
    List<Blog> findRecommendTop(Pageable pageable);

    @Query(value = "select date_format(b.update_time, '%Y') as year from t_blog b group by year order by year desc", nativeQuery = true)
    List<String> findGroupYear();

    @Query(value = "select b from Blog b where function('date_format', b.updateTime, '%Y') = ?1 order by b.updateTime desc")
    List<Blog> findBlogByYear(String year);
}
