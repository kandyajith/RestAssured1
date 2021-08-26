import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {
	
	@Test
	 void getweatherDetails()
	 {
	  //Specify base URI
	  RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  //Response object
	  Response response=httpRequest.request(Method.GET,"/Hyderabad");
	  
	  //print response in console window
	  
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  
	  //status code validation
	  int statusCode=response.getStatusCode();
	  System.out.println("Status code is: "+statusCode);
	  Assert.assertEquals(statusCode, 200);
	  
	  //status line verification
	  String statusLine=response.getStatusLine();
	  System.out.println("Status line is:"+statusLine);
	  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	  
	 }

}

/*
 * Response Body is:{
 * "City": "Hyderabad",
 * "Temperature": "40.32 Degree celsius",
 * "Humidity":"20 percent",
 * "WeatherDescription":"few clouds",
 * "WindSpeed":"2.1 km per hour",
 * "WindDerectionDegree":"230 Degree",
 * }
 * Status code is: 200
 * Status line is: HTTP/1.1 200 OK
 * Passed: get weatherDetails
 * 
 * *======================================
 * 	Default test
 * Test run: 1, Failure:0, Skips:0
 * ======================================
 */
