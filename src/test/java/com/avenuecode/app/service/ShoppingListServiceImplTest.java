package com.avenuecode.app.service;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.print.attribute.standard.PDLOverrideSupported;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.app.pojo.ShoppingListDetail;
import com.avenuecode.persistence.ProductDAO;
import com.avenuecode.persistence.ProductTestUtil;

@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration({ "classpath:/testDataSourceContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShoppingListServiceImplTest {

	@Autowired
	private ProductDAO productDAO;
	
	@Inject
	private ShoppingListService shoppingListService;

	private long[] productsId = new long[10];
	
	@Before
	public void setUp (){
		ProductTestUtil ptu = new ProductTestUtil(productDAO);
		int i=0;
		productsId[i++] = ptu.persistProduct("Bola");
		productsId[i++] = ptu.persistProduct("Cesta");
	}
	
	
	@Test
	public void test() {		
		long place = this.shoppingListService.place(productsId);
		ShoppingListDetail details = this.shoppingListService.details(place);
		Assertions.assertThat(details).isNotNull();
		Assertions.assertThat(details.getOrderId()).isEqualTo(place);
		Assertions.assertThat(details.getItemQty()).isEqualTo(1);
		Assertions.assertThat(details.getNames()).isNotNull();
		Assertions.assertThat(details.getNames().size()).isEqualTo(2);
		System.out.println(">>>>>>>>>>>>"+place);	}

}
