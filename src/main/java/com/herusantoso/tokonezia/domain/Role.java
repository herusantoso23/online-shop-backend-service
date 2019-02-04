package com.herusantoso.tokonezia.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="roles")
public class Role extends BaseDomain {
	
	private String name;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}
}
