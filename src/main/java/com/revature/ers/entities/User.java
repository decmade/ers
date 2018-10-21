package com.revature.ers.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
	
	@Column(length = 50)
	public String identity;
	
	@Column(length = 100)
	public String password;
	
	@Column(length = 50)
	public String firstName;
	
	@Column(length = 50)
	public String lastName;
	
	@Column(length = 50)
	public String email;
	
	@Column(length = 255)
	public String secret;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	public Role role;
	
}
