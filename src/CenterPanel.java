import java.awt.*;
import javax.swing.*;

public class CenterPanel extends JPanel {
	QuestionPanel myQuestionPanel;
	MainCrosswordPanel myCrosswordPanel;
	public CenterPanel(int acrossQuestionNum, int downQuestionNum) throws Exception {
		myQuestionPanel = new QuestionPanel(acrossQuestionNum, downQuestionNum);
		myCrosswordPanel = new MainCrosswordPanel();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(200, 0)));
		add(myQuestionPanel);
	}

}
