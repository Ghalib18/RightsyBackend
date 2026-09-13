package com.Rightsy.demo.repository;

import com.Rightsy.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
