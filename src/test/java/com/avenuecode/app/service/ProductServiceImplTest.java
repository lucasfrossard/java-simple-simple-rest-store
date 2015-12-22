package com.avenuecode.app.service;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
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
import com.avenuecode.persistence.ProductDAO;

/**
 * Test class for the ProductService implementation
 * 
 * @author lucasf
 *
 */
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration({ "classpath:/testDataSourceContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductServiceImplTest {

	private static final String FANTA = "Fanta";

	private static final String SPRITE = "Sprite";

	private static final String COCA = "Coca";

	@Autowired
	private ProductDAO productDAO;

	@Inject
	private ProductService productService;

	private long cocaId;

	@Before
	public void setUp() {
		Product coca = Product.builder().name(COCA).build();
		Product sprite = Product.builder().name(SPRITE).build();
		cocaId = this.productDAO.insertOrUpdate(coca);
		this.productDAO.insertOrUpdate(sprite);
	}

	@Test
	public void testList() {
		Collection<Product> products = this.productService.list();
		Collection<String> collect = CollectionUtils.collect(products, new BeanToPropertyValueTransformer("name"));
		Assertions.assertThat(collect).contains(COCA);
		Assertions.assertThat(collect).contains(SPRITE);
	}

	@Test
	public void testCreate() {
		long fantaId = this.productService.create(FANTA);
		Product foundFanta = this.productDAO.findByID(fantaId, Product.class);
		Assertions.assertThat(foundFanta).isNotNull();
		Assertions.assertThat(foundFanta.getId()).isEqualTo(fantaId);
	}

	@Test
	public void testRetrieve() {
		Product retrieve = this.productService.retrieve(this.cocaId);
		Assertions.assertThat(retrieve).isNotNull();
		Assertions.assertThat(COCA).isEqualTo(retrieve.getName());

	}

}
