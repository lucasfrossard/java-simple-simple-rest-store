package com.avenuecode.app.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An order description
 * 
 * @author lucasfrossard@gmail.com
 *
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingList {

	/*
	 * The id of this order
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	/**
	 * Product within this order
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER )
	@JoinTable(name = "ShopProducts", joinColumns = @JoinColumn(name = "shop_id", foreignKey = @ForeignKey(name = "fk_shp_prd_order_id") ) , 
	                                  inverseJoinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_ord_prd_product_id") ) )
	private Set<Product> products;

}
