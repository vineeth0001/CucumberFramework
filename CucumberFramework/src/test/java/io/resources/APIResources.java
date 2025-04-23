package io.resources;

public enum APIResources {

	// enum is a special class in java used to define collection of methods or constants.

	loginAPI("api/ecom/auth/login"), AddproductAPI("api/ecom/product/add-product"),
	AddtocartAPI("api/ecom/user/add-to-cart"), CreateorderAPI("api/ecom/order/create-order"),
	GetOrderDetailsAPI("api/ecom/order/get-orders-details"),
	DeleteproductAPI("api/ecom/product/delete-product/{productIdKey}");

	String resource;

	APIResources(String resource) {

		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}
