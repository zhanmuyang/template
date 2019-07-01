package com.bytebpm.user.dao;

import com.bytebpm.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    long save(User user);

    void add(List<User> users);

    List<User> findByIds(Integer[] ids);
    List<User> findAll();

    List<User> find(User user);
}
