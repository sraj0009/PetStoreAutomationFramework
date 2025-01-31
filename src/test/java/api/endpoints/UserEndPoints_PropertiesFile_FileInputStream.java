package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
	it contains to perform CRUD operations
	 	- Create
	 	- Read
	 	- Update
	 	- Delete

 */
public class UserEndPoints_PropertiesFile_FileInputStream {
	
	//load properties file
	public static Properties getURL() {
		Properties routes = new Properties();
		
		try {
			// Load the properties file from src/test/resources
	        String filePath = "src/test/resources/routes.properties";
	        FileInputStream input = new FileInputStream(filePath);
	        routes.load(input);
			
		}catch (IOException e) {
	        System.err.println("Error loading properties file: " + e.getMessage());
	    }
        
		return routes;
	}
	
	public static Response createUser(UserPayload payload){
		String post_url = getURL().getProperty("post_url");
		
		Response response = given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)		
			
		
		.when()
			.post(post_url)
		;
		
		return response;
	}
	
	public static Response readUser(String userName){
		String get_url = getURL().getProperty("get_url");		
		
		Response response = given()
			.relaxedHTTPSValidation()
			.pathParam("username", userName)
						
		
		.when()
			.get(get_url)
		;
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPayload payload){
		String update_url = getURL().getProperty("update_url");	
		
		Response response = given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)		
			
		
		.when()
			.put(update_url)
		;
		
		return response;
	}

	public static Response deleteUser(String userName){
		String delete_url = getURL().getProperty("delete_url");	
		
		Response response = given()
			.relaxedHTTPSValidation()
			.pathParam("username", userName)
						
		
		.when()
			.delete(delete_url)
		;
		
		return response;
	}

}
