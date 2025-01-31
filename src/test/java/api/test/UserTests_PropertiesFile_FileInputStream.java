package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.When;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints_PropertiesFile_FileInputStream;
import api.endpoints.UserEndPoints_PropertiesFile_ResourceBundle;
import api.payload.UserPayload;
import io.restassured.response.Response;

public class UserTests_PropertiesFile_FileInputStream {
	Faker faker;
	UserPayload userPayload;
	public Logger logger;
		
	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new UserPayload();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());		
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("****** creating user ******");
		Response response= UserEndPoints_PropertiesFile_FileInputStream.createUser(userPayload);
		response.then().log().all();			
		Assert.assertEquals(response.getStatusCode(), 200);
		//response.then().log().body().statusCode(200);//another way to assert status code 
		
		logger.info("***** User is created *****");
		
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("***** Reading user info *****");
		
		Response response= UserEndPoints_PropertiesFile_FileInputStream.readUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		//response.then().log().body().statusCode(200);//another way to assert status code 
		
		logger.info("***** User info is displayed *****");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {	
		
		logger.info("***** UUpdating User *****");
		
		//update data using payloads
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response= UserEndPoints_PropertiesFile_FileInputStream.updateUser(userPayload.getUsername(),userPayload);
		response.then().log().all();			
		Assert.assertEquals(response.getStatusCode(), 200);
		//response.then().log().body().statusCode(200);//another way to assert status code 
		
		//check data after update
		Response responseAfterUpdate = UserEndPoints_PropertiesFile_FileInputStream.readUser(userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		//responseAfterUpdate.then().log().body().statusCode(200);//another way to assert status code 
		
		logger.info("***** User is updated *****");
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {		
		logger.info("***** Deleting user *****");
		
		Response response = UserEndPoints_PropertiesFile_FileInputStream.deleteUser(userPayload.getUsername());
		response.then().log().all();			
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***** User is deleted *****");
	}
	

}
