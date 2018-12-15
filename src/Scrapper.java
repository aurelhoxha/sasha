import java.io.File;
import java.io.IOException;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	}
	
	
	public int firstSearch(Clue clue) throws InterruptedException, IOException{
		//Using Google Chrome
        driver = new ChromeDriver();
		System.out.println("Starting to search for " + clue.getQuestion());
		
		//Go to Google 
		
		driver.get("http://www.dictionary.com/fun/crosswordsolver");
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		WebElement element = driver.findElement(By.name("query"));
		WebElement element1 = driver.findElement(By.name("pattern"));
		
		String pattern = "";
		for(int i = 0; i < clue.length; i++){
			if(clue.solution[i] == '-'){
				pattern = pattern + "?";
			}
			else{
				pattern = pattern + clue.solution[i];
			}
			
		}
		
		element.sendKeys(clue.clueQuestion);
		element1.sendKeys(pattern);
		
		element.submit();
		
		// wait until the google page shows the result
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("solver-cell")));
			List<WebElement> searchResults = driver.findElements(By.cssSelector(".solver-cell"));
			ArrayList<String> options = new ArrayList<String>();
			
			for(WebElement myElements : searchResults) {
				options.add(myElements.getText());
			}
			
			for(int g = 2; g < options.size();g++)
			{
				if(g%2==0 && options.get(g).length() == clue.getLength())
					clue.addAlternative(options.get(g));
			}
			
			if(options.size() < 5) {
				if(options.get(3).equals("95%")){
					clue.setSolution(options.get(2));
					clue.setSolved(true);
					clue.printSolution();
					driver.close();
					driver.quit();
					return 1;
				}
				driver.close();
				driver.quit();
				return 0;
				
			}
			else {
				int n1 = Integer.parseInt(options.get(3).substring(0, 2));
				int n2 = Integer.parseInt(options.get(5).substring(0, 2));
				if(n1 > 90 && n1 - n2 <= 15){
					driver.close();
					driver.quit();
					return 0;
				}
				else {
					if(n1 > 90) {
						clue.setSolution(options.get(2));
						clue.setSolved(true);
						clue.printSolution();
						driver.close();
						driver.quit();
						return 1;
					}
					else{
						driver.close();
						driver.quit();
						return 0;
					}
				}
			}
		}
		catch (Exception e){
			System.out.println("No available solution");
			driver.close();
			driver.quit();
			return -1;
		}

		
		
	}

	public int secondSearch(Clue clue) {
		driver = new ChromeDriver();
		ArrayList<String> googlePages  = new ArrayList<String>();
		String[] googleResult = new String[3];
		driver.get("http://www.the-crossword-solver.com");
		WebElement element = driver.findElement(By.name("q"));
		
		//driver.findElement(By.linkText("Crossword Solver")).click();
		CharSequence searchQuery = clue.getQuestion();
		element.sendKeys(searchQuery);
		element.submit();
		try {
		// wait until the google page shows the result
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("searchresults")));
			
			List<WebElement> searchResults = driver.findElements(By.cssSelector(".searchresult > a")); 
	      
			for(WebElement myElements : searchResults) {
				
				if(myElements.getText().length() == clue.getLength()){
					clue.addAlternative(myElements.getText());
				}
			}
		}
		catch(Exception e){
			System.out.println("Sorrrrrrryyyy t qifsha robt");
		}
		
		//Click of the input text
//		WebElement element = driver.findElement(By.name("q"));
////		
////		//Prepare the search Clue
//		CharSequence searchQuery = clue.getQuestion() + " crossword clue\n";
////		
////		//Enter the Clue in the Text
//		element.sendKeys(searchQuery);
//		
//		// wait until the google page shows the result
//		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
//		
//	    
//		List<WebElement> searchResults = driver.findElements(By.cssSelector(".r > a")); 
//        
//		for(WebElement myElements : searchResults) {
//			if(!myElements.getAttribute("href").contains("translate.google.com")) {
//				googlePages.add(myElements.getAttribute("href"));
//				//System.out.println(myElements.getAttribute("href"));
//			}
//		}
		
		System.out.println("The links for the result have been saved successfully");
		
		//Visit the First Three Links and Save The Data
		driver.close();
		driver.quit();
		return 0;
		
	}
}
