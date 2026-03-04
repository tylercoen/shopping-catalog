package model;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imgAddress;
	private String name;
	private int price;

	public String getImgAddress() {
		return imgAddress;
	}

	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// default constructor
	public CartItem() {
	};

	// parameterized constructor
	public CartItem(String name, int price, String imgAddress) {
		this.name = name;
		this.price = price;
		this.imgAddress = imgAddress;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CartItem))
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "CartItem{" + "name = " + name + ", price = " + price + ", imgAddress = " + imgAddress + "}";
	}

	public static void main(String[] args) {
		System.out.println("=== CART ITEM TESTS ===");

		CartItem item1 = new CartItem("Laptop", 1000, "img1.jpg");
		CartItem item2 = new CartItem("Laptop", 1200, "img2.jpg");
		CartItem item3 = new CartItem("Phone", 600, "img3.jpg");

		System.out.println("item1 equals item2 (same name): " + item1.equals(item2));
		System.out.println("item1 equals item3 (different name): " + item1.equals(item3));

		System.out.println("item1 hashCode: " + item1.hashCode());
		System.out.println("name hashCode: " + item1.getName().hashCode());

		System.out.println("hashCode match: " + (item1.hashCode() == item1.getName().hashCode()));

	}

}
