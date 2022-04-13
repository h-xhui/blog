package com.hjh.service;

import com.hjh.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 洪锦辉
 * 2021/8/11 14:43
 */
public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    Page<Type> listTypes(Pageable pageable);
    List<Type> listTypes();
    List<Type> listTypeTop(int size);
    Type updateType(Long id, Type type);
    void deleteType(Long id);
}
