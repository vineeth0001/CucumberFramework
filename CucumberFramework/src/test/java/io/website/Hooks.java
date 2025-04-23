package io.website;

import java.io.IOException;

import io.cucumber.java.Before;
import io.resources.Utilites;

public class Hooks {

	@Before("@AddProduct")
	public void beforeScenario() throws IOException {
		EcommerceWebsite website = new EcommerceWebsite();

		 String method = Utilites.setGlobalValue("httpMethod", "./src/test/resources/configuration.properties");
		 String resource = Utilites.setGlobalValue("loginEndpoint", "./src/test/resources/configuration.properties");
		if (EcommerceWebsite.tokenId == null & EcommerceWebsite.userId == null) {

			website.the_user_is_on_the_login_page_with_valid_credentials();
			website.the_user_sends_a_request_to_the(method, resource);
		}
	}

}
