import java.awt.*;
import javax.swing.*;

public class QuestionPanel extends JPanel {
	AcrossPanel myAcrossPanel;
	DownPanel myDownPanel;
	public QuestionPanel(int acrossQuestionNum, int downQuestionNum) {
		myAcrossPanel = new AcrossPanel(acrossQuestionNum);
		myDownPanel = new DownPanel(downQuestionNum);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 120)));
		add(myAcrossPanel);
		add(Box.createRigidArea(new Dimension(0, 120)));
		add(myDownPanel);
		add(Box.createRigidArea(new Dimension(0, 120)));
	}

}
