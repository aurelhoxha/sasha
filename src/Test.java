import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileWriter;
import javax.swing.*;

import java.io.*;
import sun.audio.*;

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
				
				myGame = new GameInformation(adr);
				System.out.println("Taking Clue Numbers");
				myGame.scrapeClueNumbers();
				System.out.println("Taking Across Clues");
				myGame.scrapeAcrossClues();
				System.out.println("Taking Down Clues");
				myGame.scrapeDownClues();
				
				myGame.generateMatrix();
				myGame.generateLengths();
				myGame.calculateCoordinates();
				myGame.determineConstraints();
				//myGame.printCluesAndQuestionsAndLengths();

				//Initialize the Variables according to the Game Information
				myClueNumber = myGame.getClueNumbers();
				myAcrossClues = myGame.getAcrossClues();
				myDownClues = myGame.getDownClues();
				//System.out.println("Getting Date");
				gameDay = myGame.getGameDay();
				gameDate = myGame.getGameDate();
				
				//Initialization of the Main Panels
				System.out.println("Initializing Top Panel");
				TopPanel myTopPanel = new TopPanel(gameDay, gameDate);
				System.out.println("Initializing Buttons Panel");
				ButtonsPanel myButtonsPanel = new ButtonsPanel(myClueNumber);
				System.out.println("Initializing Center Panel");
				CenterPanel myCenterPanel = new CenterPanel(myAcrossClues, myDownClues, myClueNumber);
				myButtonsPanel.others.setSelectedItem((selection == "Today") ? "Today" : gameDate);
				
				//Setting Layout and Adding the Panels
				myGameFrame.setLayout(new BorderLayout());
				myGameFrame.add(myTopPanel,BorderLayout.NORTH);
				myGameFrame.add(myButtonsPanel,BorderLayout.SOUTH);
				myGameFrame.add(myCenterPanel,BorderLayout.CENTER);
				myGameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				myGameFrame.setSize(1250, 720);
				myGameFrame.setLocationRelativeTo(null);
				myGameFrame.setVisible(true);
				myGameFrame.setResizable(false);
				//System.out.println("Frame Generated");
				
				oldSelection = selection;
			}
			if(store == true) {
				System.out.println("Storing the puzzle");
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
				System.out.println("Started Solving for date: " + selection);
				solve = false;
				try{
					String gongFile = "./src/startSound.wav";
				    InputStream in = new FileInputStream(gongFile);
				    AudioStream audioStream = new AudioStream(in);
				    AudioPlayer.player.start(audioStream);
				}
				catch(Exception e){
					
				}
				
				//System.out.println("Determining Cells that should match with each other");
				Scrapper scrapi = new Scrapper();
				scrapi.firstSearch(myGame.clues, myGame.constraints);				
				System.out.println("------------------------------------------");
				System.out.println("First Search Certain Solutions:");
				System.out.println("------------------------------------------");
				printFirstSearchSolutions(myGame);
				System.out.println("------------------------------------------");
				
				scrapi.thirdSearch(myGame.clues, myGame.constraints);
				System.out.println("------------------------------------------");
				System.out.println("Second Search Alternatives:");
				System.out.println("------------------------------------------");
				printAlternatives(myGame);			
				System.out.println("------------------------------------------");
				System.out.println("Second Search Solutions:");
				System.out.println("------------------------------------------");
				printSolutions(myGame);
				System.out.println("------------------------------------------");
							
				scrapi.fourthSearch(myGame.clues,myGame.constraints);
				System.out.println("-----------------------------------------");
				System.out.println("Third Search Alternatives:");
				System.out.println("-----------------------------------------");
				printAlternatives(myGame);
				System.out.println("------------------------------------------");
				System.out.println("Third Search Solutions:");
				System.out.println("------------------------------------------");
				printSolutions(myGame);
				System.out.println("-----------------------------------------");
				
				///////////////////////////////////////////////////
				updateAlternativesByConstraints(myGame);
				updateCluesBySize(myGame);
				///////////////////////////////////////////////////
				
				System.out.println("------------------------------------");
				System.out.println("After Cleaning Constraints:");
				System.out.println("------------------------------------");
				printAlternatives(myGame);
				System.out.println("------------------------------------");
				printSolutions(myGame);
				System.out.println("------------------------------------");
				
				scrapi.googleSearch(myGame.clues, myGame.constraints);
				
				for(int i = 0; i < myGame.clues.size(); i++){ 
					myGame.clues.get(i).updateClueAlternative(); 
				}
				
				/////////////////////////////////////////////////////
				updateAlternativesByConstraints(myGame);
				updateCluesBySize(myGame);
				/////////////////////////////////////////////////////
				
				updateAlternativesByConstraints(myGame);
				updateCluesBySize(myGame);
				
				
				scrapi.fourthSearch(myGame.clues,myGame.constraints);
				scrapi.driver1.close();
				scrapi.driver1.quit();
				
				updateAlternativesByConstraints(myGame);
				updateCluesBySize(myGame);
				
				System.out.println("------------------------------------------");
				System.out.println("Final Version of Solutions");
				System.out.println("--------------------------------------------");
				printSolutions(myGame);
				System.out.println("--------------------------------------------");
				
				try{
					String gongFile = "./src/finalSound.wav";
				    InputStream in = new FileInputStream(gongFile);
				    AudioStream audioStream = new AudioStream(in);
				    AudioPlayer.player.start(audioStream);
				}
				catch(Exception e){
					
				}
				
				
				for(int l = 0; l < myGame.clues.size(); l++) {
					String clueDirection = myGame.clues.get(l).getDirection();
					if(clueDirection.equals("across")) {
						int clueXPosition = myGame.clues.get(l).getX();
						int clueYPosition = myGame.clues.get(l).getY();
						for(int k = 0; k < myGame.clues.get(l).getLength(); k++) {
							String characterToBeUpdated = "" + myGame.clues.get(l).getSolution()[k];
							int pos = (clueXPosition * 5) + clueYPosition;
							//System.out.println(clueXPosition + " " + clueYPosition);					
							CrosswordPanel.cellText[pos].setText("A");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("B");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("C");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("D");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("E");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("F");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("G");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("H");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("I");
							TimeUnit.MILLISECONDS.sleep(20);
							
							CrosswordPanel.cellText[pos].setText(characterToBeUpdated);
							//System.out.println(pos);
							clueYPosition = clueYPosition + 1;
						}
					}
					if(clueDirection.equals("down")) {
						int clueXPosition = myGame.clues.get(l).getX();
						int clueYPosition = myGame.clues.get(l).getY();
						for(int k = 0; k < myGame.clues.get(l).getLength(); k++) {
							String characterToBeUpdated = "" + myGame.clues.get(l).getSolution()[k];
							int pos = (clueXPosition*5) + clueYPosition;
							//System.out.println(clueXPosition + " " + clueYPosition);
							CrosswordPanel.cellText[pos].setText("A");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("B");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("C");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("D");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("E");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("F");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("G");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("H");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText("I");
							TimeUnit.MILLISECONDS.sleep(20);
							CrosswordPanel.cellText[pos].setText(characterToBeUpdated);
							//System.out.println(pos);
							clueXPosition = clueXPosition + 1;
						}
					}
				}
			}
			System.out.print("");
		}
	}
	
	public static void printFirstSearchSolutions(GameInformation myGame) throws InterruptedException{
		int counter = 0;
		for(int i = 0; i < myGame.clues.size(); i++) {
			if(myGame.clues.get(i).isSolved()){
				counter++;
				try{
					String gongFile = "./src/foundClue1.wav";
				    InputStream in = new FileInputStream(gongFile);
				    AudioStream audioStream = new AudioStream(in);
				    AudioPlayer.player.start(audioStream);
				}
				catch(Exception e){	
				}
				myGame.clues.get(i).printSolution();
				TimeUnit.SECONDS.sleep(3);
			}
			if(counter == 2)
				break;
		}
	}
	
	public static void printAlternatives(GameInformation myGame){
		for(int i = 0; i < myGame.clues.size(); i++){
			myGame.clues.get(i).printAlternatives();
		}
	}
	
	public static void printSolutions(GameInformation myGame){
		for(int j = 0; j < myGame.clues.size(); j++){
			myGame.clues.get(j).printSolution();
		}
	}
	
	public static void updateAlternativesByConstraints(GameInformation myGame){
		for(int m = 0; m < myGame.constraints.size(); m++){
				myGame.constraints.get(m).cleanAcrossAlternatives();
				myGame.constraints.get(m).cleanDownAlternatives();
		}
	}
	
	public static void updateCluesBySize(GameInformation myGame){
		for(int i = 0; i < myGame.clues.size(); i++){
			if(myGame.clues.get(i).alternatives.size() == 1){
				try{
					String gongFile = "./src/foundClue1.wav";
				    InputStream in = new FileInputStream(gongFile);
				    AudioStream audioStream = new AudioStream(in);
				    AudioPlayer.player.start(audioStream);
				}
				catch(Exception e){
				}
				myGame.clues.get(i).setSolved(true);
				myGame.clues.get(i).setSolution(myGame.clues.get(i).alternatives.get(0));
				for(int j = 0; j < myGame.constraints.size();j++) {
					if(myGame.constraints.get(j).contains(myGame.clues.get(i))) {
						myGame.constraints.get(j).updateClue();
					}
				}
			}
		}
	}
	
}

