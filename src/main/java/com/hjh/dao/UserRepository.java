package com.hjh.dao;

import com.hjh.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 洪锦辉
 * 2021/8/10 15:03
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
