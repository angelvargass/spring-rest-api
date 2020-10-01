package com.example.springrestapi.service;

import com.example.springrestapi.dao.UserDao;
import com.example.springrestapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Qualifier("postgres")
    @Autowired
    private final UserDao userDao;

    public UserService(@Qualifier("postgres") UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.insert(user);
    }

    public List<User> retrieveAll() {
        return userDao.retrieveAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.retrieveById(id);
    }

    public int deleteUser(UUID id) {
        return userDao.deleteById(id);
    }

    public int updateUser(User user) {
        return userDao.updateById(user);
    }
}
