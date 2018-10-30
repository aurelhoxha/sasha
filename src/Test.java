import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Test extends JFrame{

	public static void main(String[] args) throws Exception {
		
		//Variables to save the data of the game
		ArrayList<Integer> myBlockCells = new ArrayList<Integer>();
		ArrayList<String> myAcrossClues = new ArrayList<String>();
		ArrayList<String> myDownClues = new ArrayList<String>();
		
		//Get Game Information
		GameInformation myGame = new GameInformation();
		myGame.scrapeBlockCells();
		myGame.scrapeAcrossClues();
		myGame.scrapeDownClues();
		
		//Initialize the Variables according to the Game Information
		myBlockCells = myGame.getBlockCells();
		myAcrossClues = myGame.getAcrossClues();
		myDownClues = myGame.getDownClues();
		
		JFrame myGameFrame = new JFrame("Sasha");
		TopPanel myTopPanel = new TopPanel("October 30, 2018");
		ButtonsPanel myButtonsPanel = new ButtonsPanel();
		AcrossPanel myAcrossPanel = new AcrossPanel(5);
		myGameFrame.setLayout(new BorderLayout());
		myGameFrame.add(myTopPanel,BorderLayout.NORTH);
		myGameFrame.add(myButtonsPanel,BorderLayout.SOUTH);
		myGameFrame.add(myButtonsPanel,BorderLayout.WEST);
		myGameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		myGameFrame.setSize(950, 650);
		myGameFrame.setLocationRelativeTo(null);
		myGameFrame.setVisible(true);
		myGameFrame.setResizable(false);
		
		
		
		
		
	}

}
