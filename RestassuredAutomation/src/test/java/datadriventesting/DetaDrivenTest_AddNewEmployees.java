package datadriventesting;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DetaDrivenTest_AddNewEmployees {
	
	@Test
	void postNewEmployees()
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		// here we created data along with the post request
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("name","Alex");
		requestParams.put("salary","70000");
		requestParams.put("age","7");
		
		//add a header stating the request boy is a JSON
		httpRequest.header("Content-Type","applicatioin/json");
		
		//add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		//post request
		Response response = httpRequest.request(Method.POST,"/create");
		
		//capture response body to perform validation
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Alex"), true);
		Assert.assertEquals(responseBody.contains("70000"), true);
		Assert.assertEquals(responseBody.contains("7"), true);
		
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
	}
	
	
}
