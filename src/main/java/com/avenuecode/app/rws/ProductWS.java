package com.avenuecode.app.rws;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.avenuecode.app.entities.Product;
import com.avenuecode.app.service.ProductService;
import com.avenuecode.util.GsonUtils;

/**
 * Class responsible for handling products
 * 
 * @author lucasfrossard@gmail.com
 *
 */
@Path("product")
@Transactional
@Controller
public class ProductWS{

	@Inject
	private ProductService productService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProduct(@PathParam("id") int id) {
		Product product = this.productService.retrieve(id);
		return GsonUtils.convertToJson(product);
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list() {

		Collection<Product> list = this.productService.list();
		return GsonUtils.convertToJson(list);
	}

	@GET
	@Path("/populate/{name}")
	public String populate(@PathParam("name") String name) {
		this.productService.create(name);
		return this.list();
	}

}
