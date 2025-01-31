package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

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
public class UserEndPoints {
	
	public static Response createUser(UserPayload payload){
		
		Response response = given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)		
			
		
		.when()
			.post(Routes.post_url)
		;
		
		return response;
	}
	
	public static Response readUser(String userName){
		
		Response response = given()
			.relaxedHTTPSValidation()
			.pathParam("username", userName)
						
		
		.when()
			.get(Routes.get_url)
		;
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPayload payload){
		
		Response response = given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)		
			
		
		.when()
			.put(Routes.update_url)
		;
		
		return response;
	}

	public static Response deleteUser(String userName){
		
		Response response = given()
			.relaxedHTTPSValidation()
			.pathParam("username", userName)
						
		
		.when()
			.delete(Routes.delete_url)
		;
		
		return response;
	}

}
