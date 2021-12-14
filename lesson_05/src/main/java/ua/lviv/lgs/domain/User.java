package ua.lviv.lgs.domain;

import java.util.Objects;

public class User {

	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String role;
	
	public User(Integer id, String email, String firstName, String lastName, String role) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public User(String email, String firstName, String lastName, String role) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", role="
				+ role + "]";
	}

}
