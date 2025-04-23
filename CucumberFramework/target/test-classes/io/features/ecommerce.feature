@Ignore
Feature: User Login and Performed Actions 

Scenario: Validate user login successfully 
	Given the user is on the login page with valid credentials 
	When the user sends a POST request to the login API 
	Then the response status code should be 200 
	And the response message should be "Login Successfully" 
	
	
Scenario: Validate user able to Add product 

	Given the user is able to add product on home page 
	When the user sends a Post request to the Add product API 
	Then the response status code shoule be 201 
	# And the response message should be "Product Added Successfully"
	
	
Scenario Outline: Validate user is able to add product to cart 
	Given the user is able to add product to cart with "<productName>" "<productStatus>" 
	When the user sends a Post request to the Add to cart API 
	# Then the response status code should be 201 
	Examples: 
		| productName | productStatus |
		| Phone       | true     |
		| Laptop	  | true     |
		
		
Scenario: Validate user is able to create an order 
	Given the user is able to create order 
	When the user sends a Post request to the Create order API 
	# Then the response status code should be 201 
	# And the response message should be "Order Placed Successfully" 
	
Scenario: Validate user is able to delete product 
	Given the user is able to delete product 
	When the user sends a Delete request to the Delete product API 
	# Then the response status code should be 200
	# And the response message should be "Order Placed Successfully
	