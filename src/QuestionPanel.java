import java.awt.*;
import java.util.*;

import javax.swing.*;

public class QuestionPanel extends JPanel {
	
	//Inner Panels needed for the Game
	AcrossPanel myAcrossPanel;
	DownPanel myDownPanel;
	
	//Constructor of QuestionPanel takes Across and Down Clues
	//Send the Clues to Corresponding inner Panels
	public QuestionPanel(ArrayList<String> myAcrossClues, ArrayList<String> myDownClues) {
		
		//Initialization of the Inner Panels
		myAcrossPanel = new AcrossPanel(myAcrossClues);
		myDownPanel = new DownPanel(myDownClues);
		
		//Adding the Inner Panels to the Question Panel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 110)));
		add(myAcrossPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(myDownPanel);
		add(Box.createRigidArea(new Dimension(0, 120)));
	}

}
