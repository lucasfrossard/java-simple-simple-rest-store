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
import com.avenuecode.persistence.ProductDAO;
import com.google.gson.Gson;

/**
 * Class responsible for handling products
 * 
 * @author lucasfrossard@gmail.com
 *
 */
@Path("product")
@Transactional
@Controller
public class ProductWS {

	@Inject
	private ProductService productService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProduct(@PathParam("id") int id) {
		Product product = new Product(1, "Botas");
		Gson gson = new Gson();
		String json = gson.toJson(product);
		return json;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list() {

		Collection<Product> list = this.productService.list();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}

	@GET
	@Path("/populate/{name}")
	public String populate(@PathParam("name") String name) {
		this.productService.create(name);
		return this.list();
	}

}
