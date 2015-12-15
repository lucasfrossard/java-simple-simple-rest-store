package com.avenuecode.app.service;

import java.util.Collection;

import com.avenuecode.app.entities.Product;

public interface ProductService {
	
	public Collection<Product> list();

	public void create(String name);
}
