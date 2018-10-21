package com.revature.ers.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "receipts")
public class Receipt extends AbstractEntity {
	
	@Column(length = 100)
	public String fileName;
	
	@Column()
	public LocalDateTime created;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reimbursement_id", referencedColumnName = "id")
	public Reimbursement reimbursement;
}
