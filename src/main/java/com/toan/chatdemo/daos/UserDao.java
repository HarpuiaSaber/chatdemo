package com.toan.chatdemo.daos;

import com.toan.chatdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>, CustomDao<User> {
}
