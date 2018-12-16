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
	
	//Two Chrome Drivers
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
	    
	    //Initialize Driver for all clues to be used
	    driver = new ChromeDriver();   
	}
	
	
	public int firstSearch(ArrayList<Clue> clues, ArrayList<Constraint> constraints) throws InterruptedException, IOException{
        for(int i = 0; i < clues.size(); i++){
			System.out.println("Starting to search for " + clues.get(i).getQuestion());
			
			//Go to Google 
			driver.get("http://www.dictionary.com/fun/crosswordsolver");
			
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement element = driver.findElement(By.name("query"));
			WebElement element1 = driver.findElement(By.name("pattern"));
			
			String pattern = "";
			for(int m = 0; m < clues.get(i).length; m++){
				if(clues.get(i).solution[m] == '-'){
					pattern = pattern + "?";
				}
				else {
					pattern = pattern + clues.get(i).solution[m];
				}
			}
			
			if(clues.get(i).clueQuestion.contains("See 1-Across") || clues.get(i).clueQuestion.contains("See 2-Across") || clues.get(i).clueQuestion.contains("See 3-Across") || clues.get(i).clueQuestion.contains("See 3-Across")
					|| clues.get(i).clueQuestion.contains("See 4-Across") || clues.get(i).clueQuestion.contains("See 5-Across") || clues.get(i).clueQuestion.contains("See 6-Across") || clues.get(i).clueQuestion.contains("See 7-Across")
					|| clues.get(i).clueQuestion.contains("See 8-Across") || clues.get(i).clueQuestion.contains("See 9-Across")
					|| clues.get(i).clueQuestion.contains("See 1-Down") || clues.get(i).clueQuestion.contains("See 2-Down") || clues.get(i).clueQuestion.contains("See 3-Down") || clues.get(i).clueQuestion.contains("See 3-Down")
					|| clues.get(i).clueQuestion.contains("See 4-Down") || clues.get(i).clueQuestion.contains("See 5-Down") || clues.get(i).clueQuestion.contains("See 6-Down") || clues.get(i).clueQuestion.contains("See 7-Down")
					|| clues.get(i).clueQuestion.contains("See 8-Down") || clues.get(i).clueQuestion.contains("See 9-Down")){}
			else {
			
				element.sendKeys(clues.get(i).clueQuestion);
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
						if(g%2==0 && options.get(g).length() == clues.get(i).getLength())
							clues.get(i).addAlternative(options.get(g));
					}
					
					if(options.size() < 5) {
						if(options.get(3).equals("95%")){
							clues.get(i).setSolution(options.get(2));
							clues.get(i).setSolved(true);
							clues.get(i).printSolution();
						}				
					}
					else {
						int n1 = Integer.parseInt(options.get(3).substring(0, 2));
						int n2 = Integer.parseInt(options.get(5).substring(0, 2));
						if(n1 > 90 && n1 - n2 > 15){
							clues.get(i).setSolution(options.get(2));
							clues.get(i).setSolved(true);
							clues.get(i).printSolution();
						}
					}
					for(int l = 0; l < clues.size(); l++){
						if(clues.get(l).isSolved()) {
							clues.get(l).updateClueAlternative();
							for(int j = 0; j < constraints.size();j++) {
								if(constraints.get(j).contains(clues.get(l))) {
									constraints.get(j).updateClue();
								}
							}
						}
					}
				}
				catch (Exception e){
					System.out.println("No available solution");
				}
        	}
        }
        return 1;
	}

	public int secondSearch(ArrayList<Clue> clues) {
		for(int i = 0; i < clues.size(); i++){
			driver.get("http://www.the-crossword-solver.com");
			WebElement element = driver.findElement(By.name("q"));
			CharSequence searchQuery = clues.get(i).getQuestion();
			
			if(clues.get(i).clueQuestion.contains("See 1-Across") || clues.get(i).clueQuestion.contains("See 2-Across") || clues.get(i).clueQuestion.contains("See 3-Across") || clues.get(i).clueQuestion.contains("See 3-Across")
					|| clues.get(i).clueQuestion.contains("See 4-Across") || clues.get(i).clueQuestion.contains("See 5-Across") || clues.get(i).clueQuestion.contains("See 6-Across") || clues.get(i).clueQuestion.contains("See 7-Across")
					|| clues.get(i).clueQuestion.contains("See 8-Across") || clues.get(i).clueQuestion.contains("See 9-Across")
					|| clues.get(i).clueQuestion.contains("See 1-Down") || clues.get(i).clueQuestion.contains("See 2-Down") || clues.get(i).clueQuestion.contains("See 3-Down") || clues.get(i).clueQuestion.contains("See 3-Down")
					|| clues.get(i).clueQuestion.contains("See 4-Down") || clues.get(i).clueQuestion.contains("See 5-Down") || clues.get(i).clueQuestion.contains("See 6-Down") || clues.get(i).clueQuestion.contains("See 7-Down")
					|| clues.get(i).clueQuestion.contains("See 8-Down") || clues.get(i).clueQuestion.contains("See 9-Down")){}
			else {
				element.sendKeys(searchQuery);
				element.submit();
				try {
				// wait until the google page shows the result
					(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("searchresults")));
					List<WebElement> searchResults = driver.findElements(By.cssSelector(".searchresult > a")); 
			      
					for(WebElement myElements : searchResults) {
						if(myElements.getText().length() == clues.get(i).getLength()){
							clues.get(i).addAlternative(myElements.getText());
						}
					}
					clues.get(i).updateClueAlternative();			
				}
				catch(Exception e){
					System.out.println("No hints for " + clues.get(i).getQuestion());
				}
			}
		}
		return 1;
	}
	
	
	public int thirdSearch(ArrayList<Clue> clues) {
		//driver = new ChromeDriver();
		for(int i = 0; i < clues.size(); i++){
			//ArrayList<String> googlePages  = new ArrayList<String>();
			//String[] googleResult = new String[3];
			driver.get("http://www.crosswordnexus.com");
			WebElement element = driver.findElement(By.name("clue"));
			WebElement element1 = driver.findElement(By.name("pattern"));
			
			//driver.findElement(By.linkText("Crossword Solver")).click();
			CharSequence searchQuery = clues.get(i).getQuestion();//
			
			String searchByLetters = "";
			for(int h = 0; h < clues.get(i).getLength(); h++){
				if(clues.get(i).solution[h] != '-'){
					searchByLetters = searchByLetters + clues.get(i).solution[h];
				}
				else {
					searchByLetters = searchByLetters + '?';
				}
			}
			
			if(clues.get(i).clueQuestion.contains("See 1-Across") || clues.get(i).clueQuestion.contains("See 2-Across") || clues.get(i).clueQuestion.contains("See 3-Across") || clues.get(i).clueQuestion.contains("See 3-Across")
					|| clues.get(i).clueQuestion.contains("See 4-Across") || clues.get(i).clueQuestion.contains("See 5-Across") || clues.get(i).clueQuestion.contains("See 6-Across") || clues.get(i).clueQuestion.contains("See 7-Across")
					|| clues.get(i).clueQuestion.contains("See 8-Across") || clues.get(i).clueQuestion.contains("See 9-Across")
					|| clues.get(i).clueQuestion.contains("See 1-Down") || clues.get(i).clueQuestion.contains("See 2-Down") || clues.get(i).clueQuestion.contains("See 3-Down") || clues.get(i).clueQuestion.contains("See 3-Down")
					|| clues.get(i).clueQuestion.contains("See 4-Down") || clues.get(i).clueQuestion.contains("See 5-Down") || clues.get(i).clueQuestion.contains("See 6-Down") || clues.get(i).clueQuestion.contains("See 7-Down")
					|| clues.get(i).clueQuestion.contains("See 8-Down") || clues.get(i).clueQuestion.contains("See 9-Down")){}
			else {
				element.sendKeys(searchQuery);
				element1.sendKeys(searchByLetters);
				element.submit();
				
				try {
				// wait until the google page shows the result
					(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
					
					List<WebElement> searchResults = driver.findElements(By.tagName("big")); 
					
					for(WebElement myElements : searchResults) {
						if(myElements.getText().length() == clues.get(i).getLength()){
							clues.get(i).addAlternative(myElements.getText());
						}
					}
					clues.get(i).updateClueAlternative();
				}
				catch(Exception e){
					System.out.println("No hints for " + clues.get(i).getQuestion());
				}
			}
		}	
		//System.out.println("The alternatives for the clues have been added successfully");
		
		//Visit the First Three Links and Save The Data
		driver.close();
		driver.quit();
		return 0;
		
	}
	
	
	
}


//Click of the input text
//WebElement element = driver.findElement(By.name("q"));
////
//////Prepare the search Clue
//CharSequence searchQuery = clue.getQuestion() + " crossword clue\n";
////
//////Enter the Clue in the Text
//element.sendKeys(searchQuery);
//
//// wait until the google page shows the result
//(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
//
//
//List<WebElement> searchResults = driver.findElements(By.cssSelector(".r > a")); 
//
//for(WebElement myElements : searchResults) {
//	if(!myElements.getAttribute("href").contains("translate.google.com")) {
//		googlePages.add(myElements.getAttribute("href"));
//		//System.out.println(myElements.getAttribute("href"));
//	}
//}
