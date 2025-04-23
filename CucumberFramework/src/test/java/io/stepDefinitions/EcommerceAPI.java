/* package io.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.Pojo.AddProductResponse;
import io.Pojo.CreateOrderResponse;
import io.Pojo.LoginResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.log.LogConfigUtil;
import io.resources.TestDataBuild;
import io.resources.Utilites;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EcommerceAPI extends Utilites {

	RequestSpecification loginData;
	Response actualLoginRes;
	RequestSpecification addProductData;
	RequestSpecification addToCartData;
	RequestSpecification createOrderData;
	RequestSpecification deleteProductData;
	Response addProdRes;
	TestDataBuild data = new TestDataBuild();
	public static String actualToken;
	public static String actualUserId;
	public static String actualProductId;

	
	//  { LogConfigUtil.setLogFileForClass(this.getClass()); } -- Non-static
	 

	static {
		LogConfigUtil.setLogFileForClass(EcommerceAPI.class);
	}
//	private  final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
	private static final Logger logger = LogManager.getLogger(EcommerceAPI.class);

	@Given("the user is on the login page with valid credentials")
	public void the_user_is_on_the_login_page_with_valid_credentials() throws IOException {

		// Login

		loginData = given().spec(requestSpecification()).contentType(ContentType.JSON).body(data.loginApiPayload());

	}

	@When("the user sends a POST request to the login API")
	public void the_user_sends_a_post_request_to_the_login_api() {
		actualLoginRes = loginData.when().post("api/ecom/auth/login");
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(int value) {

		// System.out.println(actualLoginRes.getStatusCode());
		assertEquals(actualLoginRes.getStatusCode(), value);
		if (actualLoginRes.getStatusCode() != value) {
			logger.error("Expected status code: {}, but got: {}", value, actualLoginRes.getStatusCode());
		}

	}

	@Then("the response message should be {string}")
	public void the_response_should_be(String Value) {
		LoginResponse extractResponse = actualLoginRes.then().spec(responseSpecification()).extract().response()
				.as(LoginResponse.class);
		actualToken = extractResponse.getToken();
		actualUserId = extractResponse.getUserId();
		String actualMessage = extractResponse.getMessage();
		logger.info("Login API Response: {}", actualMessage);
		System.out.println(
				"token is : " + actualToken + " , userId is : " + actualUserId + " , message is : " + actualMessage);
		assertEquals(actualMessage, Value);
		logger.info("=========================================================================");
	}

	@Given("the user is able to add product on home page")
	public void the_user_is_able_to_add_product_on_home_page() throws IOException {
		addProductData = data.addProductPayload(actualToken, actualUserId);

	}

	@When("the user sends a Post request to the Add product API")
	public void the_user_sends_a_post_request_to_the_add_product_api() {
		addProdRes = addProductData.when().post("api/ecom/product/add-product");

	}

	@Then("the response status code shoule be {int}")
	public void the_response_status_code_shoule_be(int value) {

		assertEquals(addProdRes.getStatusCode(), value);
		AddProductResponse extractResponse = addProdRes.then().spec(responseSpecification()).extract().response()
				.as(AddProductResponse.class);
		actualProductId = extractResponse.getProductId();
		System.out.println(actualProductId);
	}

	
	@Given("the user is able to add product to cart with {string} {string}")
	public void the_user_is_able_to_add_product_to_cart_with(String productName, String productStatus)
			throws IOException {
		logger.info("Started Add To Cart---------------");
		addToCartData = given().spec(requestSpecification()).contentType(ContentType.JSON)
				.header("Authorization", actualToken)
				.body(data.addToCartPayload(actualUserId, actualProductId, productName,productStatus ));
	}

	@When("the user sends a Post request to the Add to cart API")
	public void the_user_sends_a_post_request_to_the_add_to_cart_api() {
		addToCartData.when().post("api/ecom/user/add-to-cart").then().spec(responseSpecification()).assertThat()
				.statusCode(200);
	}

	@Given("the user is able to create order")
	public void the_user_is_able_to_create_order() throws IOException {
		createOrderData = given().spec(requestSpecification()).contentType(ContentType.JSON)
				.header("Authorization", actualToken).body(data.createOrderPayload(actualProductId));
	}

	@When("the user sends a Post request to the Create order API")
	public void the_user_sends_a_post_request_to_the_create_order_api() throws IOException {
		CreateOrderResponse actualCreateRes = createOrderData.when().post("api/ecom/order/create-order").then()
				.assertThat().statusCode(201).extract().response().as(CreateOrderResponse.class);
		List<String> orders = actualCreateRes.getOrders();
		for (String orderId : orders) {
			System.out.println("Order ID: " + orderId);
			// View Order Details

			RequestSpecification viewOrderData = given().spec(requestSpecification())
					.header("Authorization", actualToken).queryParam("id", orderId);
			viewOrderData.when().get("api/ecom/order/get-orders-details").then().spec(responseSpecification())
					.assertThat().statusCode(200);
		}
	}

	@Given("the user is able to delete product")
	public void the_user_is_able_to_delete_product() throws IOException {
		deleteProductData = given().spec(requestSpecification()).header("Authorization", actualToken)
				.pathParam("productIdKey", actualProductId);
	}

	@When("the user sends a Delete request to the Delete product API")
	public void the_user_sends_a_delete_request_to_the_delete_product_api() {
		deleteProductData.when().delete("api/ecom/product/delete-product/{productIdKey}").then()
				.spec(responseSpecification()).assertThat().statusCode(200);
	}

} */
