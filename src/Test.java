import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Test extends JFrame{

	public static void main(String[] args) throws Exception {
		
		//Variables to save the data of the game
		ArrayList<Integer> myBlockCells = new ArrayList<Integer>();
		ArrayList<String> myAcrossClues = new ArrayList<String>();
		ArrayList<String> myDownClues = new ArrayList<String>();
		String gameDay;
		String gameDate;
		
		//Get Game Information
		GameInformation myGame = new GameInformation();
		myGame.scrapeBlockCells();
		myGame.scrapeAcrossClues();
		myGame.scrapeDownClues();
		
		//Initialize the Variables according to the Game Information
		myBlockCells = myGame.getBlockCells();
		myAcrossClues = myGame.getAcrossClues();
		myDownClues = myGame.getDownClues();
		gameDay = myGame.getGameDay();
		gameDate = myGame.getGameDate();
		
		myGame.printAcrossClues();
		System.out.println();
		myGame.printDownClues();
		System.out.println();
		
		JFrame myGameFrame = new JFrame("Sasha");
		TopPanel myTopPanel = new TopPanel(gameDay, gameDate);
		ButtonsPanel myButtonsPanel = new ButtonsPanel();
		CenterPanel myCenterPanel = new CenterPanel(myAcrossClues, myDownClues);
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
