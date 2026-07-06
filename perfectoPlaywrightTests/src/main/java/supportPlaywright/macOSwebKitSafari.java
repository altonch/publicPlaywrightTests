// Created by Christopher Alton
// Version 1.0
// Updated 08-26-2025
package supportPlaywright;

//****** These are the JAVA dependencies required to run this test ******
//****** Without these, the test will be unable to run ******
//****** as these tell the test how to handle the assorted timeouts and ******
//****** the protocols to access the cloud for automation ******
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Playwright Dependencies to actually run Playwright Tests
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

// Google GSON Dependencies for Perfecto Reportium Support
import com.google.gson.JsonObject;
import com.google.gson.Gson;

// ****** This is the feature file that contains ******
// ****** all my cloud URL's and security tokens ******
import myUtilities.logins;

public class macOSwebKitSafari {

//****** These are the strings that control the browser details ******
//****** We need to set the browserVersion to one we currently support ******
//****** We need to set the browserLocation to one of the data centers ******
//****** Valid Locations: (US East), (EU Frankfurt),  (AP Sydney) ******
//****** These are North America, Germany and Australia respectively ******
	
	private static String browserVersion = "16";
	private static String browserLocation = "NA-US-BOS";

	    public static void main(String[] args) throws MalformedURLException, IOException {

			logins login = new logins();    	
	    	String host = login.testcloud;
	    	String myToken = login.testcloudst;
	    	
	    	String myWUT = "https://the-internet.herokuapp.com/login";
	    	String google = "https://www.google.com";
	    	
			String userPath = "//*[@id=\"username\"]";
			String passPath = "//*[@id=\"password\"]";
			String loginButton = "//*[@class=\"radius\"]";
			String logoutButton = "//*[@class=\"button secondary radius\"]";
	    	
			String userName = "tomsmith";
			String passWord = "SuperSecretPassword!";
			
			String secureArea = "//*[text()=\" Secure Area\"]";

			String testName = "perfecto-Playwright-webkitSafari";
			String projectName = "support-Playwright-macOS";
			String projectversion = "1.0";
			
			Playwright playwright = Playwright.create();
	            JsonObject capabilities = new JsonObject();
	            capabilities.addProperty("browserName", "Safari");
	            capabilities.addProperty("browserVersion", browserVersion);
	            capabilities.addProperty("location", browserLocation);
	            capabilities.addProperty("platformName", "Mac");
	            capabilities.addProperty("platformVersion", "macOS Ventura");
	            capabilities.addProperty("securityToken", myToken);

	            String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
	            String hostUrl = "wss://" + host + "/websocket?" + caps;
	            Browser browser = playwright.webkit().connect(hostUrl);

	            System.out.println("Starting Playwright Test");
	            Page page = browser.newPage(); 
		        try { 
	          //test start
	            Map<String, Object> paramsTestStart = new HashMap<>();
	            paramsTestStart.put("name", testName);
	            //tags
	            paramsTestStart.put("tags", List.of("playwright", "support"));         
	            //job
	            paramsTestStart.put("jobName","perfecto-Playwright-webkitSafari");
	            paramsTestStart.put("jobBranch", "perfecto-sampleCode");
	            paramsTestStart.put("jobNumber", 1);         
	            //project
	            paramsTestStart.put("projectName", projectName);
	            paramsTestStart.put("projectVersion", projectversion);

	            page.evaluate("perfecto:report:testStart", new Gson().toJson(paramsTestStart));
	            
	            Map<String, Object> paramsStepStart = new HashMap<>();
	            
	            paramsStepStart.clear();
	            paramsStepStart.put("name", "Goto myWUT");
	            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
	            System.out.println("Goto myWUT");         
	            page.navigate(myWUT);
	                        
	            paramsStepStart.clear();
	            paramsStepStart.put("name", "Type in userName");
	            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
	            System.out.println("Type in userName");
				page.click(userPath);
				page.fill(userPath,userName);
				
	            paramsStepStart.clear();
	            paramsStepStart.put("name", "Type in passWord");
	            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
	            System.out.println("Type in passWord");
				page.click(passPath);
				page.fill(passPath,passWord);
				
	            paramsStepStart.clear();
	            paramsStepStart.put("name", "Click Log In");
	            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
				System.out.println("Click Log In");
				page.click(loginButton);
				
				try {
	            paramsStepStart.clear();
	            paramsStepStart.put("name", "Verify Login Page");
	            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
				System.out.println("Verify Login Page");
				Locator myElement = page.locator(secureArea);
				assertThat(myElement).isVisible();

				if (myElement.isVisible()) {
		            paramsStepStart.clear();
		            paramsStepStart.put("name", "Login Page is Visible");
		            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
					System.out.println("Login Page is Visible");
				} else {
					paramsStepStart.clear();
		            paramsStepStart.put("name", "Login Page is Not Visible");
		            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
					System.out.println("Login Page is Not Visible");
				}
			} catch (Exception e) {
				System.out.println("Check to see why the element was not found");
			}				

	            paramsStepStart.clear();
	            paramsStepStart.put("name", "Log Out of TestPage");
	            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
				System.out.println("Log Out of TestPage");
				page.click(logoutButton);
				
	            paramsStepStart.clear();
	            paramsStepStart.put("name", "Move Browser to a Clean Page");
	            page.evaluate("perfecto:report:stepStart", new Gson().toJson(paramsStepStart));
				System.out.println("Move Browser to a Clean Page");
				page.navigate(google);
				
				Thread.sleep(2000);
				
				//test stop
				Map<String, Object> paramsTestStop = new HashMap<>();
				paramsTestStop.put("success", true);
//				paramsTestStop.put("failureDescription", "My failure message");
				                    
				page.evaluate("perfecto:report:testEnd", new Gson().toJson(paramsTestStop));
				
	            browser.close();
	        } catch (Exception exception) {
	            exception.printStackTrace();
				System.err.println(exception.getMessage());
				
				Map<String, Object> paramsTestStop = new HashMap<>();
				paramsTestStop.put("success", false);
				paramsTestStop.put("failureDescription", "Review Test");			                    
				page.evaluate("perfecto:report:testEnd", new Gson().toJson(paramsTestStop));
	        }
	        
	        
		    System.out.println("Playwright Test Complete");
	    }
}