package com.hjh.service;

import com.hjh.pojo.User;

/**
 * Created by 洪锦辉
 * 2021/8/10 14:59
 */
public interface UserService {

    User checkUser(String username, String password);
}
