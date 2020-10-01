package com.example.springrestapi.dao;

import com.example.springrestapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("memoryDao")
public class MemoryUserDaoService implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insert(UUID id, User user) {
        user.setId(id);
        DB.add(user);
        return 0;
    }

    @Override
    public List<User> retrieveAll() {
        return DB;
    }

    @Override
    public Optional<User> retrieveById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<User> DBUser = retrieveById(id);
        if (!DBUser.isPresent()) {
            System.out.println("Is not present");
            return 0;
        }

        DB.remove(DBUser.get());

        return 1;
    }

    @Override
    public int updateById(User user) {
        return retrieveById(user.getId())
                .map(u -> {
                    int userIndex = DB.indexOf(u);
                    DB.set(userIndex, user);
                    return 1;
                })
                .orElse(0);
    }
}
