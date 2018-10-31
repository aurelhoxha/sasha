import java.awt.*;
import java.util.*;

import javax.swing.*;

public class CenterPanel extends JPanel {
	
	//Components Needed for the Center Panel
	QuestionPanel myQuestionPanel;
	MainCrosswordPanel myCrosswordPanel;
	
	//Initialization of the Center Panel
	public CenterPanel(ArrayList<String> myAcrossClues, ArrayList<String> myDownClues, ArrayList<Integer> myBlockCells ) throws Exception {

		//Passing the parameters to the Inner Panels
		myQuestionPanel = new QuestionPanel(myAcrossClues, myDownClues);
		myCrosswordPanel = new MainCrosswordPanel(myBlockCells);
		
		//Adding Inner Panels to the Central Panel
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(60, 0)));
		add(myQuestionPanel);
		//add(Box.createRigidArea(new Dimension(80, 0)));
	}

}
