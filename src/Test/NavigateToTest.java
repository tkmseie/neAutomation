package Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.apache.james.mime4j.field.datetime.DateTime;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.security.UserAndPassword;

public class NavigateToTest {
	
	
	 public static void NavigateToBrowser(WebDriver driver, String urlVal) throws IOException, AWTException, InterruptedException {
		 
		 Thread.sleep(2000);
	        System.out.println("before");
	       // WebDriver driver = new FirefoxDriver();
			 Thread.sleep(2000);
			// Dimension n = new Dimension(360,592);  
			// driver.manage().window().setSize(n);	
			 Thread.sleep(2000);

			 driver.manage().window().maximize();
            //driver.switchTo().activeElement().click();

			 Thread.sleep(5000);

	       // driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	       // driver.get("http://test.nationalequityatlas.com/about-the-atlas");

	        BrowserThread browtherThread = new BrowserThread(driver,urlVal);
	      browtherThread.start();
	        Authorizer authorizer = new Authorizer();
	        authorizer.start();
	       browtherThread.join();
	        authorizer.join();
	       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	       // driver.get("http://test.nationalequityatlas.com/about-the-atlas");
	        Thread.sleep(5000);
	        System.out.println(driver.getTitle());
	        System.out.println("after");
	      //  driver.quit();
	        
	 }

	 
}

class BrowserThread extends Thread {
    private WebDriver driver1;
    private String webURL;

    BrowserThread(WebDriver driver, String websiteURL) {
        driver1 = driver;
        webURL = websiteURL;
    }

    public void run() {
        
	   try{
    //	driver1.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

    	driver1.get(webURL);
        
        System.out.println(driver1.switchTo().alert().getText());
	   }catch(Exception e)
	   {
		   Actions builder = new Actions(driver1);
		//  builder.keyDown(Keys.COMMAND);
		 // builder.keyDown(Keys.TAB);

		  
	   }
    }
}

class Authorizer extends Thread {

    public void run() {
        try {
            Robot robot = new Robot();
            String login = "pl";
            String loginCaps = login.toUpperCase();
            String password = "h3usoh3sD";
            String passwordCaps = password.toUpperCase();
            char[] loginChars = login.toCharArray();
            char[] loginCharsCaps = loginCaps.toCharArray();
            char[] passwordChars = password.toCharArray();
            char[] passwordCharsCaps = passwordCaps.toCharArray();
            Thread.sleep(15000);
          //  robot.keyPress(KeyEvent.);
           // robot.keyPress(KeyEvent.VK_TAB);
        //    robot.keyRelease(KeyEvent.VK_ALT);
        //    robot.keyRelease(KeyEvent.VK_TAB);
            java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = screenSize.width/2;
            int centerY = screenSize.height/2-10;
            robot.mouseMove(centerX, centerY);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            for (int i = 0; i < login.length(); i++) {
                if ((loginChars[i] == loginCharsCaps[i]) & !Character.isDigit(loginChars[i])) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(loginCharsCaps[i]);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    robot.keyRelease(loginCharsCaps[i]);

                } else {
                    robot.keyPress(loginCharsCaps[i]);
                    robot.keyRelease(loginCharsCaps[i]);
                }

            }
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            for (int i = 0; i < password.length(); i++) {
                if ((passwordChars[i] == passwordCharsCaps[i]) & !Character.isDigit(passwordChars[i])) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(passwordCharsCaps[i]);
                    robot.keyRelease(passwordCharsCaps[i]);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(passwordCharsCaps[i]);
                    robot.keyRelease(passwordCharsCaps[i]);
                }


            }
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
