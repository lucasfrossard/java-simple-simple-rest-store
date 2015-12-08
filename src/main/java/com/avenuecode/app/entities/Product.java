package com.avenuecode.app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The description of a product
 * 
 * @author lucasfrossard@gmail.com
 *
 */
@Entity
@Builder
@AllArgsConstructor
@Data
public class Product {

	/**
	 * The id of the product
	 */
	@Id
	private long id;

	/**
	 * The name of the product
	 */
	private String name;

}
