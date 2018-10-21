package com.revature.ers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.ers.entities.Reimbursement;

@Repository
public interface ReimbursementRepositoryInterface extends JpaRepository<Reimbursement, Long> {

}
