package io.website;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.Pojo.CreateOrderResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.log.LogConfigUtil;
import io.resources.APIResources;
import io.resources.TestDataBuild;
import io.resources.Utilites;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EcommerceWebsite extends Utilites {
	RequestSpecification res;
	Response response;
	static String tokenId;
	static String userId;
	static String productId;
	// This list stores order IDs created during the "Create Order" API call,
	// so they can be reused in later steps, like viewing order details.
	static List<String> createdOrderIds = new ArrayList<>();
	TestDataBuild data = new TestDataBuild();

	static {
		LogConfigUtil.setLogFileForClass(EcommerceWebsite.class);
	}
//	private  final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
	private static final Logger logger = LogManager.getLogger(EcommerceWebsite.class);

	@Given("the user is on the login page with valid credentials")
	public void the_user_is_on_the_login_page_with_valid_credentials() throws IOException {

		res = given().spec(requestSpecification()).contentType(ContentType.JSON).body(data.loginApiPayload());
	}

	@When("the user sends a {string} request to the {string}")
	public void the_user_sends_a_request_to_the(String method, String resource) throws IOException {

		APIResources resourceName = APIResources.valueOf(resource);
		if (method.equalsIgnoreCase("POST")) {
			System.out.println("resourceName is : " + resourceName.getResource());
			response = res.when().post(resourceName.getResource());
			if (resourceName.equals(APIResources.loginAPI)) {
				System.out.println("=======================");
				tokenId = getJsonPath(response, "token");
				userId = getJsonPath(response, "userId");
				logger.info("response token is : " + tokenId);
				System.out.println(tokenId);
				System.out.println(userId);
			}
			if (resourceName.equals(APIResources.AddproductAPI)) {
				System.out.println("=======================");
				productId = getJsonPath(response, "productId");
				System.out.println(userId);
			}
			if (resourceName.equals(APIResources.CreateorderAPI)) {
				CreateOrderResponse createOrderRes = response.as(CreateOrderResponse.class);
				List<String> orders = createOrderRes.getOrders();
				for (String orderId : orders) {
					System.out.println("Order ID: " + orderId);
					createdOrderIds.add(orderId); // Add order ID to the global list
				}
			}

		} else if (method.equalsIgnoreCase("DELETE")) {
			System.out.println("resourceName is : " + resourceName.getResource());
			response = res.when().delete(resourceName.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			System.out.println("resourceName is : " + resourceName.getResource());
			response = res.when().get(resourceName.getResource());

		}
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(int statusCodeValue) {
		assertEquals(response.getStatusCode(), statusCodeValue);
		if (response.getStatusCode() != statusCodeValue) {
			logger.error("Expected Status Code: {}, but got: {}", statusCodeValue, response.getStatusCode());
			assertEquals("Unexpected status code!", statusCodeValue, response.getStatusCode());
		}
	}

	@Then("the response {string} should be {string}")
	public void the_response_message_should_be(String key, String actualValue) {
		assertEquals(getJsonPath(response, key), actualValue);

	}

	@Given("the user is able to add product on home page")
	public void the_user_is_able_to_add_product_on_home_page() throws IOException {
		res = data.addProductPayload(tokenId, userId);
	}

	@Given("the user is able to add product to cart with {string} {string}")
	public void the_user_is_able_to_add_product_to_cart_with(String productName, String productStatus)
			throws IOException {
		res = given().spec(requestSpecification()).contentType(ContentType.JSON).header("Authorization", tokenId)
				.body(data.addToCartPayload(userId, productId, productName, productStatus));
	}

	@Given("the user is able to create order")
	public void the_user_is_able_to_create_order() throws IOException {
		res = given().spec(requestSpecification()).contentType(ContentType.JSON).header("Authorization", tokenId)
				.body(data.createOrderPayload(productId));
	}

	@Given("the user has a valid order ID")
	public void the_user_has_a_valid_order_id() throws IOException {
		String orderId = createdOrderIds.get(0);
		res = given().spec(requestSpecification()).header("Authorization", tokenId).queryParam("id", orderId);
	}

	@Given("the user is able to delete product")
	public void the_user_is_able_to_delete_product() throws IOException {
		res = given().spec(requestSpecification()).header("Authorization", tokenId).pathParam("productIdKey",
				productId);
		;
	}

}
