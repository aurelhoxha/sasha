import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Scrapper {
	File chrome;
	File chrome1;
	WebDriver driver;
	
	public Scrapper(){
	    chrome = new File("./src/chromedriver.exe");
	    chrome1 = new File("./src/chromedriver");
	    String os = System.getProperty("os.name").toLowerCase();
	    if(os.contains("mac")) {
	    	System.setProperty("webdriver.chrome.driver", chrome1.getAbsolutePath());
	    }
	    else {
	    	System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
	    }
        
	    //Using Google Chrome
        driver = new ChromeDriver();
	}
	
	
	public void search(Clue clue){
		ArrayList<String> googlePages  = new ArrayList<String>();
		ArrayList<String> alternatives = new ArrayList<String>();
		String[] googleResult = new String[3];
		System.out.println("Starting to search for " + clue.getQuestion());
		
		//Go to Google 
		driver.get("http://www.google.com");
		
		//Click of the input text
		WebElement element = driver.findElement(By.name("q"));
		
		//Prepare the search Clue
		CharSequence searchQuery = clue.getQuestion() + " crossword clue\n";
		
		//Enter the Clue in the Text
		element.sendKeys(searchQuery);
		
		// wait until the google page shows the result
	    WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
	    
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        
        for (WebElement webElement : searchResults)
        {
        	System.out.println(webElement.getAttribute("href"));
            //googlePages.add(webElement.getAttribute("href"));
        }
        
//        for (int j = 0; j < googleResult.length; j++)
//        {
//            // Might not have sufficient hits or unusable hits
//            try
//            {
//                driver.get(googlePages.get(j));
//                (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
//                WebElement text = driver.findElement(By.tagName("body"));
//                if( text.getText().equals("Please Sign in")) {
//                    driver.findElement(By.xpath("/html/body/a")).click();
//                    driver.navigate().back();
//                    text = driver.findElement(By.tagName("body"));
//                }
//                googleResult[j] = text.getText();
//                System.out.println("Data from " + " Google Hit number " + (j+1) + " Retrieved!");
//               // main.setTextForTrace("Data from " + " Google Hit number " + (j+1) + " Retrieved!");
//               //trace.add("Data from " + " Google Hit number " + (j+1) + " Retrieved!");
//            }
//            catch(RuntimeException ex)
//            {
//                System.out.println("Unable to retrieve data from Google Hit number " + (j+1));
//                //trace.add("Unable to retrieve data from Google Hit number " + (j+1));
//            }
//        }
//		
//        for(int i = 0; i < googleResult.length; i++){
//        	System.out.println(googleResult[1]);
//        }
		
	}
}
