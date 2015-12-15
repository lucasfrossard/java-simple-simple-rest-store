package com.avenuecode.persistence;

import com.avenuecode.app.entities.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductTestUtil {
	
	private ProductDAO productDAO;

	public long persistProduct(String name) {
		Product product = Product.builder().name(name).build();
		return this.productDAO.insertOrUpdate(product);
	}
}
