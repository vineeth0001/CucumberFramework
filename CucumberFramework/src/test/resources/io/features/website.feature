Feature: User Login and Performed Actions 


@Login @Regression # we can have multiple tags
Scenario: Verify user login successfully 
	Given the user is on the login page with valid credentials 
	When the user sends a "POST" request to the "loginAPI" 
	Then the response status code should be 200 
	And the response "message" should be "Login Successfully" 
	
@AddProduct @Regression 
Scenario: Verify user able to Add product 

	Given the user is able to add product on home page 
	When the user sends a "Post" request to the "AddproductAPI" 
	Then the response status code should be 201 
	And the response "message" should be "Product Added Successfully" 
	
@AddToCart @Regression 
Scenario Outline: Verify user is able to add product to cart 
	Given the user is able to add product to cart with "<productName>" "<productStatus>" 
	When the user sends a "Post" request to the "AddtocartAPI" 
	Then the response status code should be 200 
	Examples: 
		| productName | productStatus |
		#		| Phone       | true     |
		| Laptop	  | true     |
		
		@CreateOrder @Regression 
		Scenario: Verify user is able to create an order 
			Given the user is able to create order 
			When the user sends a "Post" request to the "CreateorderAPI" 
			Then the response status code should be 201 
			And the response "message" should be "Order Placed Successfully" 
			
		@ViewOrder 
		Scenario: Verify user is able to view order details 
			Given the user has a valid order ID 
			When the user sends a "GET" request to the "GetOrderDetailsAPI" 
			Then the response status code should be 200 
			And the response "message" should be "Orders fetched for customer Successfully" 
			
		@DeleteProduct @Regression 
		Scenario: Verify user is able to delete product 
			Given the user is able to delete product 
			When the user sends a "Delete" request to the "DeleteproductAPI" 
			Then the response status code should be 200 
			And the response "message" should be "Product Deleted Successfully"  
	