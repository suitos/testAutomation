package apiTest;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import actionFactory.ApiActions;
import actionFactory.ApiActions.RequestType;
import commonValues.Values;

public class ApiTest {

	ApiActions api = new ApiActions(Values.BASEURL);

	private static final int SUCCESS = 200;

	// Services Names
	private String signin_endpoint = "/login";

	public Response userSignInSuccess(String email,String password) {

		Map<String, Object> formParams = new HashMap<>();
		formParams.put("email", email);
		formParams.put("password", password);

		return api.performRequest(RequestType.POST, signin_endpoint, SUCCESS, null, null, formParams, null, null,
				null);

	}
}
