package com.jobportal.jobportal.user;

public interface UserDao {
    User findByUsername(String username);

    User save(User user);
}