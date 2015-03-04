package controllers;

import org.junit.*;

import com.google.common.collect.ImmutableMap;

import models.*;
import play.mvc.Result;
import play.test.*;
import static org.junit.Assert.*;
import static play.test.Helpers.*;

public class LoginTest extends WithApplication {

	@Before
	public void setup() {
		fakeApplication(inMemoryDatabase(), fakeGlobal());
		User.createUser("test@email.com", "123456");

	}

	@Test
	public void authenticateSuccess() {
		Result result = callAction(
				controllers.routes.ref.Application.signIn(),
				fakeRequest().withFormUrlEncodedBody(
						ImmutableMap.of("email", "test@mail.com", "password",
								"123456")));
	assertEquals(303,status(result));
	assertEquals("1",session(result).get("user_id"));
	}
	
	@Test 
	public void authenticateFail(){
		Result result = callAction(
				controllers.routes.ref.Application.signIn(),fakeRequest().withFormUrlEncodedBody(ImmutableMap.of("email","test@mail.com","password","123456")));
		asserEquals(200,status(result));
		asserEquals(null,session(result).get("user_id"));
		assertFalse(session(result).containsKey("user_id"));
	
	}
	
}
