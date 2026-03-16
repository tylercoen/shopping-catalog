package model;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
	private Map<String, Map<CartItem, Integer>> userCarts;

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
		return userCarts.get(email);

	}

	public void addToCart(String email, CartItem item) {
		Map<CartItem, Integer> cart = userCarts.get(email);

		if (cart == null) {
			cart = new HashMap<>();
			userCarts.put(email, cart);
		}
		int quantity = cart.getOrDefault(item, 0);

		cart.put(item, quantity + 1);
	}

	public static void main(String[] args) {
		CartManager manager = new CartManager();

		String email = "test@example.com";

		CartItem laptop = new CartItem("Laptop", 1000, "img1.jpg");
		CartItem phone = new CartItem("Phone", 600, "img2.jpg");

		manager.addToCart(email, laptop);
		manager.addToCart(email, laptop); // duplicate
		manager.addToCart(email, phone);

		Map<CartItem, Integer> cart = manager.getUserCart(email);
		cart.forEach((item, qty) -> System.out.println(item.getName() + ": " + qty));

	}

}
