package com.hjh.service.serviceImpl;

import com.hjh.dao.TagRepository;
import com.hjh.pojo.Blog;
import com.hjh.pojo.Tag;
import com.hjh.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 洪锦辉
 * 2021/8/11 16:14
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Tag updateTag(Long id, Tag tag) {
        Tag newTag = tagRepository.getById(id);
        BeanUtils.copyProperties(newTag, tag);
        return newTag;
    }

    @Override
    @Transactional
    public Tag getTag(Long id) {
        return tagRepository.getById(id);
    }

    @Override
    @Transactional
    public Page<Tag> listTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public List<Tag> listTags(String tagIds) {
        String[] tags = tagIds.split(",");
        List<Tag> res = new ArrayList<>();
        for (String tag : tags) {
            res.add(tagRepository.getById(Long.parseLong(tag)));
        }
        return res;
    }

    @Transactional
    @Override
    public List<Tag> listTagTop(int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    @Override
    @Transactional
    public String getTagsId(Blog blog) {
        List<Tag> tags = blog.getTags();
        String tagsId = "";
        for (Tag tag : tags) {
            tagsId += String.valueOf(tag.getId()) + ",";
        }
        return tagsId.substring(0, tagsId.length()-1);
    }
}

