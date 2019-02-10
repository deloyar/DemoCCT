package com.example.entity;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.example.auth.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Role role;
	private String name;
	private String password;
	private boolean status;

	public User() {
	}

	public User(Integer userId) {
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status")
	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}