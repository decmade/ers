package com.revature.ers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.ers.entities.ReimbursementType;

@Repository
public interface ReimbursementTypeRepositoryInterface extends JpaRepository<ReimbursementType, Long> {

}
