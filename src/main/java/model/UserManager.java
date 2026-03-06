package model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {

	// key = email
	private Map<String, User> users = new HashMap<>();

	// register a new user

	private boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	public void registerUser(User userToRegister) {

		// check for null object
		if (userToRegister == null) {
			throw new InvalidParameterException("User cannot be null.");
		}

		// validate fields
		if (isNullOrEmpty(userToRegister.getEmail()) || isNullOrEmpty(userToRegister.getPassword())
				|| isNullOrEmpty(userToRegister.getFirstName()) || isNullOrEmpty(userToRegister.getLastName())) {
			throw new InvalidParameterException("All user fields must be non-null and non-empty");
		}

		// Check if already registered
		if (users.containsKey(userToRegister.getEmail())) {
			throw new IllegalStateException("User is already registered");
		}

		users.put(userToRegister.getEmail(), userToRegister);

	}

	// authenticate user
	public boolean authenticate(String email, String password) {
		User user = users.get(email);
		if (user == null) {
			return false;
		}
		return user.getPassword().equals(password);
	}

	// retrieve all users
	public List<User> getUsers() {
		return new ArrayList<>(users.values());
	}

	// retrieve user by email
	public User getUser(String email) {
		return users.get(email);
	}

	// for testing/debugging
	public int getUserCount() {
		return users.size();
	}

	// default constructor
	public UserManager() {
		this.users = new HashMap<>();
	}

	// constructor used when loading from database
	public UserManager(Map<String, User> users) {
		this.users = users;
	}

	public static void main(String[] args) {
		UserManager manager = new UserManager();

		User validUser = new User("Iyo", "Sky", "iyo@wwe.com", "pass123");

		manager.registerUser(validUser);
		System.out.println("User registered successfully");

		// try duplicate
		manager.registerUser(validUser); // should throw IllegalStateException
	}
}
