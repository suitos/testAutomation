package actionFactory;

import static org.testng.Assert.fail;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import log.Logging;

public class ApiActions {
	
	private RequestSpecification request;
	private Response response;
	private QueryableRequestSpecification queryableRequestSpecs;
	private SessionFilter sessionFilter;
	private CookieFilter cookieFilter;
	private String baseUrl;

	public enum RequestType {
		POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE"), PATCH("PATCH");

		private String value;

		RequestType(String type) {
			this.value = type;
		}

		protected String getValue() {
			return value;
		}
	}

	public ApiActions(String baseUrl) {
		this.baseUrl = baseUrl;
		sessionFilter = new SessionFilter();
		cookieFilter = new CookieFilter();
	}

	private RequestSpecification requestSpec = new RequestSpecBuilder()
			.log(LogDetail.ALL).build();
	
	private ResponseSpecification responseSpec = new ResponseSpecBuilder()
			.log(LogDetail.BODY).build();

	/**
	 * 
	 * @param requestType
	 * @param serviceName
	 * @param expectedStatusCode
	 * @param headers
	 * @param contentType
	 * @param formParams
	 * @param queryParams
	 * @param body
	 * @param cookies
	 * @return Response
	 */
	public Response performRequest(RequestType requestType, String serviceName, int expectedStatusCode,
			Map<String, Object> headers, ContentType contentType, Map<String, Object> formParams,
			Map<String, Object> queryParams, Object body, Map<String, String> cookies) {
		String requestUrl = baseUrl + serviceName;

		request = RestAssured.given().spec(requestSpec);
		queryableRequestSpecs = SpecificationQuerier.query(request);

		Logging.getLogger().info("Request URL: [" + requestUrl + "] | Request Method: [" + requestType.getValue()
				+ "] | Expected Status Code: [" + expectedStatusCode + "]");

		try {

			if (headers != null) {
				request.headers(headers);
				String qHeaders = queryableRequestSpecs.getHeaders().toString();
				Logging.getLogger().info("Headers: " + qHeaders.getBytes());

			}

			if (contentType != null) {
				request.contentType(contentType);
				String qContentType = queryableRequestSpecs.getContentType();
				Logging.getLogger().info("Content Type: " + qContentType.getBytes());
				
			}

			if (formParams != null) {
				request.formParams(formParams);
				String qFormParams = queryableRequestSpecs.getFormParams().toString();
				Logging.getLogger().info("Form params: " + qFormParams.getBytes());
				
			}

			if (queryParams != null) {
				request.queryParams(queryParams);
				String qQueryParams = queryableRequestSpecs.getQueryParams().toString();
				Logging.getLogger().info("Query params: " + qQueryParams.getBytes());
				
			}

			if (body != null) {
				request.body(body);
				String qBody = queryableRequestSpecs.getBody().toString();
				Logging.getLogger().info("Body :" + qBody.getBytes());
				
			}

			if (cookies != null) {
				request.cookies(cookies);
				String qCookies = queryableRequestSpecs.getCookies().toString();
				Logging.getLogger().info("Cookies : " + qCookies.getBytes());
				
			}

			request.filter(sessionFilter);
			request.filter(cookieFilter);

			switch (requestType) {
			case POST:
				response = request.post(requestUrl);
				break;
			case GET:
				response = request.get(requestUrl);
				break;
			case PUT:
				response = request.put(requestUrl);
				break;
			case DELETE:
				response = request.delete(requestUrl);
				break;
			case PATCH:
				response = request.patch(requestUrl);
				break;
			}

			response.then().spec(responseSpec).statusCode(expectedStatusCode);

		} catch (Exception e) {
			Logging.getLogger().error(e.getMessage());
			fail(e.getMessage());
			
		}

		Logging.getLogger().info(response.asByteArray());

		return response;
	}

}
