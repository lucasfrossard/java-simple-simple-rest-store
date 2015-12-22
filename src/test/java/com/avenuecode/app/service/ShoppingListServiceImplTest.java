package com.avenuecode.app.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

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
import com.avenuecode.app.pojo.ShoppingListDetail;
import com.avenuecode.persistence.ProductDAO;
import com.avenuecode.persistence.ProductTestUtil;

@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration({ "classpath:/testDataSourceContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShoppingListServiceImplTest {

	private static final String BEIRUTE = "Beirute";

	private static final String BASKET = "Cesta";

	private static final String BALL = "Bola";

	private static final String PEN = "Caneta";
	
	private static final String PHONE = "Celular";

	@Autowired
	private ProductDAO productDAO;

	@Inject
	private ShoppingListService shoppingListService;

	private Collection<Product> products = new ArrayList<>();

	private Collection<Product> someOtherproducts = new ArrayList<>();

	@Before
	public void setUp() {
		ProductTestUtil ptu = new ProductTestUtil(productDAO);
		products.add(ptu.persistProduct(BALL));
		products.add(ptu.persistProduct(BASKET));
		someOtherproducts.add(ptu.persistProduct(PHONE));
		someOtherproducts.add(ptu.persistProduct(PEN));
		someOtherproducts.add(ptu.persistProduct(BEIRUTE));
	}

	@Test
	public void testPlaceAndDetails() {
		long placeId = this.shoppingListService.place(products);
		ShoppingListDetail details = this.shoppingListService.details(placeId);
		Assertions.assertThat(details).isNotNull();
		Assertions.assertThat(details.getOrderId()).isEqualTo(placeId);
		Assertions.assertThat(details.getItemQty()).isEqualTo(2);
		Assertions.assertThat(details.getNames()).isNotNull();
		Assertions.assertThat(details.getNames().size()).isEqualTo(2);
		System.out.println(">>>>>>>>>>>>" + placeId);
	}

	@Test
	public void testModify(){
		// save an order with the default products
		long placeId = this.shoppingListService.place(products);
		// get details and verify if things, to check later if they really changed
		ShoppingListDetail details = this.shoppingListService.details(placeId);
		Assertions.assertThat(details).isNotNull();
		Assertions.assertThat(details.getOrderId()).isEqualTo(placeId);
		Assertions.assertThat(details.getItemQty()).isEqualTo(2);
		Assertions.assertThat(details.getNames()).isNotNull();
		Assertions.assertThat(details.getNames().size()).isEqualTo(2);
		details.getNames().contains(BALL);
		details.getNames().contains(BASKET);
		
		// modify that order to contains this other products
		this.shoppingListService.modify(placeId, someOtherproducts);
		// get details to verify if the order has been modified
		details = this.shoppingListService.details(placeId);
		Assertions.assertThat(details).isNotNull();
		Assertions.assertThat(details.getOrderId()).isEqualTo(placeId);
		Assertions.assertThat(details.getItemQty()).isEqualTo(3);
		Assertions.assertThat(details.getNames()).isNotNull();
		Assertions.assertThat(details.getNames().size()).isEqualTo(3);
		details.getNames().contains(PHONE);
		details.getNames().contains(PEN);
		details.getNames().contains(BEIRUTE);

		
		// long orderId, Collection<Product> products
	}

	// public Collection<ShoppingList> list();

}
