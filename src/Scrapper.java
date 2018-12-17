import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
	boolean googleDriverClosed = true;
	
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
		ChromeOptions chromeOptions = new ChromeOptions();  
	    chromeOptions.addArguments("--headless");  
	    chromeOptions.addArguments("--disable-gpu");  
	    driver1 = new ChromeDriver(chromeOptions);   
	    driver = new ChromeDriver();
	    
	}
	
	public void googleSearch(ArrayList<Clue> clues, ArrayList<Constraint> constraints) throws InterruptedException, IOException, Exception {
		
		for(int i = 0; i < clues.size(); i++) {
			if(!clues.get(i).isSolved()) {
				ArrayList<String> googlePages  = new ArrayList<String>();
				System.out.println("\nGoogling possible solutions for: " + clues.get(i).getQuestion());
				driver.get("https://www.google.com/");
				WebElement element = driver.findElement(By.name("q"));
				
				//Prepare the search Clue
				CharSequence searchQuery = clues.get(i).getQuestion() + " crossword clue\n";
				//Enter the Clue in the Text
				element.sendKeys(searchQuery);
				try {
					// wait until the google page shows the result
					(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
					List<WebElement> searchResults = driver.findElements(By.cssSelector(".r > a")); 
					for(WebElement myElements : searchResults) {
						if(!myElements.getAttribute("href").contains("translate.google.com")) {
							googlePages.add(myElements.getAttribute("href"));
						}
					}
					System.out.println("Results for Clue: " + clues.get(i).getQuestion());
					System.out.println("-------------------------------------");
					ArrayList<String> googlePagesDeleted  = new ArrayList<String>();
					boolean firstCheck = false;
					boolean secondCheck = false;
					for(int j = 0; j < googlePages.size(); j++)
					{
						if(googlePages.get(j).contains("wordplays")) {
							googlePagesDeleted.add(googlePages.get(j));
						}
						if(googlePages.get(j).contains(".pdf")) {
							googlePagesDeleted.add(googlePages.get(j));
						}
						if(googlePages.get(j).contains(".txt")) {
							googlePagesDeleted.add(googlePages.get(j));
						}
						if(googlePages.get(j).contains("the-crossword-solver.com")) {
							firstCheck = true;
							
						}
						if(googlePages.get(j).contains("crosswordnexus.com")) {
							secondCheck = true;
						}
					}
					
					for(int j = 0; j < googlePagesDeleted.size(); j++) {
						googlePages.remove(googlePagesDeleted.get(j));
					}
					for(int j = 0; j < googlePages.size(); j++)
					{
						System.out.println(googlePages.get(j));
						try {
							String tempText = "";
							//Making the Connection with the WEBSITE
							//Taking the HTML Code
							//System.out.println("Connecting to the NY MiniPuzzle");
							URL oracle = new URL(googlePages.get(j));
							BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
							String lineText;
							while ((lineText = in.readLine()) != null) {
								tempText = tempText + lineText + "\n";
							}
							in.close();
							
							Document doc = Jsoup.parse(tempText);
							String bodyText = doc.body().text();
							System.out.println("--------------------------------------------------------------------");
							String[] bodyWords = bodyText.split(" ");
							for(int z = 0; z < bodyWords.length; z++) {
								bodyWords[z] = bodyWords[z].replaceAll("[\\.$|,|;|'|!|?|@|#|%|^|&|*|(|)|_|-|+|=|{|}|:|<|>|\"|‘|’|-|/|×|…|«]", "");
							}
							for(int z = 0; z < bodyWords.length; z++) {
								if(bodyWords[z].length() == clues.get(i).getLength())
									clues.get(i).addAlternative(bodyWords[z].toUpperCase());
							}
							System.out.println("---------------------------------------------------------------------");
						}
						catch(Exception e) {
							System.out.println("Robot Check Detected");
							System.out.println("---------------------------------------------------------------------");
						}
					}
				}
				catch(Exception e){
					System.out.println("No Google results found");
					System.out.println("---------------------------------------------------------------------");
				}
			}
			
		}
		driver.close();
		driver.quit();
		googleDriverClosed = true;
	}
	
	public int firstSearch(ArrayList<Clue> clues, ArrayList<Constraint> constraints) throws InterruptedException, IOException{
        for(int i = 0; i < clues.size(); i++){
			System.out.println("Starting to search certain answer for: " + clues.get(i).getQuestion());
			
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
						}				
					}
					else {
						int n1 = Integer.parseInt(options.get(3).substring(0, 2));
						int n2 = Integer.parseInt(options.get(5).substring(0, 2));
						if(n1 > 90 && n1 - n2 > 15){
							clues.get(i).setSolution(options.get(2));
							clues.get(i).setSolved(true);
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
					System.out.println("No solutions found for " + clues.get(i).getQuestion());
				}
        	}
        }
        return 1;
	}
	
	public int thirdSearch(ArrayList<Clue> clues, ArrayList<Constraint> constraints) {
		for(int i = 0; i < clues.size(); i++){
			if(!clues.get(i).isSolved()) {
				driver.get("https://www.google.com/");
				WebElement elementGoogle = driver.findElement(By.name("q"));
				
				System.out.println("\nGoogling possible solutions for: " + clues.get(i).getQuestion());
				
				//Prepare the search Clue
				CharSequence searchQueryGoogle = clues.get(i).getQuestion() + "\n";
				//Enter the Clue in the Text
				elementGoogle.sendKeys(searchQueryGoogle);
				
				driver1.get("http://www.crosswordnexus.com");
				WebElement element = driver1.findElement(By.name("clue"));
				WebElement element1 = driver1.findElement(By.name("pattern"));
				
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
						(new WebDriverWait(driver1, 10)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
						
						List<WebElement> searchResults = driver1.findElements(By.tagName("big")); 
						List<WebElement> rows = driver1.findElements(By.tagName("tr")); 
						ArrayList<Integer> stars = new ArrayList<Integer>();
						
						int t = 1;
						for(WebElement myElements : rows) {
							List<WebElement> starsCounter = myElements.findElements(By.tagName("img"));
							stars.add(starsCounter.size());
							//System.out.println("Visiting link number " + t++);
						}
						
						int counter = 0;
						for(WebElement myElements : searchResults) {
							if(myElements.getText().length() == clues.get(i).getLength()){
								if(counter == 0 && ((stars.get(0) >= 3 && stars.get(1) < 2 ) || (stars.get(0) == 4 && stars.get(1) < 3))) {
									clues.get(i).setSolved(true);
									clues.get(i).setSolution(myElements.getText());
									clues.get(i).updateClueAlternative();
								}
								else {
									clues.get(i).addAlternative(myElements.getText());
								}
								counter++;
							}
						}
						clues.get(i).updateClueAlternative();
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
						System.out.println("All links were visited and data is collected");
					}
					catch(Exception e){
						System.out.println("All links were visited and data is collected");
					}
				}
				
			}
		}		
		return 0;
	}


	public int fourthSearch(ArrayList<Clue> clues, ArrayList<Constraint> constraints) {
		for(int i = 0; i < clues.size(); i++){
			if(!clues.get(i).isSolved()) {
				if(googleDriverClosed == false) {
					driver.get("https://www.google.com/");
					WebElement elementGoogle = driver.findElement(By.name("q"));
					
					//Prepare the search Clue
					CharSequence searchQueryGoogle = clues.get(i).getQuestion() + "\n";
					//Enter the Clue in the Text
					elementGoogle.sendKeys(searchQueryGoogle);
				}
				driver1.get("http://www.crosswordnexus.com");
				WebElement element = driver1.findElement(By.name("pattern"));
				
				int counter1 = 0;
				String searchByLetters = "";
				for(int h = 0; h < clues.get(i).getLength(); h++){
					if(clues.get(i).solution[h] != '-'){
						searchByLetters = searchByLetters + clues.get(i).solution[h];
						counter1++;
					}
					else {
						searchByLetters = searchByLetters + '?';
					}
				}
				
				if((clues.get(i).length > 3 && counter1 > 2) || (clues.get(i).length == 3 && counter1 >= 2) ){
					element.sendKeys(searchByLetters);
					element.submit();
					
					try {
					// wait until the google page shows the result
						(new WebDriverWait(driver1, 10)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
						
						List<WebElement> searchResults = driver1.findElements(By.tagName("big")); 
						List<WebElement> rows = driver1.findElements(By.tagName("tr")); 
						ArrayList<Integer> stars = new ArrayList<Integer>();
						
						for(WebElement myElements : rows) {
							List<WebElement> starsCounter = myElements.findElements(By.tagName("img"));
							stars.add(starsCounter.size());
							//System.out.println("This one has STARS =  " + starsCounter.size() );
						}
						
						for(WebElement myElements : searchResults) {
							if(myElements.getText().length() == clues.get(i).getLength()){
								clues.get(i).addAlternative(myElements.getText());
							}
						}
						clues.get(i).updateClueAlternative();
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
					catch(Exception e){
						//System.out.println("No hints for " + clues.get(i).getQuestion());
					}
				}
			}
			}
				
		return 0;
	}
		
}
