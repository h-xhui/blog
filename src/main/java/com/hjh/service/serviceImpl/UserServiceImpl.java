package com.hjh.service.serviceImpl;

import com.hjh.dao.UserRepository;
import com.hjh.pojo.User;
import com.hjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 洪锦辉
 * 2021/8/10 15:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}

