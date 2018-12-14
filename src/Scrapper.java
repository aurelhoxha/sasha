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
	
	//Files for Mac and Windows
	File chrome;
	File chrome1;
	
	//Two Chrome
	WebDriver driver;
	WebDriver driver1;
	
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
		String[] googleResult = new String[3];
		System.out.println("Starting to search for " + clue.getQuestion());
		
		//Go to Google 
		driver.get("http://www.dictionary.com/fun/crosswordsolver");
		
		WebElement element = driver.findElement(By.name("query"));
		
		element.sendKeys("Groceries holder");
		driver.findElement(By.className("submit")).click();
		
		//Click of the input text
//		WebElement element = driver.findElement(By.name("q"));
//		
//		//Prepare the search Clue
//		CharSequence searchQuery = clue.getQuestion() + " crossword clue\n";
//		
//		//Enter the Clue in the Text
//		element.sendKeys(searchQuery);
//		
//		// wait until the google page shows the result
//		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
//	    
//		List<WebElement> searchResults = driver.findElements(By.cssSelector(".r > a")); 
//        
//		for(WebElement myElements : searchResults) {
//			if(!myElements.getAttribute("href").contains("translate.google.com")) {
//				googlePages.add(myElements.getAttribute("href"));
//				//System.out.println(myElements.getAttribute("href"));
//			}
//		}
		
		System.out.print("The links for the result have been saved successfully");
		
		//Visit the First Three Links and Save The Data
		
	}
}
