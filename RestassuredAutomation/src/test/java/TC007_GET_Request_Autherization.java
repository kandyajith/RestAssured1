import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Autherization {
	@test
	public void AutherizationTest()
	{
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		//Basic authentication
		PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
		authscheme.setUserName("ToolsQA");
		authscheme.setPassword("TestPassword");
		
		RestAssured.authentication=authscheme;
		
		//Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response object
		Response response=httpRequest.request(Method.GET, "/");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("The response body is",+responseBody);
		
		//status code validation
		int statusCode=response.getStatusCode();
		System.out.println("The status code is",+statusCode);
		Assert.assertEquals(statusCode, 200);
	}

}
