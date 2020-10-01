package com.example.springrestapi.dao;

import com.example.springrestapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDaoService implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(UUID id, User user) {
        user.setId(id);
        String sql = "INSERT INTO TBL_USER(id, name, last_name, email, password)\n" +
                "VALUES(?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
          sql,
          user.getId(),
          user.getName(),
          user.getLastName(),
          user.getEmail().toLowerCase(),
          user.getPassword()
        );
    }

    @Override
    public List<User> retrieveAll() {
        String query = "SELECT * FROM TBL_USER";
        return jdbcTemplate.query(query, ((resultSet, i) -> {
            return new User(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"));
        }));
    }

    @Override
    public Optional<User> retrieveById(UUID id) {
        String query = "SELECT * FROM TBL_USER WHERE ID = ?";

        User user = jdbcTemplate.queryForObject(query, new Object[]{id}, ((resultSet, i) -> {
            return new User(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"));
        }));

        return Optional.ofNullable(user);
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "DELETE FROM TBL_USER \n"+
                    "WHERE ID = ?";
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public int updateById(User user) {
        String sql = "UPDATE TBL_USER\n" +
                "SET name = ?, last_name = ?, email = ?, password = ?\n" +
                "WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                user.getName(),
                user.getLastName(),
                user.getEmail().toLowerCase(),
                user.getPassword(),
                user.getId()
        );
    }
}
