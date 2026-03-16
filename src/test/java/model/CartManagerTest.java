package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CartManagerTest {

	private CartManager cartManager;

	@Before
	public void setUp() throws Exception {
		cartManager = new CartManager();
	}

	@Test
	public void testAddToCartNewItem() {
		CartItem item = new CartItem("Laptop", 999.99, "images/laptop.png");

		cartManager.addToCart("test@email.com", item);

		Map<CartItem, Integer> cart = cartManager.getUserCart("test@email.com");

		assertNotNull(cart);
		assertEquals(1, cart.size());
		assertEquals(Integer.valueOf(1), cart.get(item));
	}

	@Test
	public void testAddToCartIncreaseQuantity() {
		CartItem item = new CartItem("Laptop", 999.99, "images/laptop.png");

		cartManager.addToCart("test@email.com", item);
		cartManager.addToCart("test@email.com", item);

		Map<CartItem, Integer> cart = cartManager.getUserCart("test@email.com");

		assertEquals(Integer.valueOf(2), cart.get(item));

	}

	@Test
	public void testGetUserCartWhenEmpty() {
		Map<CartItem, Integer> cart = cartManager.getUserCart("unknown@email.com");

		assertNull(cart);
	}

	@Test
	public void testGetUserCarts() {
		CartItem item = new CartItem("Laptop", 999.99, "images/laptop.png");

		cartManager.addToCart("test@email.com", item);

		Map<String, Map<CartItem, Integer>> carts = cartManager.getUserCarts();

		assertEquals(1, carts.size());
	}

}
