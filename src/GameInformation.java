import java.net.*;
import java.util.*;
import java.io.*;

public class GameInformation {
	
	//Variables
	private String htmlCode;
	private ArrayList<Integer> blockPosition;
	private ArrayList<String> acrossClues;
	private ArrayList<String> downClues;
	
	//Constructor
	public GameInformation() throws Exception {
		
		//Initialization of the variables
		htmlCode = readContent();
		blockPosition = new ArrayList<Integer>();
		acrossClues = new ArrayList<String>();
		downClues = new ArrayList<String>();
	}
	
	//Read the HTML Content
	public String readContent() throws Exception {
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
		int endPosition = tempText.indexOf("</span></li></ol></div></article>");
		
		//Return the required HTML Code
		String returnText = tempText.substring(startPosition,endPosition);
		return returnText;
	}
	
	//Method that finds the cells that are blocked
	public void getBlockCells() {
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
	public void getAcrossClues() {
		//Save the starting keyword for HTML Tag for Across
		String keywordForAcrossStart = "<h3 class=\"ClueListMobile-title--3tRr-\">Across</h3>";
		//Save the ending keyword for HTML Tag for Across
		String keywordForAcrossEnd = "<h3 class=\"ClueListMobile-title--3tRr-\">Down</h3>";
		//Save Indexes for beginning and end of Across Clue
		int readBeginning = this.htmlCode.indexOf(keywordForAcrossStart);
		readBeginning = readBeginning + keywordForAcrossStart.length();
		int readEnd = this.htmlCode.indexOf(keywordForAcrossEnd);
		int textPosition = -1; 
		//Read everything between Across HTML Code
		while(readBeginning < readEnd ) {
			//Check for the character '<' and it is found increase the index until character '>'
			if(this.htmlCode.charAt(readBeginning)=='<') {
				int indexToEnd = readBeginning;
				//Skip Everything that is inside HTML Tags
				while(this.htmlCode.charAt(indexToEnd) != '>')
				{
					indexToEnd++;
				}
				readBeginning = indexToEnd+1;
			}
			//If it not HTML Tag is is Text
			else {
				//If the text is Digit add it to the ArrayList of the AcrossClues
				if(Character.isDigit(htmlCode.charAt(readBeginning))) {
					textPosition++;
					acrossClues.add(textPosition, htmlCode.charAt(readBeginning) + " ");
				}
				else {
					//After Digit there is always Corresponding Clue.
					//Add it to the same place as the number and increment the position
					String textForClue = acrossClues.get(textPosition);
					textForClue = textForClue + htmlCode.charAt(readBeginning);
					acrossClues.set(textPosition, textForClue);
				}
				readBeginning++;
			}
			
		}
		
	}
	
	public void getDownClues() {
		//Save the starting keyword for HTML Tag for Down
		String keywordForDownStart = "<h3 class=\"ClueListMobile-title--3tRr-\">Down</h3>";
		//Save Index for beginning and end of Down Clue
		int readBeginning = this.htmlCode.indexOf(keywordForDownStart);
		readBeginning = readBeginning + keywordForDownStart.length();
		int textPosition = -1; 
		//Read everything between Down HTML Code
		while(readBeginning < this.htmlCode.length() ) {
			//Check for the character '<' and it is found increase the index until character '>'
			if(this.htmlCode.charAt(readBeginning)=='<') {
				int indexToEnd = readBeginning;
				//Skip Everything that is inside HTML Tags
				while(this.htmlCode.charAt(indexToEnd) != '>') {
					indexToEnd++;
				}
				readBeginning = indexToEnd+1;
			}
			//If it not HTML Tag is is Text
			else {
				//If the text is Digit add it to the ArrayList of the DownClues
				if(Character.isDigit(htmlCode.charAt(readBeginning))) {
					textPosition++;
					downClues.add(textPosition, htmlCode.charAt(readBeginning) + " ");
				}
				else {
					//After Digit there is always Corresponding Clue.
					//Add it to the same place as the number and increment the position
					String textForClue = downClues.get(textPosition);
					textForClue = textForClue + htmlCode.charAt(readBeginning);
					downClues.set(textPosition, textForClue);
				}
				readBeginning++;
			}	
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
	

}
