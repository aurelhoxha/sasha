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
		
		downQuestions.setLayout(new GridLayout(myDownClues.size()+3,1));
		for(int i = 0; i < myDownClues.size(); i++ ) {
			JLabel theQuestion = new JLabel(myDownClues.get(i));
			
			if(myDownClues.get(i).length() >= 80){
				String first = "";
				String second = "";
				for(int j = 70; j < myDownClues.get(i).length()-1; j++) {
					if(myDownClues.get(i).charAt(j) == ' ' ){
						first = myDownClues.get(i).substring(0, j);
						second = "   " + myDownClues.get(i).substring(j+1, myDownClues.get(i).length());
						break;
					}
				}
				JLabel theQuestion1 = new JLabel(first);
				JLabel theQuestion2 = new JLabel(second);
				theQuestion1.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion2.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion1.setAlignmentX(Component.LEFT_ALIGNMENT);
				theQuestion2.setAlignmentX(Component.LEFT_ALIGNMENT);
				downQuestions.add(theQuestion1);
				downQuestions.add(theQuestion2);				
			}
			else {	
				theQuestion.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
				downQuestions.add(theQuestion);
			}
		}
		setLayout(new BorderLayout());
		add(downTitle, BorderLayout.NORTH);
		add(downQuestions,BorderLayout.CENTER);
	}

}