package com.avenuecode.app.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.app.entities.Product;
import com.avenuecode.app.entities.ShoppingList;
import com.avenuecode.app.pojo.ShoppingListDetail;
import com.avenuecode.app.pojo.ShoppingListDetail.ShoppingListDetailBuilder;
import com.avenuecode.persistence.ProductDAO;
import com.avenuecode.persistence.ShopDAO;

/**
 * Shopping list service implementation
 * 
 * @author lucasf@ciandt.com
 *
 */
@Transactional
@Service
public class ShoppingListServiceImpl implements ShoppingListService {

	@Autowired
	private ShopDAO shopDAO;

	@Autowired
	private ProductDAO productDAO;

	@Override
	public long place(Collection<Product> products) {
		ShoppingList shop = ShoppingList.builder().products(new HashSet<Product>()).build();

		// validate fi products exists
		for (Product product : products) {
			Product found = this.productDAO.findByID(product.getId(), Product.class);
			if (found != null) {
				addProductsToShoppingList(products, shop);
			}
		}

		return this.shopDAO.insertOrUpdate(shop);

	}

	private void addProductsToShoppingList(Collection<Product> products, ShoppingList shop) {
		for (Product l : products) {
			shop.getProducts().add(l);
		}
	}

	@Override
	public void modify(long orderId, Collection<Product> products) {
		ShoppingList shop = this.shopDAO.findByID(orderId, ShoppingList.class);
		shop.setProducts(new HashSet<Product>());
		addProductsToShoppingList(products, shop);
		this.shopDAO.insertOrUpdate(shop);
	}

	@Override
	public Collection<ShoppingList> list() {
		Collection<ShoppingList> shoppingLists = this.shopDAO.retrieveAll(ShoppingList.class);
		return shoppingLists;
	}

	@Override
	public ShoppingListDetail details(long id) {
		ShoppingList shoppingList = this.shopDAO.findByID(id, ShoppingList.class);
		Set<Product> products = shoppingList.getProducts();

		ShoppingListDetailBuilder builder = ShoppingListDetail.builder();
		builder.itemQty(products.size());
		builder.orderId(shoppingList.getId());

		for (Product product : products) {
			builder.name(product.getName());
		}

		return builder.build();
	}

}
