package newtesting;





import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;

//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v108.browser.Browser;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class tomsmith {

//Path of WebDriver	
String driverpath = "D:\\autmation new\\data\\chromedriver.exe";
public	WebDriver driver;



@BeforeSuite
public void Setup()
            {  
    	    System.setProperty("webdriver.chrome.driver", driverpath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");	
			driver = new ChromeDriver(options);
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize(); 
		    }


            @AfterSuite
            public void teardown() 
            {
            driver.close();
            }
		
		
               
	     	@Test (priority=1)
	    	public void loginPassed()
	     	{
			driver.get("http://localhost:7080/login");
	    	driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("tomsmith");
	    	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("SuperSecretPassword!");
	    	driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
	    	WebElement logintitle = driver.findElement(By.xpath("/html/body/div[1]/div/div"));
    		String actual = logintitle.getText();
	    	Assert.assertTrue(actual.contains("You logged into a secure area!"));
		    }
		
		
		
		
			
		
	     	@Test (priority=2)
	    	public void loginFailed() 
	     	{
			driver.get("http://localhost:7080/login");
	    	driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("tomsmdffdfdith");
	    	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("SuperSedfddfdfdffcretPassword!");
	    	driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
	    	String text = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
	    	String actualtitle = "Your username is invalid!";
	    	Assert.assertTrue(text.contains(actualtitle));
		    }
		
		
		

		
		
		
	    	@Test (priority=3)
	    	public void CheckBoxes() 
	    	{
			driver.get("http://localhost:7080/checkboxes");
	    	driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
	    	driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
	    	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).isSelected());
	    	Assert.assertFalse(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).isSelected());	
	     	}
		
		
		
		
		
	
		
		
		    @Test (priority=4)
		    public void DragAndDrop() throws InterruptedException 
		    {
		    Thread.sleep(1000);
		    driver.get("http://localhost:7080/drag_and_drop");
		    WebElement source = driver.findElement(By.xpath("(//div[@id='column-a'])[1]"));
		    WebElement target = driver.findElement(By.xpath("(//div[@id='column-b'])[1]"));
		    String targettext = target.getText();

		    Thread.sleep(1000);
		 
		       //Dragging source destination to target destination
		       JavascriptExecutor js = (JavascriptExecutor) driver;
			   js.executeScript("function createEvent(typeOfEvent) {\n" +"var event =document.createEvent(\"CustomEvent\");\n" +"event.initCustomEvent(typeOfEvent,true, true, null);\n" +"event.dataTransfer = {\n" +"data: {},\n" +"setData: function (key, value) {\n" +"this.data[key] = value;\n" +"},\n" +"getData: function (key) {\n" +"return this.data[key];\n" +"}\n" +"};\n" +"return event;\n" +"}\n" +"\n" +"function dispatchEvent(element, event,transferData) {\n" +"if (transferData !== undefined) {\n" +"event.dataTransfer = transferData;\n" +"}\n" +"if (element."
					+ "dispatchEvent) {\n" + "element.dispatchEvent(event);\n" +"} else if (element.fireEvent)"
					+ " {\n" +"element.fireEvent(\"on\" + event.type, event);\n" +"}\n" +"}\n" +"\n" +"function "
					+ "simulateHTML5DragAndDrop(element, destination) {\n" +"var dragStartEvent =createEvent"
					+ "('dragstart');\n" +"dispatchEvent(element, dragStartEvent);\n"  +"var dropEvent ="
					+ "createEvent('drop');\n" +"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" 
					+"}\n" +"\n" +"var source = arguments[0];\n" +"var destination = arguments[1];\n" +"simulateHTML5DragAndDrop"
			        + "(source,destination);",source, target);
				
			   //Getting text of source destination after dragging
				String sourcetext = source.getText();  
				
				//Using assertion to verify that drag and drop is successfully
				Assert.assertEquals(sourcetext, targettext);
			
		      }
		
		 
		    
		    
		    
		    
		    
		 
		    
		    @Test (priority=5)
		    public void Dropdown() throws InterruptedException 
		    {
		    	
		    Thread.sleep(1000);

		    driver.get("http://localhost:7080/dropdown");
		    driver.findElement(By.xpath("//*[@id=\"dropdown\"]")).click();
		    driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).click();
		    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).isSelected() );

		    Thread.sleep(1000);
		 
		    driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[3]")).click();
		    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[3]")).isSelected() );
		    }
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    @Test (priority=6)
		    public void DynamicContent() throws InterruptedException  
		    {
		    Thread.sleep(1000);
		    
		    driver.get("http://localhost:7080/dynamic_content"); 
		    for (int i=0; i<3;i++) {    
		    String row1  = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]")).getText();
		    String row2  = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]")).getText();
		    String row3  = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]")).getText();
		   	    
		    Thread.sleep(1000);
		    driver.navigate().refresh();
		    
		    String row1AfterRefresh  = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]")).getText();
		    String row2AfterRefresh  = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]")).getText();
		    String row3AfterRefresh  = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[3]/div[2]")).getText();
		    
		
		    Assert.assertNotEquals(row1,row1AfterRefresh);
		    Assert.assertNotEquals(row2,row2AfterRefresh);
		    Assert.assertNotEquals(row3,row3AfterRefresh);
		    }
		    }
		    
		    
		    
		    

		    
		    @Test (priority=7)
		    public void ControleContent() throws InterruptedException  
		    {
		    Thread.sleep(5000);
		    	
		    
		     driver.get("http://localhost:7080/dynamic_controls");
		     driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button")).click();
		     
		     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		     By remove = By.xpath("/html/body/div[2]/div/div[1]/form[1]/p");
		     WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(remove));
		     Assert.assertTrue(element.isDisplayed());
		        
		     
		     // Adding 

		     driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[1]/button")).click();
		     By Add = By.xpath("/html/body/div[2]/div/div[1]/form[1]/p");
   
			 WebElement checkadd = wait.until(ExpectedConditions.presenceOfElementLocated(Add));

			    //Checking that Element is added  
			 Assert.assertTrue(checkadd.isDisplayed());
			            
			 driver.navigate().refresh();
			       
			 driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/button")).click();
  
			 By enable = By.xpath("/html/body/div[2]/div/div[1]/form[2]/p");

		     WebElement checkenable = wait.until(ExpectedConditions.presenceOfElementLocated(enable));
		     Assert.assertTrue(checkenable.isDisplayed());			        
		     driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/button")).click();

				    
			 By disable = By.xpath("/html/body/div[2]/div/div[1]/form[2]/p");

		     // Wait for the element to be present on the page
			 WebElement checkdisable = wait.until(ExpectedConditions.presenceOfElementLocated(disable));
					   
		      //Checking that Element is added  
		     Assert.assertTrue(checkdisable.isDisplayed());
			
		    }
		    
		    
		    

		    
		    
		    @Test (priority=8)
		    public void DynamicLoading() 
		    {
		    driver.get("http://localhost:7080/dynamic_loading/2");
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/button")).click();
		    	
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		    // Find the element using a locator
		    By loadingdata = By.xpath("/html/body/div[2]/div/div/div[1]/h4");

				  
		    WebElement waittoload = wait.until(ExpectedConditions.presenceOfElementLocated(loadingdata));

				   
		    Assert.assertTrue(waittoload.isDisplayed());

		    }
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    @Test (priority=9)
		    public void DownloadFile() throws InterruptedException {
		    	driver.get("http://localhost:7080/download");
		    	driver.findElement(By.xpath("/html/body/div[2]/div/div/a")).click();
		    	Thread.sleep(2000);
		    	
		    	    String expectedFileName = "some-file.txt";
			        File downloadedFile = new File("C:\\Users\\Zeeshan-pc\\Downloads", expectedFileName);
			        
	 Assert.assertTrue(downloadedFile.exists());
		    	
		    }
                
                
      
		    
		    
            @Test (priority=10)   
            public void FileUpload()
            {	
            driver.get("http://localhost:7080/upload");
            String filePath = "C:\\\\Users\\\\Zeeshan-pc\\\\Downloads\\\\some-file.txt";
            WebElement uploadhere = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/input[1]"));
            uploadhere.sendKeys(filePath);
            	
            driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/input[2]")).click();
            	
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            	
            By file = By.xpath("/html/body/div[2]/div/div/div");
            WebElement findfile = wait.until(ExpectedConditions.visibilityOfElementLocated(file));
            Assert.assertTrue(findfile.isDisplayed());
            }
		    
            
            
            
            
            
            
            
		     @Test (priority=11)
             public void Floatingmenu() {
                	
             driver.get("http://localhost:7080/floating_menu#about");
                	
                	
             WebElement floatingMenu = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]"));
                	
             int GetPositionBefore = floatingMenu.getLocation().getY();
                
                	
             JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("window.scrollBy(0, 800)");
                	
                	
             int GetPositionAfter = floatingMenu.getLocation().getY();
                	
                	
             Assert.assertTrue(GetPositionBefore >= GetPositionAfter);
              }
                
		    
		        
		        
		        
		        
		    @Test (priority=12)
		    public void Ifrme() {
		    	
		    driver.get("http://localhost:7080/iframe");
		    	
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		    driver.findElement(By.xpath("/html/body/div[4]/div/div/button/div")).click();
		    WebElement iframe = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/div[1]/iframe"));
		    
		    driver.switchTo().frame(iframe);
		    
		    
		    WebElement textbox = driver.findElement(By.xpath("/html/body"));
		    textbox.sendKeys(Keys.CONTROL + "a");
		    textbox.sendKeys("hello");
		    
		    String  GivenText = "hello";
		    String GetText = textbox.getText();
		    
		    Assert.assertEquals(GetText, GivenText);

		    }
		 
		 
		    
		    
		    
		    
		       @Test (priority=13)
               public void hovermouse() {
                	
               driver.get("http://localhost:7080/hovers");
               Actions actions = new Actions(driver);
                
               WebElement img1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/img"));
               actions.moveToElement(img1).perform();
               WebElement img1text =  driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div/h5"));
               String getimg1text = img1text.getText();
               Assert.assertTrue(getimg1text.contains("name: user1"));
               
               WebElement img2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/img"));
               actions.moveToElement(img2).perform();
               WebElement img2text =  driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/h5"));
               String getimg2text = img2text.getText();
               Assert.assertTrue(getimg2text.contains("name: user2"));
               
               
               WebElement img3 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/img"));
               actions.moveToElement(img3).perform();
               WebElement img3text =  driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div/h5"));
               String getimg3text = img3text.getText();
               Assert.assertTrue(getimg3text.contains("name: user3"));

                }
		       
		       
		       
		       
		
		       
		       @Test (priority=14)
	           public void JavaScriptAlerts () throws InterruptedException {
			
			   driver.get("http://localhost:7080/javascript_alerts");
			   //
			   //Test Alert Js
			   driver.findElement(By.xpath("/html/body/div[2]/div/div/ul/li[1]/button")).click();
			   Alert alert = driver.switchTo().alert();
			   String alertext= alert.getText();
			   Assert.assertEquals(alertext, "I am a JS Alert");
			   alert.accept();
			   
			   // Test Alert JS Confirm
			   driver.findElement(By.xpath("/html/body/div[2]/div/div/ul/li[2]/button")).click();
			   Alert alertconfirm = driver.switchTo().alert();
			   String alertconfirmtext= alertconfirm.getText();
			   Assert.assertEquals(alertconfirmtext, "I am a JS Confirm");
			   alert.accept();
			   
			  
			  //Test Js alert prompt
			   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			   driver.findElement(By.xpath("/html/body/div[2]/div/div/ul/li[3]/button")).click();
			   Alert alertprompt = wait.until(ExpectedConditions.alertIsPresent());
			   alertprompt.sendKeys("hello");
			   alert.accept();
			   
			   WebElement result=   driver.findElement(By.xpath("/html/body/div[2]/div/div/p[2]"));
			   String myvalue = "hello";
			   String gettext = result.getText();
			   Assert.assertTrue(gettext.contains(myvalue));

	          }
		        
		        
		        
		        
		        
		        
                
              @Test (priority=15)  
              public void checkjavascripterror() {
            	  
            	  driver.get("http://localhost:7080/javascript_error");
            	  
            	  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            	  Boolean hasErrors = (Boolean) jsExecutor.executeScript("return window.javascriptErrors != null && window.javascriptErrors.length > 0");

            	  // Assert the error message
            	  if (hasErrors) {
           	      @SuppressWarnings("unchecked")					List<String> errorMessages = (List<String>) jsExecutor.executeScript("return window.javascriptErrors");
           	      for (String errorMessage : errorMessages) {
           	          if (errorMessage.contains("Cannot read property 'xyz' of undefined")) {
           	              Assert.fail("JavaScript error found: " + errorMessage);
           	          }
           	      }
           	  }
            	    
            }
              
              
              
              
              
              
             
                
              
              
              
              
                @Test (priority=16)
                public void NotificationMessage() {
                	
                    driver.get("http://localhost:7080/notification_message_rendered");
                    driver.findElement(By.xpath("/html/body/div[2]/div/div/p/a")).click();
                
                
                    WebElement getsuccess = driver.findElement(By.xpath("/html/body/div[1]/div/div"));
                    String gettext = getsuccess.getText();
              
                    for(int i = 0; i<4;i++) {
                    if (gettext.contains("Action successful") ) 
                         {
                	     Assert.assertTrue(gettext.contains("Action successful"));
                       	 break;
                         }
                    else  
                    {
                	
                	driver.findElement(By.xpath("/html/body/div[2]/div/div/p/a")).click();
                	
                    }
     
                    }

                    }
                
          
                
                
                @Test (priority=17)
                public void OpenNewTab() throws InterruptedException {
              	  
              	  driver.get("http://localhost:7080/windows");
              	  driver.findElement(By.xpath("/html/body/div[2]/div/div/a")).click();
              	  Thread.sleep(1500);
              	  driver.close();
                  driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
              	  Thread.sleep(2500);
                    WebElement gettext =  driver.findElement(By.xpath("/html/body/div"));
              	  String textis = gettext.getText();
              	  Assert.assertTrue(textis.contains("New Window"));
  	              
  	              
                }
                  
                
                	
                @Test (priority=18)
        		public void ContextMenu() throws InterruptedException 
                     {
        			
        			  driver.get("http://localhost:7080/context_menu");
        		      WebElement box = driver.findElement(By.xpath("//*[@id=\"hot-spot\"]"));
        		      Actions actions = new Actions(driver);
        		      actions.contextClick(box).perform();
        		      String alertText = driver.switchTo().alert().getText();
        		      driver.switchTo().alert().accept();
        		      Thread.sleep(10);
                      String alertrealtext = "You selected a context menu";
                      Assert.assertTrue(alertText.contains(alertrealtext));

                     
                      
                       }
                	
                	
                
                
	}


