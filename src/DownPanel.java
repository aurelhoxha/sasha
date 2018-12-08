//PANEL THAT WILL CONTAIN DOWN CLUES

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class DownPanel extends JPanel{
	
	//Components needed for Panel
	JLabel downTitle;
	JPanel downQuestions;
	
	//Constructor for DownPanel
	//Takes the Down Clues from QuestionPanel
	public DownPanel(ArrayList<String> myDownClues) {
		
		//Create a label to specify the Panel
		downTitle = new JLabel("Down");
		
		//Save the Questions in another Panel
		downQuestions = new JPanel();
		
		//Set Font and Alignment for the Label
		downTitle.setFont(new Font("Serif",Font.BOLD,14));
		downTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Set the Layout for the Panel of the questions
		downQuestions.setLayout(new GridLayout(myDownClues.size()+3,1));
		for(int i = 0; i < myDownClues.size(); i++ ) {
			
			//Add the questions to the Panel
			JLabel theQuestion = new JLabel(myDownClues.get(i));
			
			//If question is to Long divide it
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
				
				//Divide the Label
				//Add it to the Panel
				JLabel theQuestion1 = new JLabel(first);
				JLabel theQuestion2 = new JLabel(second);
				theQuestion1.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion2.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion1.setAlignmentX(Component.LEFT_ALIGNMENT);
				theQuestion2.setAlignmentX(Component.LEFT_ALIGNMENT);
				downQuestions.add(theQuestion1);
				downQuestions.add(theQuestion2);				
			}
			
			//If the Length is OK add it to the Panel
			else {	
				theQuestion.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
				downQuestions.add(theQuestion);
			}
		}
		
		//Add both components to the Panel of the Question
		setLayout(new BorderLayout());
		add(downTitle, BorderLayout.NORTH);
		add(downQuestions,BorderLayout.CENTER);
	}

}