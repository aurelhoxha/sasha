import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;

public class Test extends JFrame{

	static String selection = "Today";
	static String oldSelection = "";
	//static boolean reveal = false;
	static boolean store = false;
	static boolean solve = false;
	public static void main(String[] args) throws Exception {	
		//Variables to save the data of the game
		Integer[] myClueNumber = new Integer[25];
		ArrayList<String> myAcrossClues = new ArrayList<String>();
		ArrayList<String> myDownClues = new ArrayList<String>();
		String gameDay = "";
		String gameDate = "";
		GameInformation myGame = null;
		String adr = "";
		
		//Creation of the Game
		JFrame myGameFrame = new JFrame("Sasha");;
		
		while(true){
			if(!selection.equals(oldSelection)){
				myGameFrame.dispose();
				myGameFrame = new JFrame("Sasha");
				
				//If user chooses today then he is displayed the puzzle from Internet, otherwise from stored ones
				if(selection.equals("Today"))
					adr = "https://www.nytimes.com/crosswords/game/mini";
				else
					adr = selection;
				
				myGame = new GameInformation(adr);
				myGame.scrapeClueNumbers();
				myGame.scrapeAcrossClues();
				myGame.scrapeDownClues();
				
				//Initialize the Variables according to the Game Information
				myClueNumber = myGame.getClueNumbers();
				myAcrossClues = myGame.getAcrossClues();
				myDownClues = myGame.getDownClues();
				gameDay = myGame.getGameDay();
				gameDate = myGame.getGameDate();
				//Initialization of the Main Panels
				TopPanel myTopPanel = new TopPanel(gameDay, gameDate);
				ButtonsPanel myButtonsPanel = new ButtonsPanel(myClueNumber);
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
				
				oldSelection = selection;
			}
//			if(reveal == true){
//				String path = "";
//				ImageIcon solution;
//				if(selection == "Today"){
//	                JOptionPane.showMessageDialog(
//	                        null,
//	                        "No Solution saved for this puzzle",
//	                        "Solution for the puzzle", JOptionPane.INFORMATION_MESSAGE
//	                        );
//				}
//				else {
//					path = "./oldPuzzles/" + selection + "/solution.png";
//					solution = new ImageIcon(path);
//	                JOptionPane.showMessageDialog(
//	                        null,
//	                        "",
//	                        "Solution for the puzzle", JOptionPane.INFORMATION_MESSAGE,
//	                        solution);								
//				}
//				reveal = false;
//			}	
			if(store == true){
				String dir = "./oldPuzzles/" + gameDate;
				File theDir = new File(dir);

				// if the directory does not exist, create it
				if (!theDir.exists()) {
				    System.out.println("creating directory: " + theDir.getName());
				    boolean result = false;

				    try{
				        theDir.mkdir();
				        result = true;
				    } 
				    catch(SecurityException se){
				        //handle it
				    }        
				    if(result) {    
				        System.out.println("DIR created");  
				    }
				    
					try {
						File file = new File(dir +"/htmlCode.txt");
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write(myGame.tempText);
						fileWriter.flush();
						fileWriter.close();
					} 	
					catch (IOException e) {
						e.printStackTrace();
					}

				}
				store = false;
			}	
			if(solve == true){
				System.out.println("Solve is true");
				solve = false;
			}
			System.out.print("");
		}
	}
}

