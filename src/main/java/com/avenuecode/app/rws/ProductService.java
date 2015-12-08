package com.avenuecode.app.rws;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.avenuecode.app.entities.Product;
import com.google.gson.Gson;

/**
 * Class responsible for handling products
 * 
 * @author lucasfrossard@gmail.com
 *
 */
@Path("product")
public class ProductService {

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
		Collection<Product> products = new ArrayList<Product>();
		products.add(new Product(1, "Botas"));
		products.add(new Product(1, "Cadarço"));
		Gson gson = new Gson();
		String json = gson.toJson(products);
		return json;
	}

}
