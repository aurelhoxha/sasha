import java.awt.*;
import javax.swing.*;

public class AcrossPanel extends JPanel{
	JLabel acrossTitle;
	JPanel acrossQuestions;
	public AcrossPanel(int acrossQuestionNum) {
		acrossTitle = new JLabel("Across");
		acrossQuestions = new JPanel();
		acrossTitle.setFont(new Font("Serif",Font.BOLD,14));
		acrossTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		acrossQuestions.setLayout(new GridLayout(acrossQuestionNum,1));
		for(int i = 0; i < acrossQuestionNum; i++ ) {
			JLabel theQuestion = new JLabel("Question " + i );
			theQuestion.setFont(new Font("Serif",Font.PLAIN,12));
			theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
			acrossQuestions.add(theQuestion);
		}
		setLayout(new BorderLayout());
		add(acrossQuestions,BorderLayout.SOUTH);
	}

}
