package com.ob.springexercise4.service;


import com.ob.springexercise4.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User update(User user, Long userId);

    void delete(Long userId);

    User findByID(Long userId);

    List<User> findAll();
}
