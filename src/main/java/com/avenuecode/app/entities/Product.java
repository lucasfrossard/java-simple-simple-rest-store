package com.avenuecode.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The description of a product
 * 
 * @author lucasfrossard@gmail.com
 *
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

	/**
	 * The id of the product
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	/**
	 * The name of the product
	 */
	@Column(name = "name")
	private String name;

	@Override
	public int hashCode() {
		return Long.valueOf(id).hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Product) {
			Product aProduct = (Product) object;
			if (aProduct != null) {
				return aProduct.getId() == this.id;
			}
		}
		return false;
	}

}
