package com.avenuecode.app.rws;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.avenuecode.app.entities.Product;
import com.avenuecode.app.entities.ShoppingList;
import com.avenuecode.app.pojo.ShoppingListDetail;
import com.avenuecode.app.service.ShoppingListService;
import com.avenuecode.util.GsonUtils;

/**
 * Class responsible for handling orders.
 * 
 * @author lucasfrossard@gmail.com
 *
 */
@Path("order")
public class ShoppingListWS {
	
	@Inject 
	private ShoppingListService shoppingListService;

	/**
	 * Place an order
	 * 
	 * @return json indicating success or failure of the operation
	 */
	@POST
	@Path("/place")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String placeOrder(Collection<Product> products) {
		Long place = this.shoppingListService.place(products);
		return GsonUtils.convertToJson(place);
	}

	/**
	 * Modify placed orders, receives the id and a list of products and update
	 * that order
	 * 
	 * @return json indicating success or failure of the operation
	 */
	@POST
	@Path("/change/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String change(Collection<Product> products, @PathParam("orderId") long orderId) {
		this.shoppingListService.modify(orderId, products);
		return GsonUtils.convertToJson(Boolean.TRUE);
	}

	/**
	 * List orders
	 * 
	 * @return json containing all orders placed
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list() {
		Collection<ShoppingList> orders = this.shoppingListService.list();
		return GsonUtils.convertToJson(orders);
	}

	/**
	 * Details of a given order
	 * 
	 * @param id
	 * @return details of a given order
	 */
	@GET
	@Path("/details/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String details(@PathParam("id") int id) {
		ShoppingListDetail details = this.shoppingListService.details(id);
		return GsonUtils.convertToJson(details);

	}

	

}
