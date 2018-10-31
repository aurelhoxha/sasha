import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Test extends JFrame{

	public static void main(String[] args) throws Exception {
		
		//Variables to save the data of the game
		Integer[] myClueNumber = new Integer[25];
		ArrayList<String> myAcrossClues = new ArrayList<String>();
		ArrayList<String> myDownClues = new ArrayList<String>();
		String gameDay;
		String gameDate;
		
		//Get Game Information
		GameInformation myGame = new GameInformation();
		myGame.scrapeClueNumbers();
		myGame.scrapeAcrossClues();
		myGame.scrapeDownClues();
		
		//Initialize the Variables according to the Game Information
		myClueNumber = myGame.getClueNumbers();
		myAcrossClues = myGame.getAcrossClues();
		myDownClues = myGame.getDownClues();
		gameDay = myGame.getGameDay();
		gameDate = myGame.getGameDate();
		
		//System.out.println(myGame.htmlCode);
		myGame.printArrayCells();
		
		//Creation of the Game
		JFrame myGameFrame = new JFrame("Sasha");
		
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
		
		
		
		
		
	}

}
