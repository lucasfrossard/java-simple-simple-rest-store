package com.avenuecode.app.pojo;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Builder
@Data
public class ShoppingListDetail {
	private long orderId;

	private long itemQty;

	@Singular("name")
	private List<String> names;
}
