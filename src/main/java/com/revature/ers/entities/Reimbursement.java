package com.revature.ers.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursements")
public class Reimbursement extends AbstractEntity {
	
	@Column()
	public double amount;
	
	@Column(length = 255)
	public String description;
	
	@Column()
	public LocalDateTime submitted;
	
	@Column()
	public LocalDateTime resolved;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	public ReimbursementStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	public ReimbursementType type;
	
	@OneToMany(mappedBy = "receipt", fetch = FetchType.EAGER)
	public List<Receipt> receipts;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	public User author;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resolver_id", referencedColumnName = "id")
	public User resolver;
	
	
	public Reimbursement() {
		super();
		
		this.receipts = new ArrayList<>();
	}
}
