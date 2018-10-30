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
		
		
		
		
	}

}
