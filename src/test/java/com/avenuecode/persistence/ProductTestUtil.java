package com.avenuecode.persistence;

import com.avenuecode.app.entities.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductTestUtil {
	
	private ProductDAO productDAO;

	public Product persistProduct(String name) {
		Product product = Product.builder().name(name).build();
		product.setId(this.productDAO.insertOrUpdate(product));
		return product;
	}
}
