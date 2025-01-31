package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String uID, String uName, String fname, String lName, String uEmail, String pwd, String ph ) {
		//System.out.print(uID+" "+uName+" "+fname+" "+lName+" "+uEmail+" "+pwd+" "+ph+"\n");
		UserPayload userPayload = new UserPayload();
		userPayload.setId(Integer.parseInt(uID));
		userPayload.setUsername(uName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lName);
		userPayload.setEmail(uEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response= UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String uName) {
		//System.out.println(uName);
		Response response= UserEndPoints.deleteUser(uName);
		Assert.assertEquals(response.getStatusCode(), 200);			
		
	}
	
	@Test
	void hey() {
		String reportLoc = (".\\reports\\"+"sumant.html");
		System.out.println(reportLoc);
	}

}
