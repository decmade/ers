package com.revature.ers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.ers.entities.ReimbursementStatus;

@Repository
public interface ReimbursementStatusRepositoryInterface extends JpaRepository<ReimbursementStatus, Long> {

}
