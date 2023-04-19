package com.ob.springexercise4.service.impl;

import com.ob.springexercise4.entity.User;
import com.ob.springexercise4.repository.UserRepository;
import com.ob.springexercise4.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {

        Optional<User> userOpt = userRepository.findById(user.getId());

        if (userOpt.isPresent())
            throw new ServiceException("There's already an user with this id");

        return userRepository.save(user);

    }

    @Override
    public User update(User updatedUser, Long userId) {

        User user = findByID(userId);
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);

    }

    @Override
    public void delete(Long userId) {

        userRepository.delete(findByID(userId));

    }

    @Override
    public User findByID(Long userId) {

        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty())
            throw new ServiceException("User with that id does not exist");

        return userOpt.get();
    }

    @Override
    public List<User> findAll() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty())
            throw new ServiceException("The list of users is empty");

        return userRepository.findAll();

    }
}
