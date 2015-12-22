package com.avenuecode.app.service;

import java.util.Collection;

import com.avenuecode.app.entities.Product;
import com.avenuecode.app.entities.ShoppingList;
import com.avenuecode.app.pojo.ShoppingListDetail;

/**
 * Interface for handling orders on the system
 * 
 * @author lucasf
 *
 */
public interface ShoppingListService {

	/**
	 * Places an order.
	 * 
	 * @param products
	 *            products that the user is shopping. The product existence is
	 *            verified. If it does not exists, the product is discarded
	 * @return the id of the order
	 */
	public long place(Collection<Product> products);

	/**
	 * Modify an existing order
	 * 
	 * @param orderId
	 *            the current order id
	 * @param products
	 *            list of products for that list. The previous list is
	 *            discarded.
	 */
	public void modify(long orderId, Collection<Product> products);

	/**
	 * List all the orders in the system
	 * 
	 * @return all the orders within the system
	 */
	public Collection<ShoppingList> list();

	/**
	 * Details of a diven order
	 * 
	 * @param id
	 * @return details of a given order
	 */
	public ShoppingListDetail details(long id);

}
