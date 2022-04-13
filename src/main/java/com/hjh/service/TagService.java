package com.hjh.service;

import com.hjh.pojo.Blog;
import com.hjh.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 洪锦辉
 * 2021/8/11 16:11
 */
public interface TagService {
    Tag saveTag(Tag tag);
    void deleteTag(Long id);
    Tag updateTag(Long id, Tag tag);
    Tag getTag(Long id);
    Page<Tag> listTags(Pageable pageable);
    List<Tag> listTags();
    List<Tag> listTags(String tagIds);
    List<Tag> listTagTop(int size);
    String getTagsId(Blog blog);
}
