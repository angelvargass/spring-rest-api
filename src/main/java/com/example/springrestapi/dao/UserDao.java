package com.example.springrestapi.dao;

import com.example.springrestapi.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    default int insert(User user) {
        UUID id = UUID.randomUUID();
        return insert(id, user);
    }

    int insert(UUID id, User user);

    List<User> retrieveAll();

    Optional<User> retrieveById(UUID id);

    int deleteById(UUID id);

    int updateById(User user);
}
