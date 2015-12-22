package com.avenuecode.persistence;

import java.util.Collection;

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

@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration({ "classpath:/testDataSourceContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductDAOImplTest {

	private static final String BOTA = "Bota";

	private static final String BOLSA = "Bolsa";

	@Autowired
	private ProductDAO productDAO;

	private Product bota, bolsa;
	
	@Before
	public void setup() {
		ProductTestUtil ptu = new ProductTestUtil(this.productDAO) ;
		this.bolsa = ptu.persistProduct(BOLSA);
		this.bota = ptu.persistProduct(BOTA);
	}

	@Test
	public void testFindBolsa() {
		Product found = productDAO.findByID(this.bolsa.getId(), Product.class);
		Assertions.assertThat(found).isNotNull();
		Assertions.assertThat(found.getName()).isEqualTo(BOLSA);
	}
	
	@Test
	public void testFindBota() {
		Product found = productDAO.findByID(this.bota.getId(), Product.class);
		Assertions.assertThat(found).isNotNull();
		Assertions.assertThat(found.getName()).isEqualTo(BOTA);
	}
	
	@Test
	public void testAll() {
		Collection<Product> products = productDAO.retrieveAll(Product.class);
		Assertions.assertThat(products).isNotNull();
		Assertions.assertThat(products).hasSize(2);
	}

}
