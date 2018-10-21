package com.revature.ers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.ers.entities.Receipt;

@Repository
public interface ReceiptRepositoryInterface extends JpaRepository<Receipt, Long> {

}
