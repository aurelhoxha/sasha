//PANEL THAT INCLUDE OUR PUZZLE, CLUES AND OFFICIAL SOLUTION

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CenterPanel extends JPanel {
	//Components Needed for the Center Panel
	QuestionPanel myQuestionPanel;
	MainCrosswordPanel myCrosswordPanel;
	
	//Initialization of the Center Panel
	public CenterPanel(ArrayList<String> myAcrossClues, ArrayList<String> myDownClues, Integer[] myClueNumber ) throws Exception {

		//Passing the parameters to the Inner Panels
		myQuestionPanel = new QuestionPanel(myAcrossClues, myDownClues);
		myCrosswordPanel = new MainCrosswordPanel(myClueNumber);
		
		JPanel solutionPanel = new JPanel(new BorderLayout());
		String meh = "./oldPuzzles/" + Test.selection + "/solution.png";
		ImageIcon img = new ImageIcon(meh);
		JLabel lab = new JLabel(img);
		solutionPanel.add(lab, BorderLayout.CENTER);
		
		//Adding Inner Panels to the Central Panel
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(40, 0)));
		add(myQuestionPanel);
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(solutionPanel);
		add(Box.createRigidArea(new Dimension(10, 0)));
	}

}
