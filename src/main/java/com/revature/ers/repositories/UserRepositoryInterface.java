package com.revature.ers.repositories;

import org.springframework.stereotype.Repository;

import com.revature.ers.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long> {

}
