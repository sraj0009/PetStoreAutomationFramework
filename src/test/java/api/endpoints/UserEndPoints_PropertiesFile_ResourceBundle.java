package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

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
public class UserEndPoints_PropertiesFile_ResourceBundle {
	//load properties file
	public static ResourceBundle getURL(){
		 ResourceBundle routes = ResourceBundle.getBundle("routes");//load properties file
		 return routes;
	}
	
	public static Response createUser(UserPayload payload){
		String post_url = getURL().getString("post_url");
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
		
		String get_url = getURL().getString("get_url");
		
		Response response = given()
			.relaxedHTTPSValidation()
			.pathParam("username", userName)
						
		
		.when()
			.get(get_url)
		;
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPayload payload){
		String update_url = getURL().getString("update_url");
		
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
		String delete_url = getURL().getString("delete_url");
		
		Response response = given()
			.relaxedHTTPSValidation()
			.pathParam("username", userName)
						
		
		.when()
			.delete(delete_url)
		;
		
		return response;
	}

}
