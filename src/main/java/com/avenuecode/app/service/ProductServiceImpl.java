package com.avenuecode.app.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.app.entities.Product;
import com.avenuecode.persistence.ProductDAO;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	public Collection<Product> list (){
		return this.productDAO.retrieveAll(Product.class);
	}

	@Override
	public long create(String name) {
		Product product = new Product(0, name);
		long id = this.productDAO.insertOrUpdate(product);
		product.setId(id);
		return id;
	}

	@Override
	public Product retrieve(long id) {
		return this.productDAO.findByID(id, Product.class);
	}
}
