package gitProjectActivity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIProject1 {
	
	
	RequestSpecification reqSpec;
	String SSHkey = "ssh-rsa *******";
	int  SSHkeyID;
	
	@BeforeClass
	public void setup()
	{
		reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addHeader("Authorization", "token ********")
				.setBaseUri("https://api.github.com").build();
			
	}
	
	@Test(priority=1)
	public void Postmethod_addKey()
	{
		 String reqBody = "{\"title\": \"TestKey\", \"key\": \""+SSHkey+"\"}";
	//	 String reqBody1 = "{\"title\": \"TestKey\", \"key\": \"" + sshKey + "\" }";
		 
		 Response response = given().log().all().spec(reqSpec).body(reqBody).when().post("/user/keys");
		 response.then().log().all();
		 String responseBody = response.getBody().prettyPrint();
		 System.out.println(responseBody);
		 SSHkeyID = response.then().extract().path("id");
		 System.out.println("ID is "+SSHkeyID);
		
	}
	
	
	@Test(priority=2)
	public void Getmethod_GetKeys()
	{
		
	 System.out.println("Get Method");
		 
		 Response response = given().spec(reqSpec).when().get("/user/keys");
		 String resBody = response.getBody().asPrettyString();
			System.out.println(resBody);
			// Assertions
			response.then().statusCode(200);
	}
	
	

	@Test(priority=3)
	public void Deletemethod()
	{
		 System.out.println("Delete Method");
		Response response = given().spec(reqSpec).pathParam("keyId", SSHkeyID).when().delete("/user/keys/{keyId}");
	
		 String resBody = response.getBody().asPrettyString();
			System.out.println(resBody);
			response.then().statusCode(204);
	}	



}
