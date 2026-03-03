package model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private String firstName;
	private String lastName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	// default constructor

	public User() {

	}

	// full constructor user for registration
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;

	}

	// login constructor for authentication
	public User(String email, String password) {
		this.email = email;
		this.password = password;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;

		if (email == null) {
			return other.email == null;
		}

		return email.equals(other.email);
	}

	@Override
	public int hashCode() {
		return (email == null) ? 0 : email.hashCode();
	}

	public static void main(String[] args) {
		System.out.println("=== USER TEST ===");

		User user1 = new User("John", "Doe", "test@email.com", "pass123");
		User user2 = new User("Jane", "Smith", "test@email.com", "differentPass");
		User user3 = new User("John", "Doe", "other@email.com", "pass123");

		// equals() test
		System.out.println("user1 equals user2 (same email): " + user1.equals(user2));
		System.out.println("user1 equals user3 (different email: " + user1.equals(user3));

		// hashCode() test
		System.out.println("user1 hashCode: " + user1.hashCode());
		System.out.println("email hashCode: " + user1.getEmail().hashCode());

		System.out.println("hashCode match: " + (user1.hashCode() == user1.getEmail().hashCode()));

	}

}
