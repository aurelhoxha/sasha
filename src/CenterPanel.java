import java.awt.*;
import java.util.*;

import javax.swing.*;

public class CenterPanel extends JPanel {
	QuestionPanel myQuestionPanel;
	MainCrosswordPanel myCrosswordPanel;
	public CenterPanel(ArrayList<String> myAcrossClues, ArrayList<String> myDownClues, ArrayList<Integer> myBlockCells ) throws Exception {
		myQuestionPanel = new QuestionPanel(myAcrossClues, myDownClues);
		myCrosswordPanel = new MainCrosswordPanel(myBlockCells);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(60, 0)));
		add(myQuestionPanel);
		//add(Box.createRigidArea(new Dimension(80, 0)));
	}

}
