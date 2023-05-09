package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	public int count = 0;
    private static final int maxTry = 4; //최대 4번까지 재시도
    
    //public int recount = 0;
    
    @Override
    public boolean retry(ITestResult iTestResult) {

        if (iTestResult.getTestContext().getCurrentXmlTest().getName().contains("failed")) {

        	count ++;
        	//recount = count;
        	
        	if (!iTestResult.isSuccess()) {
				iTestResult.setWasRetried(true);
				if (count < maxTry) {
	                iTestResult.setAttribute("re-count", count);
	                return true;
	            } else {
	            	iTestResult.setAttribute("re-count", count);
	            	iTestResult.setStatus(ITestResult.FAILURE);
	                
	            }
        	} else {
        		iTestResult.setStatus(ITestResult.SUCCESS);
        	}
        	
        	return false;

        }
        return false;
    }
    
}
