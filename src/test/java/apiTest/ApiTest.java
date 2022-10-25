package apiTest;

import io.restassured.response.Response;
import utils.ExcelReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import actionFactory.ApiActions;
import actionFactory.ApiActions.RequestType;
import commonValues.Values;

public class ApiTest {

	public ExcelReader apitestData;
	
	ApiActions api = new ApiActions(Values.BASEURL);

	private static final int SUCCESS = 200;

	// Services Names
	private String signin_endpoint = "/login";

	public ApiTest() throws EncryptedDocumentException, IOException {
		
		apitestData = new ExcelReader(new File("src/test/resources/testData/apiTestData.xlsx"));
		apitestData.switchToSheet("apis");
		
	}
	
	public Response userSignInSuccess(String email,String password) {

		Map<String, Object> formParams = new HashMap<>();
		formParams.put("email", email);
		formParams.put("password", password);

		return api.performRequest(RequestType.POST, signin_endpoint, SUCCESS, null, null, formParams, null, null,null);

	}

	public Response userSignInSuccess() throws InvalidFormatException, IOException {
		
		int row = apitestData.getRowCount("apis");
		
		String[] paramsValue = apitestData.getCellData("ParamsValue").split(",");
		String[] params = apitestData.getCellData("FormParams").split(",");
		
		Map<String, Object> formParams = new HashMap<>();
		
		for(int i = 0; i < params.length; i++) {
			formParams.put(params[i], paramsValue[i]);
			
		}
		
		return api.performRequest(apitestData.getCellData("ServiceName"), Integer.parseInt(apitestData.getCellData("ExpectedStatusCode")), null, null, formParams, null, null, null);
	}
}
