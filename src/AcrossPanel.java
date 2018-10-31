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
		
		acrossQuestions.setLayout(new GridLayout(myAcrossClues.size()+3,1));
		for(int i = 0; i < myAcrossClues.size(); i++ ) {
			JLabel theQuestion = new JLabel(myAcrossClues.get(i));
			
			if(myAcrossClues.get(i).length() >= 80){
				String first = "";
				String second = "";
				for(int j = 70; j < myAcrossClues.get(i).length()-1; j++) {
					if(myAcrossClues.get(i).charAt(j) == ' ' ){
						first = myAcrossClues.get(i).substring(0, j);
						second = "   " + myAcrossClues.get(i).substring(j+1, myAcrossClues.get(i).length());
						break;
					}
				}
				JLabel theQuestion1 = new JLabel(first);
				JLabel theQuestion2 = new JLabel(second);
				theQuestion1.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion2.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion1.setAlignmentX(Component.LEFT_ALIGNMENT);
				theQuestion2.setAlignmentX(Component.LEFT_ALIGNMENT);
				acrossQuestions.add(theQuestion1);
				acrossQuestions.add(theQuestion2);				
			}
			else {	
				theQuestion.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
				acrossQuestions.add(theQuestion);
			}
		}
		setLayout(new BorderLayout());
		add(acrossTitle, BorderLayout.NORTH);
		add(acrossQuestions,BorderLayout.CENTER);
	}

}
