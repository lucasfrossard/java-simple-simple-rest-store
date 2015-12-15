package com.avenuecode.persistence;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.app.entities.Product;
import com.avenuecode.app.entities.ShoppingList;


@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration({ "classpath:/testDataSourceContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShoppingListDAOImplTest {

	private static final String TENIS = "Tenis";

	private static final String BOTA = "Bota";

	private static final String BOLSA = "Bolsa";

	private long idOrder, idTenis;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ShopDAO orderDAO;
	
	@Before
	public void setUp() throws Exception {
		Product bota = Product.builder().name(BOTA).build();
		Product bolsa = Product.builder().name(BOLSA).build();
		Product tenis = Product.builder().name(TENIS).build();
		productDAO.insertOrUpdate(bolsa);
		productDAO.insertOrUpdate(bota);
		idTenis = productDAO.insertOrUpdate(tenis);
		Set<Product> products = new HashSet<>();
		products.add(bolsa);
		products.add(bota);
		
		ShoppingList order = new ShoppingList();
		order.setProducts(products);
		idOrder = this.orderDAO.insertOrUpdate(order);
	}

	@Test
	public void testFindById() {
		ShoppingList found = orderDAO.findByID(this.idOrder, ShoppingList.class);
		Assertions.assertThat(found).isNotNull();
		Assertions.assertThat(found.getProducts()).hasSize(2);
	}
	
	@Test
	public void modifyOrder() {
		// search for an order
		ShoppingList found = orderDAO.findByID(this.idOrder, ShoppingList.class);
		Set<Product> products = found.getProducts();
		// add a product
		Product tenis = productDAO.findByID(idTenis, Product.class);
		products.add(tenis);
		// update that order
		orderDAO.insertOrUpdate(found);
		// search for it again
		found = orderDAO.findByID(this.idOrder, ShoppingList.class);
		// check if it has more products
		Assertions.assertThat(found).isNotNull();
		Assertions.assertThat(products).hasSize(3);
	}

}
