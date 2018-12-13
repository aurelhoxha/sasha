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
	WebDriver driver;
	WebDriver driver2;
	
	public Scrapper(){
	    chrome = new File("src/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        driver = new ChromeDriver();  // Using Firefox
        driver2 = new ChromeDriver();
	}
	
	
	public ArrayList<String> search(Clue clue){
		ArrayList<String> alternatives = new ArrayList<String>();
		
		
		
		
		
		
		return alternatives;
	}
}
