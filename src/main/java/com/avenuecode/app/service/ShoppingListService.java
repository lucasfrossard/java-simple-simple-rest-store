package com.avenuecode.app.service;

import java.util.Collection;

import com.avenuecode.app.entities.ShoppingList;
import com.avenuecode.app.pojo.ShoppingListDetail;

public interface ShoppingListService {

	public long place(long[] productsId);

	public void modify(long orderId, long[] productsId);

	public Collection<ShoppingList> list();
	
	public ShoppingListDetail details (long id);

}
