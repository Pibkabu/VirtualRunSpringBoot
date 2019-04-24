package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserAccount;

public interface UserAccountRespository extends JpaRepository<UserAccount, Integer>{
	@Query(value = "SELECT * FROM useraccount", nativeQuery = true)
	List<UserAccount> getAllUserAccountINeed();
	
	@Query(value = "SELECT * FROM useraccount where Email = :email", nativeQuery = true)
	UserAccount getUserAccountByEmail(@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO useraccount(Email, UserPassword) VALUES(:email,:password)", nativeQuery = true)
	void addAnotherUserAccount(@Param("email") String email, @Param("password") String password);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO useraccount(Email, UserPassword) VALUES(:email,:password)", nativeQuery = true)
	UserAccount addAnotherUserAccount2(@Param("email") String email, @Param("password") String password);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE useraccount set UserPassword = :password where Email = :email", nativeQuery = true)
	UserAccount changePassword(@Param("email") String email, @Param("password") String password);
}
