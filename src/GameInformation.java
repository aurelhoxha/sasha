import java.net.*;
import java.util.*;
import java.io.*;

public class GameInformation {
	
	//Variables
	public String htmlCode;
	private ArrayList<String> acrossClues;
	private ArrayList<String> downClues;
	private Integer[] clueNumbers;
	private String dayText;
	private String dateText;
	
	//Constructor
	public GameInformation() throws Exception {
		
		//Initialization of the variables
		htmlCode = "";
		dayText = "";
		dateText = "";
		acrossClues = new ArrayList<String>();
		downClues = new ArrayList<String>();
		clueNumbers = new Integer[25];
		
		//Making the Connection with the WEBSITE
		//Taking the HTML Code
		URL oracle = new URL("https://www.nytimes.com/crosswords/game/mini");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(oracle.openStream()));
		
		String tempText = "";
		String lineText;
		while ((lineText = in.readLine()) != null) {
			tempText = tempText + lineText + "\n";
		}
		in.close();
		
		//Indexes for required HTML Code
		int startPosition = tempText.indexOf("<body>");
		String positionToEnd = "</span></li></ol></div></article>";
		int endPosition = tempText.indexOf(positionToEnd);
		endPosition = endPosition + positionToEnd.length();
		String dayTextKeyword = "<div class=\"PuzzleDetails-dayOfWeek--3rr8s\">";
		int dayTextStartPosition = tempText.indexOf(dayTextKeyword);
		dayTextStartPosition = dayTextStartPosition + dayTextKeyword.length();
		int dayTextEndPosition = tempText.indexOf("<",dayTextStartPosition);
		int dateTextStartPosition = tempText.indexOf("<",dayTextEndPosition+1);
		dateTextStartPosition = tempText.indexOf(">",dateTextStartPosition);
		int dateTextEndPosition = tempText.indexOf("<",dateTextStartPosition);
		dayText = tempText.substring(dayTextStartPosition,dayTextEndPosition);
		dateText = tempText.substring(dateTextStartPosition+1,dateTextEndPosition);
		
