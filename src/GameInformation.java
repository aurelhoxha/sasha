import java.net.*;
import java.util.*;
import java.io.*;

public class GameInformation {
	
	//Variables
	public String htmlCode;
	private ArrayList<Integer> blockPosition;
	private ArrayList<String> acrossClues;
	private ArrayList<String> downClues;
	private String dayText;
	private String dateText;
	
	//Constructor
	public GameInformation() throws Exception {
		
		//Initialization of the variables
		htmlCode = "";
		dayText = "";
		dateText = "";
		blockPosition = new ArrayList<Integer>();
		acrossClues = new ArrayList<String>();
		downClues = new ArrayList<String>();
		
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
	public void scrapeBlockCells() {
		//
		int readPosition = 0;
		int cellsCovered = 0;
		
		while(cellsCovered <= 24) {
			
			//Save the keyword to find HTML Code for Cells
			String keywordForBlock = "id=\"cell-id-" + cellsCovered + "\"" + " class=\"Cell-";
			int findCellStart = this.htmlCode.indexOf(keywordForBlock,readPosition);
			int searchPosition = findCellStart + keywordForBlock.length();
			
			//Save the Type of the Cell and if it is blocked add it to the ArrayList
			String foundType = this.htmlCode.substring(searchPosition, searchPosition + 5);
			if(foundType.equals("block"))
				blockPosition.add(cellsCovered);
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
			String keywordForStartNumbersAcross = "<span class=\"Clue-label--2IdMY\">";
			String keywordFornEndNumberAcross = "</span><span class=\"Clue-text--3lZl7\">";
			int numberReadingStart = this.htmlCode.indexOf(keywordForStartNumbersAcross,readBeginning);
			numberReadingStart = numberReadingStart + keywordForStartNumbersAcross.length();
		
			int numberReadingEnd = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingStart);
			String acrossNumber = this.htmlCode.substring(numberReadingStart, numberReadingEnd);
			int clueReadingStart = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingEnd);
			clueReadingStart = clueReadingStart + keywordFornEndNumberAcross.length();
			int clueReadingEnd = this.htmlCode.indexOf("<", clueReadingStart);
			String acrossClue = this.htmlCode.substring(clueReadingStart, clueReadingEnd);
			acrossClues.add(acrossNumber + " " + acrossClue);
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
			String keywordForStartNumbersAcross = "<span class=\"Clue-label--2IdMY\">";
			String keywordFornEndNumberAcross = "</span><span class=\"Clue-text--3lZl7\">";
			int numberReadingStart = this.htmlCode.indexOf(keywordForStartNumbersAcross,readBeginning);
			numberReadingStart = numberReadingStart + keywordForStartNumbersAcross.length();
		
			int numberReadingEnd = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingStart);
			String acrossNumber = this.htmlCode.substring(numberReadingStart, numberReadingEnd);
			System.out.print(acrossNumber + " ");
			int clueReadingStart = this.htmlCode.indexOf(keywordFornEndNumberAcross,numberReadingEnd);
			clueReadingStart = clueReadingStart + keywordFornEndNumberAcross.length();
			int clueReadingEnd = this.htmlCode.indexOf("<", clueReadingStart);
			String acrossClue = this.htmlCode.substring(clueReadingStart, clueReadingEnd);
			System.out.println(acrossClue);
			downClues.add(acrossNumber + " " + acrossClue);
			String keywordForStop = "</span></li></ol></div></article>";
			String processedCode = this.htmlCode.substring(readBeginning, clueReadingEnd + keywordForStop.length());
			if(processedCode.contains(keywordForStop))
				readBeginning = this.htmlCode.length();
			else
				readBeginning = clueReadingEnd;	
		}
	}
	public void printDownClues() {
		for(int i = 0; i < downClues.size();i++)
			System.out.println(downClues.get(i));
	}

	public void printAcrossClues() {
		for(int i = 0; i < acrossClues.size();i++)
			System.out.println(acrossClues.get(i));
	}

	public void printBlockCells() {
		for(int i = 0; i < blockPosition.size();i++)
			System.out.println(blockPosition.get(i));
	}
	
	public ArrayList<Integer> getBlockCells() {
		return blockPosition;
	}
	
	public ArrayList<String> getAcrossClues() {
		return acrossClues;
	}
	
	public ArrayList<String> getDownClues() {
		return downClues;
	}
	
	public String getGameDay() {
		return dayText;
	}
	
	public String getGameDate() {
		return dateText;
	}
	
	
	

}
