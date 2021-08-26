package datadriventesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DDT_AddMultiNewEmployees {
	
	@Test(dataProvider="empdataprovider")
	void postNewEmployees(String ename, String esal, String eage)
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		// here we created data along with the post request
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("name",ename);
		requestParams.put("salary",esal);
		requestParams.put("age",eage);
		
		//add a header stating the request boy is a JSON
		httpRequest.header("Content-Type","applicatioin/json");
		
		//add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		//post request
		Response response = httpRequest.request(Method.POST,"/create");
		
		//capture response body to perform validation
		String responseBody=response.getBody().asString();
		
		//System.out.println("EResponse body is: ", +responseBody);
		
		AssertJUnit.assertEquals(responseBody.contains(ename), true);
		AssertJUnit.assertEquals(responseBody.contains(esal), true);
		AssertJUnit.assertEquals(responseBody.contains(eage), true);
		
		int statuscode=response.getStatusCode();
		AssertJUnit.assertEquals(statuscode, 200);
		
	}

	@DataProvider(name="empdataprovider")
	String [][] getEmpData()
	{
		String empdata[][] = { {"abc123","3000","40"},{"xyz123","34","43"},{"pqr123","343","34"}};
		return(empdata);
	}
	
	
}
