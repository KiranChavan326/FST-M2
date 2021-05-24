package restAssuredPackage;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
 
import org.testng.annotations.Test;
 
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
 
public class RestAssured_Actitity1 {
 
    @Test
    public void GetPetDetails() {
        // Specify the base URL to the RESTful web service
        baseURI = "https://petstore.swagger.io/v2/pet";
 
        // Make a request to the server by specifying the method Type and 
        // resource to send the request to.
        // Store the response received from the server for later use.
        Response response = 
            given().contentType(ContentType.JSON) // Set headers
            .when().get("/findByStatus?status=available");	
         // Run GET request
       
 
        // Now let us print the body of the message to see what response
        // we have received from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
//        String responsePretty = response.getBody().prettyPrint();
//        System.out.println("Response Body is =>  " + responsePretty);
//        
//        Headers responseHeaders = response.then().extract().headers();
//        System.out.println("Response Headers :"+responseHeaders);
//       
 
        // Assertions
        response.then().statusCode(200);
        response.then().body("[0].status", equalTo("available"));
        response.then().body("[0].name", equalTo("dolphin"));
    }
 
}


