package model;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
	private Map<String, Map<CartItem, Integer>> userCarts = new HashMap<>();

	public CartManager(Map<String, Map<CartItem, Integer>> userCarts) {
		this.userCarts = userCarts;
	}

	public CartManager() {
		this.userCarts = new HashMap<>();
	}

	public Map<String, Map<CartItem, Integer>> getUserCarts() {
		return userCarts;
	}

	public Map<CartItem, Integer> getUserCart(String email) {
		Map<CartItem, Integer> cart = userCarts.get(email);

		if (cart == null) {
			cart = new HashMap<>();
			userCarts.put(email, cart);
		}
		return cart;

	}

	public void addToCart(String email, CartItem item) {
		Map<CartItem, Integer> cart = getUserCart(email);

		cart.put(item, cart.getOrDefault(item, 0) + 1);
	}

	public static void main(String[] args) {
		CartManager manager = new CartManager();

		CartItem laptop = new CartItem("Laptop", 1000, "img1.jpg");
		CartItem phone = new CartItem("Phone", 600, "img2.jpg");

		manager.addToCart("john@email.com", laptop);
		manager.addToCart("john@email.com", laptop); // duplicate
		manager.addToCart("john@email.com", phone);

		Map<CartItem, Integer> cart = manager.getUserCart("john@email.com");
		cart.forEach((item, qty) -> System.out.println(item.getName() + ": " + qty));

	}

}
