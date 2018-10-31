import java.awt.*;
import java.util.*;

import javax.swing.*;

public class DownPanel extends JPanel{
	JLabel downTitle;
	JPanel downQuestions;
	public DownPanel(ArrayList<String> myDownClues) {
		downTitle = new JLabel("Down");
		downQuestions = new JPanel();
		downTitle.setFont(new Font("Serif",Font.BOLD,14));
		downTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		downQuestions.setLayout(new GridLayout(myDownClues.size(),1));
		for(int i = 0; i < myDownClues.size(); i++ ) {
			JLabel theQuestion = new JLabel(myDownClues.get(i));
			theQuestion.setFont(new Font("Serif",Font.PLAIN,13));
			theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
			downQuestions.add(theQuestion);
		}
		setLayout(new BorderLayout());
		add(downTitle, BorderLayout.NORTH);
		add(downQuestions,BorderLayout.CENTER);
	}

}