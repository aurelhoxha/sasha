// CLASS THAT WILL GET THE URL, OR DATE AND SCRAPE CLUES and BLOCK CELLS 

import java.net.*;
import java.util.*;
import java.io.*;

public class GameInformation {
	//Variables
	public String htmlCode;
	private ArrayList<String> acrossClues;
	private ArrayList<String> downClues;
	private Integer[] clueNumbers;
	public ArrayList<Constraint> constraints;
	
	private String dayText;
	private String dateText;
	public String tempText;
	public Integer[][] matrix = new Integer[5][5];
	public int numOfClues = 0;
	
	/////////////////////////
	////SECOND DEMO CODE////
	////////////////////////
	private ArrayList<Clue> clues;
	
	
	////////////////////////
	
	//Constructor
	public GameInformation(String thePath) throws Exception {
		//Initialization of the variables
		htmlCode = "";
		dayText = "";
		dateText = "";
		acrossClues = new ArrayList<String>();
		downClues = new ArrayList<String>();
		clues = new ArrayList<Clue>();
		clueNumbers = new Integer[25];
		tempText = "";
		
		//If the path is not today's date...
		if(!thePath.substring(thePath.length()-4).equals("mini")) {
			File file = new File("./oldPuzzles/" + thePath + "/htmlCode.txt"); 
			Scanner sc = new Scanner(file); 
			//System.out.println("Looking for the selected date in old puzzles");
			while (sc.hasNextLine())
				tempText = tempText + sc.nextLine() + "\n";
		}
		else {
			//Making the Connection with the WEBSITE
			//Taking the HTML Code
			//System.out.println("Connecting to the NY MiniPuzzle");
			URL oracle = new URL(thePath);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));
			String lineText;
			while ((lineText = in.readLine()) != null) {
				tempText = tempText + lineText + "\n";
			}
			in.close();
		}
		
		//Indexes for required HTML Code
		//Find the Beginning of the Body and end of the Body HTML Code
		int startPosition = tempText.indexOf("<body>");
		String positionToEnd = "</span></li></ol></div></article>";
		int endPosition = tempText.indexOf(positionToEnd);
		endPosition = endPosition + positionToEnd.length();

		//Find the Date Information Details of the Puzzle
		String dayTextKeyword = "<div class=\"PuzzleDetails-dayOfWeek--3rr8s\">";
		int dayTextStartPosition = tempText.indexOf(dayTextKeyword);
		dayTextStartPosition = dayTextStartPosition + dayTextKeyword.length();
		int dayTextEndPosition = tempText.indexOf("<",dayTextStartPosition);
		int dateTextStartPosition = tempText.indexOf("<",dayTextEndPosition+1);
		dateTextStartPosition = tempText.indexOf(">",dateTextStartPosition);
		int dateTextEndPosition = tempText.indexOf("<",dateTextStartPosition);

		//Register the Date Information Accordingly
		dayText = tempText.substring(dayTextStartPosition,dayTextEndPosition);
		dateText = tempText.substring(dateTextStartPosition+1,dateTextEndPosition);
		
		//Return the required HTML Code
		String returnText = tempText.substring(startPosition,endPosition);
		htmlCode= returnText;
	}
	
	//Generate puzzle as it is in reality
	public void generateMatrix(){
		matrix[0][0] = clueNumbers[0];
		matrix[0][1] = clueNumbers[1];
		matrix[0][2] = clueNumbers[2];
		matrix[0][3] = clueNumbers[3];
		matrix[0][4] = clueNumbers[4];
		matrix[1][0] = clueNumbers[5];
		matrix[1][1] = clueNumbers[6];
		matrix[1][2] = clueNumbers[7];
		matrix[1][3] = clueNumbers[8];
		matrix[1][4] = clueNumbers[9];
		matrix[2][0] = clueNumbers[10];
		matrix[2][1] = clueNumbers[11];
		matrix[2][2] = clueNumbers[12];
		matrix[2][3] = clueNumbers[13];
		matrix[2][4] = clueNumbers[14];
		matrix[3][0] = clueNumbers[15];
		matrix[3][1] = clueNumbers[16];
		matrix[3][2] = clueNumbers[17];
		matrix[3][3] = clueNumbers[18];
		matrix[3][4] = clueNumbers[19];
		matrix[4][0] = clueNumbers[20];
		matrix[4][1] = clueNumbers[21];
		matrix[4][2] = clueNumbers[22];
		matrix[4][3] = clueNumbers[23];
		matrix[4][4] = clueNumbers[24];
	}
	
	//Print puzzle as matrix
	public void printPuzzle(){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(matrix[i][j] >= 0)
					System.out.print(" ");
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//Method that finds the cells that are blocked
	public void scrapeClueNumbers() {	
		int cellsCovered = 0;
		//System.out.println("Finding block cells");
		while(cellsCovered <= 24) {
			//Save the keyword to find HTML Code for Cells
			String keywordForCellStart = "id=\"cell-id-" + cellsCovered + "\"";
			String keywordForCellEnd;
			if(cellsCovered <24 )
				keywordForCellEnd = "id=\"cell-id-" + (cellsCovered + 1) + "\"" ;
			else
				keywordForCellEnd = "</g><g data-group=\"grid\">";
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
		//System.out.println("Generating Across Clues from HTML");
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
			
			///////////////////////////////////////////////////////////////////////////////////
			Clue clue = new Clue(acrossClue, Integer.parseInt(acrossNumber), 0, -1, -1, "across");
			clues.add(clue);
			///////////////////////////////////////////////////////////////////////////////////
			
			
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
		//System.out.println("Generating Down Clues from HTML");
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
			
			///////////////////////////////////////////////////////////////////////////////////
			Clue clue = new Clue(acrossClue, Integer.parseInt(acrossNumber), 0, -1, -1, "down");
			clues.add(clue);
			///////////////////////////////////////////////////////////////////////////////////
			
			//Stop executing when the keyword that notifies the end of Question is found
			String keywordForStop = "</span></li></ol></div></article>";
			String processedCode = this.htmlCode.substring(readBeginning, clueReadingEnd + keywordForStop.length());
			if(processedCode.contains(keywordForStop))
				readBeginning = this.htmlCode.length();
			else
				readBeginning = clueReadingEnd;	
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	public void printCluesAndQuestionsAndLengths(){
		System.out.println("\nAcross clues are:");
		for(int i = 0; i < clues.size(); i++){
			if(clues.get(i).direction.equals("across")){	
				System.out.print(clues.get(i).clueNumber + " ");
				System.out.print(clues.get(i).clueQuestion + " ");
				System.out.println(clues.get(i).length);
			}
		}
		
		System.out.println("\nDown clues are:");
		for(int i = 0; i < clues.size(); i++){
			if(clues.get(i).direction.equals("down")){	
				System.out.print(clues.get(i).clueNumber + " ");
				System.out.print(clues.get(i).clueQuestion + " ");
				System.out.println(clues.get(i).length);
			}
		}
	}
	///////////////////////////////////////////////////////////////////////////////////
	
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

	//Print Matching Cells(Constraints)
	public void determineConstraints(){
		int matchAcross = -1;
		int matchDown = -1;
		int toGoLeft = -1;
		int toGoUp = -1;
		int amountLeft = 0;
		int amountUp = 0;
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(matrix[i][j] == -1){
					System.out.println("Cell " + i + ", " + j + " is a block cell");
				}
				else {
				    toGoLeft = j;
				    toGoUp = i;
				    matchAcross = matrix[i][j];
				    matchDown = matrix[i][j];
					
				    while(toGoLeft >= 0){
				    	if(matrix[i][toGoLeft] > 0) 
				    		matchAcross = matrix[i][toGoLeft];
				    	
				    	if(matrix[i][toGoLeft] == -1)
				    		break;
				    	
				    	toGoLeft--;
				    }
				    amountLeft = j - toGoLeft -1;				    
				    
				    while(toGoUp >= 0){
				    	if(matrix[toGoUp][j] > 0)
				    		matchDown = matrix[toGoUp][j];
				    	
				    	if(matrix[toGoUp][j] == -1)
				    		break;
				    	
				    	toGoUp--;
				    }
				    amountUp = i - toGoUp -1;
					
					System.out.println("Cell " + i + ", " + j + " is clue " + matchAcross + " across at index " + amountLeft + " and clue " + matchDown + " down at index " + amountUp );
					
				}
			}
			
		}
	}

	//Print Lengths of Clues
	public void generateLengths(){
		int clue = -1;
		int length = 0;		

		for(int i = 0; i < 5; i ++){
			for(int k = 4; k >= 0; k--){
				if(matrix[i][k] > 0)
					clue = matrix[i][k];
			
				if(matrix[i][k] != -1)
					length++;
			}	
			//System.out.println("Clue " + clue + " across has length of " + length);
			
			for(int l = 0; l < clues.size(); l++){
				if(clues.get(l).clueNumber == clue && clues.get(l).direction.equals("across")){
					clues.get(l).setLength(length);
				}		
			}
			
			length = 0;
		}
		
		for(int i = 0; i < 5; i ++){
			for(int k = 4; k >= 0; k--){
				if(matrix[k][i] > 0)
					clue = matrix[k][i];
			
				if(matrix[k][i] != -1)
					length++;
			}	
			//System.out.println("Clue " + clue + " down has length of " + length);
			
			for(int l = 0; l < clues.size(); l++){
				if(clues.get(l).clueNumber == clue && clues.get(l).direction.equals("down")){
					clues.get(l).setLength(length);
				}		
			}			
			length = 0;
		}
		System.out.println("\n");
	}
}


