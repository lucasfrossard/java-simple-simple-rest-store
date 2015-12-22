package com.avenuecode.app.service;

import java.util.Collection;

import com.avenuecode.app.entities.Product;

/**
 * Interface for handling products
 * 
 * @author lucasf
 *
 */
public interface ProductService {

	/**
	 * List all products registered in the system.
	 * 
	 * @return The list of the products registered in the system
	 */
	public Collection<Product> list();

	/**
	 * Create a product with the given name
	 * 
	 * @param name
	 *            the name of the product
	 * @return the new product id
	 */
	public long create(String name);

	/**
	 * Retrieve a given product of the system
	 * 
	 * @param id
	 *            the id of the product
	 * @return the product id
	 */
	public Product retrieve(long id);
}
