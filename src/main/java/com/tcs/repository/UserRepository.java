package com.tcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.binding.UserForm;
import com.tcs.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserEmailAndPassword(String userEmail, String userPassword);

	public User findByUserEmail(String userEmail);
}
