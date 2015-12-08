package com.avenuecode.app.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

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
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	/*
	 * The id of this order
	 */
	@Id
	private long id;
	
	/**
	 * Product within this order
	 */
	@OneToMany
	@JoinTable(
            name="OrderProducts",
            joinColumns = @JoinColumn( name="order_id"),
            inverseJoinColumns = @JoinColumn( name="product_id")
    )
	private Collection<Product> products;

}
