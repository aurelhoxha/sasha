import java.awt.*;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.io.FileFilter;

import javax.swing.*;

public class Test extends JFrame{

	static String selection = "https://www.nytimes.com/crosswords/game/mini";
	static String oldSelection = "";
	static boolean reveal = false;
	public static void main(String[] args) throws Exception {	
		//Variables to save the data of the game
		Integer[] myClueNumber = new Integer[25];
		ArrayList<String> myAcrossClues = new ArrayList<String>();
		ArrayList<String> myDownClues = new ArrayList<String>();
		String gameDay;
		String gameDate;
		GameInformation myGame;
		
		//Creation of the Game
		JFrame myGameFrame = new JFrame("Sasha");;
		
		while(true){
			if(!selection.equals(oldSelection)){
				myGameFrame.dispose();
				myGameFrame = new JFrame("Sasha");
				//Get Game Information
				//myGameFrame.dispose();
				myGame = new GameInformation(selection);
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
				ButtonsPanel myButtonsPanel = new ButtonsPanel();
				CenterPanel myCenterPanel = new CenterPanel(myAcrossClues, myDownClues, myClueNumber);
		
				//Setting Layout and Adding the Panels
				myGameFrame.setLayout(new BorderLayout());
				myGameFrame.add(myTopPanel,BorderLayout.NORTH);
				myGameFrame.add(myButtonsPanel,BorderLayout.SOUTH);
				myGameFrame.add(myCenterPanel,BorderLayout.CENTER);
				myGameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				myGameFrame.setSize(950, 650);
				myGameFrame.setLocationRelativeTo(null);
				myGameFrame.setVisible(true);
				myGameFrame.setResizable(false);
				
				oldSelection = selection;
			}
			if(reveal == true){
				String path = "";
				ImageIcon solution;
				if(selection == "https://www.nytimes.com/crosswords/game/mini"){
					path = "./oldPuzzles/01 November 2018/solution.png";
					solution = new ImageIcon(path);
	                JOptionPane.showMessageDialog(
	                        null,
	                        "",
	                        "Solution for the puzzle", JOptionPane.INFORMATION_MESSAGE,
	                        solution);
				}
				else {
					path = "./oldPuzzles/" + selection + "/solution.png";
					solution = new ImageIcon(path);
	                JOptionPane.showMessageDialog(
	                        null,
	                        "",
	                        "Solution for the puzzle", JOptionPane.INFORMATION_MESSAGE,
	                        solution);								
				}
				reveal = false;
			}			
			System.out.println();
		}
	}
}

