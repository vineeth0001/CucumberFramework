package io.log;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jFilter implements Filter {

	private static final Logger log = LogManager.getLogger(Log4jFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

		log.info("---- REQUEST ----");
		log.info("URI: {}", requestSpec.getURI());
		log.info("Method: {}", requestSpec.getMethod());
		log.info("Headers: {}", requestSpec.getHeaders());
		if (requestSpec.getBody() != null) {
			log.info("Body: {}",()-> requestSpec.getBody());
		}

		Response response = ctx.next(requestSpec, responseSpec);

		int statusCode = response.getStatusCode();

		if (statusCode >= 400) {
			log.error("---- ERROR RESPONSE ----");
			log.error("Status Code: {}", statusCode);
			log.error("Headers: {}", response.getHeaders());
			log.error("Body: {}", response.getBody().asPrettyString());
		} else {
			log.info("---- RESPONSE ----");
			log.info("Status Code: {}", statusCode);
			log.info("Headers: {}", response.getHeaders());
			log.info("Body: {}", response.getBody().asPrettyString());
		}

		return response;
	}

}

/*
 * want to generate request body for form data etc....
 * log.info("---- REQUEST ----"); log.info("URI: {}", requestSpec.getURI());
 * log.info("Method: {}", requestSpec.getMethod()); log.info("Headers: {}",
 * requestSpec.getHeaders());
 * 
 * String contentType = requestSpec.getContentType();
 * 
 * if (contentType != null) { if (contentType.contains("application/json")) {
 * String requestBody = requestSpec.getRequestBody(); if (requestBody != null) {
 * log.info("JSON Body: {}", requestBody); } } else if
 * (contentType.contains("application/x-www-form-urlencoded")) {
 * log.info("Form Params: {}", requestSpec.getFormParams()); } else if
 * (contentType.contains("multipart/form-data")) {
 * log.info("Multipart Params: {}", requestSpec.getMultipartParams()); } else {
 * // Fallback String requestBody = requestSpec.getRequestBody(); if
 * (requestBody != null) { log.info("Raw Body: {}", requestBody); } else {
 * log.info("No readable request body found."); } } }
 */
