import java.awt.*;
import java.util.*;

import javax.swing.*;

public class CenterPanel extends JPanel {
	QuestionPanel myQuestionPanel;
	MainCrosswordPanel myCrosswordPanel;
	public CenterPanel(ArrayList<String> myAcrossClues, ArrayList<String> myDownClues) throws Exception {
		myQuestionPanel = new QuestionPanel(myAcrossClues, myDownClues);
		myCrosswordPanel = new MainCrosswordPanel();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(myQuestionPanel);
	}

}
