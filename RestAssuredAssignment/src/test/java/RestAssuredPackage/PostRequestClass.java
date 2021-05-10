package RestAssuredPackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import util.datafromexcell;

public class PostRequestClass {
	@Test(dataProvider = "PostRequestdata")
	public void testcase1(String name1, String name2, String name3) {

		JSONObject jsonobj = new JSONObject(); // main body object
		jsonobj.put("stream", name1);
		jsonobj.put("firstname", name2);
		jsonobj.put("lastname", name3);
		RestAssured.baseURI = "http://localhost:3000";
		Response resp = given().contentType(ContentType.JSON).body(jsonobj.toJSONString()).post("/Students").then()
				.statusCode(201).log().all().extract().response();

		RequestSpecification httpRequest = RestAssured.given();
		Response resp1 = httpRequest.get("/Students");
		int statusCode = resp1.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		ResponseBody respbody = resp.body();
		System.out.println("Response body is : " + respbody.asString());
		String strbody = respbody.asString();
		Assert.assertTrue(strbody.contains(name1));
		Assert.assertTrue(strbody.contains(name2));
		Assert.assertTrue(strbody.contains(name3));
		Assert.assertEquals(strbody.contains("id"), true);

	}

	@DataProvider(name = "PostRequestdata")
	public Object[][] postreqdata() throws IOException {
		Object[][] data = datafromexcell.testdata();
		return data;
	}
}
