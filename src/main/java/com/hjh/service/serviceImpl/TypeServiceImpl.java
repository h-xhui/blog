package com.hjh.service.serviceImpl;

import com.hjh.dao.TypeRepository;
import com.hjh.pojo.Type;
import com.hjh.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.peer.TextAreaPeer;
import java.util.List;
import java.util.Optional;

/**
 * Created by 洪锦辉
 * 2021/8/11 14:46
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        if (type.getId() != null) {
            return typeRepository.save(type);
        }
        List<Type> lists = listTypes();
        boolean exit = false;
        for (Type t : lists) {
            if (t.getName().equals(type.getName())) {
                exit = true;
                break;
            }
        }
        return exit==false? typeRepository.save(type) : null;
    }

    @Transactional
    @Override
    public Type getType(Long id) {
       return typeRepository.getById(id);
    }

    @Transactional
    @Override
    public Page<Type> listTypes(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> listTypes() {
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public List<Type> listTypeTop(int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type Type) {
        Type newType = typeRepository.getById(id);
        BeanUtils.copyProperties(newType, Type);
        return newType;
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}

