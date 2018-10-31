import java.awt.*;
import java.util.*;

import javax.swing.*;

public class AcrossPanel extends JPanel{
	JLabel acrossTitle;
	JPanel acrossQuestions;
	public AcrossPanel(ArrayList<String> myAcrossClues) {
		acrossTitle = new JLabel("Across");
		acrossQuestions = new JPanel();
		acrossTitle.setFont(new Font("Serif",Font.BOLD,14));
		acrossTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		acrossQuestions.setLayout(new GridLayout(myAcrossClues.size(),1));
		for(int i = 0; i < myAcrossClues.size(); i++ ) {
			JLabel theQuestion = new JLabel(myAcrossClues.get(i));
			theQuestion.setFont(new Font("Serif",Font.PLAIN,13));
			theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
			acrossQuestions.add(theQuestion);
		}
		setLayout(new BorderLayout());
		add(acrossTitle, BorderLayout.NORTH);
		add(acrossQuestions,BorderLayout.CENTER);
	}

}
