import java.awt.*;
import javax.swing.*;

public class AcrossQuestionsPanel extends JPanel {
	
	public AcrossQuestionsPanel(int acrossQuestionNum) {
		setLayout(new GridLayout(acrossQuestionNum,1));
		for(int i = 0; i < acrossQuestionNum; i++ ) {
			JLabel theQuestion = new JLabel("Question " + i );
			theQuestion.setFont(new Font("Serif",Font.PLAIN,12));
			theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(theQuestion);
		}
	}

}
