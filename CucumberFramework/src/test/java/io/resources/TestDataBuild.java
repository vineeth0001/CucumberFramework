package io.resources;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.Pojo.AddToCartRequest;
import io.Pojo.CreateOrderReq;
import io.Pojo.LoginRequest;
import io.Pojo.OrderDetails;
import io.Pojo.Product;
import io.restassured.specification.RequestSpecification;

public class TestDataBuild extends Utilites {

	public LoginRequest loginApiPayload() throws IOException {

		DataDriven excel = new DataDriven();
		ArrayList<String> data = excel.getData("./testData.xlsx", "Login", "TestCases", "Login");
		LoginRequest login = new LoginRequest();
		// login.setUserEmail("vineethreddy.k@gmail.com");
		// login.setUserPassword("Saanvi@143");
		login.setUserEmail(data.get(1));
		login.setUserPassword(data.get(2));
		return login;
	}

	public RequestSpecification addProductPayload(String actualToken, String actualUserId) throws IOException {

		return given().spec(requestSpecification()).header("Authorization", actualToken)
				.formParam("productName", "Laptop").formParam("productAddedBy", actualUserId)
				.formParam("productCategory", "Electronics").formParam("productSubCategory", "Devices")
				.formParam("productPrice", "11500").formParam("productDescription", "Asus Laptop")
				.formParam("productFor", "everyone").multiPart("productImage", new File("src/test/resources/th.jpg"));
	}

	public AddToCartRequest addToCartPayload(String actualUserId, String actualProductId, String productName,
			String productStatus) {

		AddToCartRequest cartReq = new AddToCartRequest();
		cartReq.set_id(actualUserId);
		Product prod = new Product();
		prod.set__v(0);
		prod.set_id(actualProductId);
		prod.setProductName(productName);
		prod.setProductCategory("Electronics");
		prod.setProductSubCategory("Devices");
		prod.setProductAddedBy(actualUserId);
		prod.setProductFor("women");
		prod.setProductStatus(productStatus);
		prod.setProductPrice(11500);
		prod.setProductDescription("Asus Laptop");
		prod.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1700658506971.jpg");
		prod.setProductRating("0");
		prod.setProductTotalOrders("0");
		cartReq.setProduct(prod);
		return cartReq;
	}

	public CreateOrderReq createOrderPayload(String actualProductId) {
		OrderDetails order = new OrderDetails();
		order.setCountry("India");
		order.setProductOrderedId(actualProductId);
		List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		orderList.add(order);
		CreateOrderReq createOrder = new CreateOrderReq();
		createOrder.setOrders(orderList);
		return createOrder;

	}
}
