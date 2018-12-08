//TOP PANEL WILL HAVE ONLY DATE AND GENERAL INFO ABOUT PUZZLE

import java.awt.*;
import javax.swing.*;

//The Panel that Contains Both the Date and Info of the Puzzle
public class TopPanel extends JPanel {
	
	//Panels for the date and info
	DatePanel myDatePanel;
	InfoPanel myInfoPanel;
	
	//Constructor for the Top Panel
	//It takes two input which have arrive from Main Class and are passed to the Inner Panels
	public TopPanel(String dayText, String dateText) {
		
		//Initializing Inner Panels and adding them to Top Panel
		myDatePanel = new DatePanel(dayText, dateText);
		myInfoPanel = new InfoPanel();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(myDatePanel);
		add(myInfoPanel);
	}

}
