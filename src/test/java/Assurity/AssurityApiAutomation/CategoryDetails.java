package Assurity.AssurityApiAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CategoryDetails {

	@Test
	public void RegistrationSuccessful() {
		RequestSpecification request = RestAssured.given();
		Response response = request.get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false");
		
		String json = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(json);
		
		String name = jsonPath.get("Name");
		boolean canRelist = jsonPath.get("CanRelist");
		String promotionsDesc =  jsonPath.getString("Promotions[1].Description");
		
		System.out.println(promotionsDesc);

		Assert.assertEquals("Carbon credits", name, "Name Matched: Carbon credits");
		Assert.assertTrue(canRelist,"CanRelist getting true");
		Assert.assertTrue(promotionsDesc.contains("2x larger image"));

	}
}
