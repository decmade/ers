package com.revature.ers.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement_statuses")
public class ReimbursementStatus extends AbstractEntity {
	
	@Column(length = 50)
	public String description;
}
