package com.abc.bt.modules.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_User")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User {
	@Id
	private long id;
	private String username;

	@Version
	private int version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
