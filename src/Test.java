import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import javax.swing.*;

public class Test extends JFrame{
	static String selection = "Today";
	static String oldSelection = "";
	static String gameDate = "";
	static boolean store = false;
	static boolean solve = false;
	
	public static void main(String[] args) throws Exception {	
		//Variables to save the data of the game
		Integer[] myClueNumber = new Integer[25];
		ArrayList<String> myAcrossClues = new ArrayList<String>();
		ArrayList<String> myDownClues = new ArrayList<String>();
		String gameDay = "";	
		GameInformation myGame = null;
		String adr = "";
		
//		myGame = new GameInformation("https://www.nytimes.com/crosswords/game/mini"); //November 16, 2018
//		myGame.scrapeClueNumbers();
//		myGame.scrapeAcrossClues();
//		myGame.scrapeDownClues();
//		//myGame.printArrayCells();
//		myGame.generateMatrix();
//		myGame.printPuzzle();
//		myGame.printMatchingCells();
//		myGame.printLengths();
		
		//Creation of the Game
		JFrame myGameFrame = new JFrame("Sasha");;
		
		while(true) {
			if(!selection.equals(oldSelection)) {
				myGameFrame.dispose();
				myGameFrame = new JFrame("Sasha");
				
				//If user chooses today then he is displayed the puzzle from Internet, otherwise from stored ones
				if(selection.equals("Today"))
					adr = "https://www.nytimes.com/crosswords/game/mini";
				else
					adr = selection;
				
				myGame = new GameInformation("December 16, 2018");
				//System.out.println("Getting Clue Numbers");
				myGame.scrapeClueNumbers();
				//System.out.println("Getting Across Clues");
				myGame.scrapeAcrossClues();
				//System.out.println("Getting Down Clues");
				myGame.scrapeDownClues();
				
				myGame.generateMatrix();
				myGame.printPuzzle();
				
				myGame.generateLengths();
				myGame.calculateCoordinates();
				
				myGame.printCluesAndQuestionsAndLengths();
				myGame.determineConstraints();
				
				Scrapper scrapi = new Scrapper();
				scrapi.firstSearch(myGame.clues, myGame.constraints);

				System.out.println("------------------------------------");
				System.out.println("First Search Alternatives and Solutions:");
				System.out.println("------------------------------------");
				for(int i = 0; i < myGame.clues.size(); i++){
					myGame.clues.get(i).printAlternatives();
				}				
				System.out.println("------------------------------------");
				
				for(int j = 0; j < myGame.clues.size(); j++){
					myGame.clues.get(j).printSolution();
				}
				
				scrapi.thirdSearch(myGame.clues, myGame.constraints);
				System.out.println("------------------------------------");
				System.out.println("Second Search Alternatives and Solutions:");
				System.out.println("------------------------------------");
				for(int i = 0; i < myGame.clues.size(); i++){
					myGame.clues.get(i).printAlternatives();
				}			
				
				for(int j = 0; j < myGame.clues.size(); j++){
					myGame.clues.get(j).printSolution();
				}
				System.out.println("------------------------------------");
				
//				scrapi.secondSearch(myGame.clues);
//				for(int i = 0; i < myGame.clues.size(); i++){
//					myGame.clues.get(i).updateClueAlternative();
//				}
//				System.out.println("------------------------------------");
//				System.out.println("Third Search Alternatives and Solutions:");
//				System.out.println("------------------------------------");
//				for(int i = 0; i < myGame.clues.size(); i++){
//					myGame.clues.get(i).printAlternatives();
//				}			
//				
//				for(int j = 0; j < myGame.clues.size(); j++){
//					myGame.clues.get(j).printSolution();
//				}
//				System.out.println("------------------------------------");
//				
				scrapi.fourthSearch(myGame.clues,myGame.constraints);
				System.out.println("------------------------------------");
				System.out.println("Fourth Search Alternatives and Solutions:");
				System.out.println("------------------------------------");
				for(int i = 0; i < myGame.clues.size(); i++){
					myGame.clues.get(i).printAlternatives();
				}			
				
				for(int j = 0; j < myGame.clues.size(); j++){
					myGame.clues.get(j).printSolution();
				}
				System.out.println("------------------------------------");
				
				for(int m = 0; m < myGame.constraints.size(); m++){
					//if(!myGame.constraints.get(m).contains(myGame.clues.get(0))){
						myGame.constraints.get(m).cleanAcrossAlternatives();
						myGame.constraints.get(m).cleanDownAlternatives();
					//}
				}
				System.out.println("------------------------------------");
				System.out.println("After Cleaning Constraints:");
				System.out.println("------------------------------------");
				for(int i = 0; i < myGame.clues.size(); i++){
					myGame.clues.get(i).printAlternatives();
				}			
				
				for(int j = 0; j < myGame.clues.size(); j++){
					myGame.clues.get(j).printSolution();
				}
				System.out.println("------------------------------------");
				
				System.out.println("------------------------------------");
				System.out.println("After Completing Cells:");
				System.out.println("------------------------------------");
				for(int i = 0; i < myGame.clues.size(); i++){
					myGame.clues.get(i).printAlternatives();
				}			
				
				for(int j = 0; j < myGame.clues.size(); j++){
					myGame.clues.get(j).printSolution();
				}
				System.out.println("------------------------------------");
				
				scrapi.googleSearch(myGame.clues, myGame.constraints);
				
				for(int i = 0; i < myGame.clues.size(); i++){
					myGame.clues.get(i).updateClueAlternative();
				}
				
				for(int m = 0; m < myGame.constraints.size(); m++){
					myGame.constraints.get(m).cleanAcrossAlternatives();
					myGame.constraints.get(m).cleanDownAlternatives();
				}
				
				for(int i = 0; i < myGame.clues.size(); i++){
					if(myGame.clues.get(i).alternatives.size() == 1){
						myGame.clues.get(i).setSolved(true);
						myGame.clues.get(i).setSolution(myGame.clues.get(i).alternatives.get(0));
						for(int j = 0; j < myGame.constraints.size();j++) {
							if(myGame.constraints.get(j).contains(myGame.clues.get(i))) {
								myGame.constraints.get(j).updateClue();
							}
						}
					}
				}
				for(int m = 0; m < myGame.constraints.size(); m++){
					myGame.constraints.get(m).cleanAcrossAlternatives();
					myGame.constraints.get(m).cleanDownAlternatives();
				}
				for(int i = 0; i < myGame.clues.size(); i++){
					if(myGame.clues.get(i).alternatives.size() == 1){
						myGame.clues.get(i).setSolved(true);
						myGame.clues.get(i).setSolution(myGame.clues.get(i).alternatives.get(0));
						for(int j = 0; j < myGame.constraints.size();j++) {
							if(myGame.constraints.get(j).contains(myGame.clues.get(i))) {
								myGame.constraints.get(j).updateClue();
							}
						}
					}
				}
				for(int i = 0; i < myGame.clues.size(); i++) {
					myGame.clues.get(i).printAlternatives();
				}
				for(int i = 0; i < myGame.clues.size(); i++) {
					myGame.clues.get(i).printSolution();
				}

//				//Initialize the Variables according to the Game Information
//				myClueNumber = myGame.getClueNumbers();
//				myAcrossClues = myGame.getAcrossClues();
//				myDownClues = myGame.getDownClues();
//				//System.out.println("Getting Date");
//				gameDay = myGame.getGameDay();
//				gameDate = myGame.getGameDate();
//				
//				//Initialization of the Main Panels
//				//System.out.println("Initializing Top Panel");
//				TopPanel myTopPanel = new TopPanel(gameDay, gameDate);
//				//System.out.println("Initializing Buttons Panel");
//				ButtonsPanel myButtonsPanel = new ButtonsPanel(myClueNumber);
//				//System.out.println("Initializing Center Panel");
//				CenterPanel myCenterPanel = new CenterPanel(myAcrossClues, myDownClues, myClueNumber);
//				myButtonsPanel.others.setSelectedItem((selection == "Today") ? "Today" : gameDate);
//				
//				//Setting Layout and Adding the Panels
//				myGameFrame.setLayout(new BorderLayout());
//				myGameFrame.add(myTopPanel,BorderLayout.NORTH);
//				myGameFrame.add(myButtonsPanel,BorderLayout.SOUTH);
//				myGameFrame.add(myCenterPanel,BorderLayout.CENTER);
//				myGameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//				myGameFrame.setSize(1250, 720);
//				myGameFrame.setLocationRelativeTo(null);
//				myGameFrame.setVisible(true);
//				myGameFrame.setResizable(false);
//				//System.out.println("Frame Generated");
				
				oldSelection = selection;
			}
			if(store == true) {
				//System.out.println("Storing the puzzle");
				String dir = "./oldPuzzles/" + gameDate;
				File theDir = new File(dir);
				store = false;

				// if the directory does not exist, create it
				if (!theDir.exists()) {
				    //System.out.println("Creating directory: " + theDir.getName());
				    boolean result = false;

				    try {
				        theDir.mkdir();
				        result = true;
				    } 
				    catch(SecurityException se){
				        //handle it
				    }        
				    if(result) {    
				        //System.out.println("DIR created");  
				    }
				    
					try {
						File file = new File(dir + "/htmlCode.txt");
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write(myGame.tempText);
						fileWriter.flush();
						fileWriter.close();
					} 	
					catch (IOException e) {
						e.printStackTrace();
					}
				}				
			}	
			if(solve == true) {
				//System.out.println("Started Solving");
				solve = false;
				//System.out.println("Determining Cells that should match with each other");
						
				
			}
			System.out.print("");
		}
	}
}

