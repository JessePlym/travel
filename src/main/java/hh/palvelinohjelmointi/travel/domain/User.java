package hh.palvelinohjelmointi.travel.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long userId;

	@Column(name = "username", nullable = false, updatable = false)
	private String username;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "password", nullable = false)
	private String passwordHash;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Booking> bookings;

	public User() {

	}

	public User(String username, String role, String passwordHash) {
		super();
		this.username = username;
		this.role = role;
		this.passwordHash = passwordHash;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	public String getEmail() {
		return username;
	}

	public void setEmail(String email) {
		this.username = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

}
