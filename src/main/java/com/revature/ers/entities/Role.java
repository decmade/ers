package com.revature.ers.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {
	
	@Column(length = 50)
	public String name;
}
