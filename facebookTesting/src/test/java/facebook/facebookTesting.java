package facebook;
import java.io.File;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;




public class facebookTesting {
	
	private WebDriver driver;

//* Initialize WebElement*
	
	static By email = By.id("email");
	static By pass = By.id("pass");
	static By login = By.name("login");
	static By search = By.xpath("//input[@type='search']");

 
//*Initialize Chrome driver and use chromeOptions to disable notifications*	
	
  @BeforeClass
  public void beforeClass() {
	  ChromeOptions options = new ChromeOptions();
	  options.addArguments("--disable-notifications");
	  
	  System.setProperty("webdriver.chrome.driver", "D:\\Selenium Training\\chromedriver.exe");

		 driver=new ChromeDriver(options);
		 
  }
  
  //*Initilaize the credentials username and password to be used*
  
  @DataProvider(name="TestData")
	public Object [][] getData(){
		Object [][] data = new Object[1][2];
		data[0][0] = "Amr Metawea";		
		data[0][1] = "metawea31";

		return data;
				}

  @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }
  
  
  //*Open Facebook website and maximize the screen*
  
  @Test
  public void f() throws InterruptedException, IOException {
	  
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	  

	  
      driver.get("https://www.facebook.com");
      driver.manage().window().maximize();
      
      
  }
  
  //*Login to facebook using the credentials in TestData and check that logged in successfully*
  
  @Test(dataProvider = "TestData")
	public  void login(String userName , String password) throws InterruptedException {
		

		
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(userName);
		driver.findElement(pass).clear();
		driver.findElement(pass).sendKeys(password);	
		driver.findElement(login).click();
		Thread.sleep(5000);

	assertEquals(driver.getTitle(), "Facebook" );
		
	}
  
  //*Search on Trella app using search box and check it's functionality*
  
  @Test
  public void search() throws InterruptedException, IOException {
	  driver.findElement(search).sendKeys("trella",Keys.ENTER, Keys.ENTER);
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	 	Screenshot screenshot4 = new AShot().takeScreenshot(driver);
	    ImageIO.write(screenshot4.getImage(), "jpg", new File("C:\\Users\\Amr Mostafa\\Trella\\TrellaSearch.jpg"));
			  
  
  }

}