		//Return the required HTML Code
		String returnText = tempText.substring(startPosition,endPosition);
		htmlCode= returnText;
	}
	
		
	//Method that finds the cells that are blocked
	public void scrapeClueNumbers() {
		
		int cellsCovered = 0;
		
		while(cellsCovered <= 24) {
			
			//Save the keyword to find HTML Code for Cells
			String keywordForCellStart = "id=\"cell-id-" + cellsCovered + "\"";
			String keywordForCellEnd;
			if(cellsCovered <24 )
				keywordForCellEnd = "id=\"cell-id-" + (cellsCovered + 1) + "\"" ;
			else
				keywordForCellEnd = "</rect></g></g><g data-group=\"grid\">";
			int findCellStart = this.htmlCode.indexOf(keywordForCellStart);
			findCellStart = findCellStart + keywordForCellStart.length();
			int fincCellEnd = this.htmlCode.indexOf(keywordForCellEnd,findCellStart);
			
			//Save the Type of the Cell and if it is blocked add it to the ArrayList
			String foundText = this.htmlCode.substring(findCellStart, fincCellEnd);
			String toFoundForClues ="text-anchor=\"start\" font-size=\"33.33\">";
			String toFoundForBlock = "class=\"Cell-block--1oNaD\"";
			if(foundText.contains(toFoundForClues)) {
				int indexForClue = this.htmlCode.indexOf(toFoundForClues,findCellStart);
				indexForClue = indexForClue + toFoundForClues.length();
				int indexForEndClue = this.htmlCode.indexOf("<",indexForClue);
				String clueValue = this.htmlCode.substring(indexForClue, indexForEndClue);
				clueNumbers[cellsCovered] = Integer.parseInt(clueValue);
			}
			else if(foundText.contains(toFoundForBlock)) {
				clueNumbers[cellsCovered] = -1;
			}
			else {
				clueNumbers[cellsCovered] = 0;
			}
			cellsCovered++;
		}
		
	}
	
	//Method that find the across clues
	public void scrapeAcrossClues() {
		
		//Save the starting keyword for HTML Tag for Across
		String keywordForAcrossStart = "<h3 class=\"ClueListMobile-title--3tRr-\">Across</h3>";
		
		//Save the ending keyword for HTML Tag for Across
		String keywordForAcrossEnd = "<h3 class=\"ClueListMobile-title--3tRr-\">Down</h3>";
		
		//Save Indexes for beginning and end of Across Clue
		int readBeginning = this.htmlCode.indexOf(keywordForAcrossStart);
		readBeginning = readBeginning + keywordForAcrossStart.length();
		int readEnd = this.htmlCode.indexOf(keywordForAcrossEnd);
		
		//Read everything between Across HTML Code
		while(readBeginning < readEnd ) {
			
			//Save keyword that is used to take the number of Question
			String keywordForStartNumbersAcross = "<span class=\"Clue-label--2IdMY\">";
			String keywordFornEndNumberAcross = "</span><span class=\"Clue-text--3lZl7\">";
			int numberReadingStart = this.htmlCode.indexOf(keywordForStartNumbersAcross,readBeginning);
			numberReadingStart = numberReadingStart + keywordForStartNumbersAcross.length();
			int numberReadingEnd = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingStart);
			
			//Save the number of the Question
			String acrossNumber = this.htmlCode.substring(numberReadingStart, numberReadingEnd);
			
			//Start Searching for the Corresponding Question
			int clueReadingStart = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingEnd);
			clueReadingStart = clueReadingStart + keywordFornEndNumberAcross.length();
			int clueReadingEnd = this.htmlCode.indexOf("<", clueReadingStart);
			
			//Save the Content of the Question
			String acrossClue = this.htmlCode.substring(clueReadingStart, clueReadingEnd);
			
			//Add both number and Clue to the the AcrossClue Array
			acrossClues.add(acrossNumber + " " + acrossClue);
			
			//Stop executing when the keyword that notifies the end of Question is found
			String keywordForStop = "</span></li></ol></div>";
			String processedCode = this.htmlCode.substring(readBeginning, clueReadingEnd + keywordForStop.length());
			if(processedCode.contains(keywordForStop))
				readBeginning = readEnd;
			else
				readBeginning = clueReadingEnd;
		}
		
	}
	
	public void scrapeDownClues() {
		
		//Save the starting keyword for HTML Tag for Down
		String keywordForDownStart = "<h3 class=\"ClueListMobile-title--3tRr-\">Down</h3>";
		
		//Save Index for beginning and end of Down Clue
		int readBeginning = this.htmlCode.indexOf(keywordForDownStart);
		readBeginning = readBeginning + keywordForDownStart.length();
		
		//Read everything between Down HTML Code
		while(readBeginning < this.htmlCode.length() ) {
			
			//Save keyword that is used to take the number of Question
			String keywordForStartNumbersAcross = "<span class=\"Clue-label--2IdMY\">";
			String keywordFornEndNumberAcross = "</span><span class=\"Clue-text--3lZl7\">";
			int numberReadingStart = this.htmlCode.indexOf(keywordForStartNumbersAcross,readBeginning);
			numberReadingStart = numberReadingStart + keywordForStartNumbersAcross.length();
			int numberReadingEnd = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingStart);
			
			//Save the number of the Question
			String acrossNumber = this.htmlCode.substring(numberReadingStart, numberReadingEnd);
			
			//Start Searching for the Corresponding Question
			int clueReadingStart = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingEnd);
			clueReadingStart = clueReadingStart + keywordFornEndNumberAcross.length();
			int clueReadingEnd = this.htmlCode.indexOf("<", clueReadingStart);
			
			//Save the Content of the Question
			String acrossClue = this.htmlCode.substring(clueReadingStart, clueReadingEnd);
			
			//Add both number and Clue to the the DownClue Array
			downClues.add(acrossNumber + " " + acrossClue);
			
			//Stop executing when the keyword that notifies the end of Question is found
			String keywordForStop = "</span></li></ol></div></article>";
			String processedCode = this.htmlCode.substring(readBeginning, clueReadingEnd + keywordForStop.length());
			if(processedCode.contains(keywordForStop))
				readBeginning = this.htmlCode.length();
			else
				readBeginning = clueReadingEnd;	
		}
	}
	
	//Print the Down Clues
	public void printDownClues() {
		for(int i = 0; i < downClues.size();i++)
			System.out.println(downClues.get(i));
	}

	//Print the Across Clues
	public void printAcrossClues() {
		for(int i = 0; i < acrossClues.size();i++)
			System.out.println(acrossClues.get(i));
	}
	
	
	//Get the ArrayList of Block Cells
	public Integer[] getClueNumbers() {
		return clueNumbers;
	}
	
	//Get the ArrayList of Across Clues
	public ArrayList<String> getAcrossClues() {
		return acrossClues;
	}
	
	//Get the ArrayList of Down Clues
	public ArrayList<String> getDownClues() {
		return downClues;
	}
	
	//Get the Text of GameDay
	public String getGameDay() {
		return dayText;
	}
	
	//Get the Text of GameDate
	public String getGameDate() {
		return dateText;
	}
	
	//Print the Block Cells
	public void printArrayCells() {
		for(int i = 0; i < clueNumbers.length;i++)
			System.out.println("At position " + i + " value : " + clueNumbers[i]);
	}
		
	
	
	

}
