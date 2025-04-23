package io.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.log.Log4jFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilites {

	public RequestSpecification requestSpecification() throws IOException {
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri(setGlobalValue("baseUrl", "./src/test/resources/configuration.properties"))
				.addFilter(new Log4jFilter()).build();
		return reqSpec;

	}

	public ResponseSpecification responseSpecification() {
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return resSpec;
	}

	public static String setGlobalValue(String key, String filePath) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		return prop.getProperty(key);
	}

	public  String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath jsonPath = new JsonPath(resp);
		return jsonPath.get(key).toString();

	}
}
