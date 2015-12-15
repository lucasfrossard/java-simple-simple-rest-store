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
import com.avenuecode.persistence.ShopDAO;

@Transactional
@Service
public class ShoppingListServiceImpl implements ShoppingListService {
	
	@Autowired
	private ShopDAO shopDAO;

	@Override
	public long place(long[] productsId) {
		ShoppingList shop = ShoppingList.builder().products(new HashSet<Product>()).build();

		addProductsToShoppingList(productsId, shop);

		return this.shopDAO.insertOrUpdate(shop);

	}

	private void addProductsToShoppingList(long[] productsId, ShoppingList shop) {
		for (long l : productsId) {
			shop.getProducts().add(Product.builder().id(l).build());
		}
	}

	@Override
	public void modify(long orderId, long[] productsId) {
		ShoppingList shop = this.shopDAO.findByID(orderId, ShoppingList.class);
		shop.setProducts(new HashSet<Product>());
		addProductsToShoppingList(productsId, shop);
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

		for (Product product : products) {
			builder.name(product.getName());
		}

		return builder.build();
	}

}
