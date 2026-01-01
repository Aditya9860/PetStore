package api.endpoints;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
//Curd operations
public class UserEndPoints {
	
   public static Response createUser(User payload){
		Response response = given()
				  .contentType(ContentType.JSON)
				  .accept(ContentType.JSON)
				  .body(payload)
		.when()
		   .post(Routes.post_URL);
		return response;
   }
   
   public static Response readUser(String userName){
		Response response = given()
				.pathParam("username", userName)
		.when()
		   .get(Routes.get_URL);
		return response;
   }
   
   public static Response updateUser(String userName, User payload){
	    return given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .pathParam("username", userName)
	            .body(payload)
	    .when()
	            .put(Routes.update_URL);
	}

   public static Response deleteUser(String userName){
		Response response = given()
				.pathParam("username", userName)
		.when()
		   .delete(Routes.delete_URL);
		return response;
  }

}
