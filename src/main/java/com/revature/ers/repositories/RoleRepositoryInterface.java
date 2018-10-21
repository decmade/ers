package com.revature.ers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.ers.entities.Role;

@Repository
public interface RoleRepositoryInterface extends JpaRepository<Role, Long> {

}
