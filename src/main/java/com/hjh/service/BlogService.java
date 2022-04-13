package com.hjh.service;

import com.hjh.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by 洪锦辉
 * 2021/8/12 14:42
 */
public interface BlogService {
    Blog saveBlog(Blog blog);
    void deleteBlog(Long id);
    Blog getBlog(Long id);
    Blog MarkdownToHtml(Long id);
    Page<Blog> listBlog(Pageable pageable, Blog blog);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(Pageable pageable, Long tagId);
    List<Blog> listRecommendTopBlog(int size);
    Map<String, List<Blog>> archiveBlog();
    Long blogCount();
}
