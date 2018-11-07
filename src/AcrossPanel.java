import java.awt.*;
import java.util.*;

import javax.swing.*;

//Panel that Save the AcrossQuestions
public class AcrossPanel extends JPanel{
	
	private static final long serialVersionUID = 6014559460886907016L;
	//Components needed for Panel
	JLabel acrossTitle;
	JPanel acrossQuestions;
	
	//Constructor for AcrossPanel
	//Takes the Across Clues from QuestionPanel
	public AcrossPanel(ArrayList<String> myAcrossClues) {
		
		//Create a label to specify the Panel
		acrossTitle = new JLabel("Across");
		
		//Save the Questions in another Panel
		acrossQuestions = new JPanel();
		
		//Set Font and Alignment for the Label
		acrossTitle.setFont(new Font("Serif",Font.BOLD,14));
		acrossTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Set the Layout for the Panel of the questions
		acrossQuestions.setLayout(new GridLayout(myAcrossClues.size()+3,1));
		for(int i = 0; i < myAcrossClues.size(); i++ ) {
			
			//Add the questions to the Panel
			JLabel theQuestion = new JLabel(myAcrossClues.get(i));
			
			//If question is to Long divide it
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
				//Divide the Label
				//Add it to the Panel
				JLabel theQuestion1 = new JLabel(first);
				JLabel theQuestion2 = new JLabel(second);
				theQuestion1.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion2.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion1.setAlignmentX(Component.LEFT_ALIGNMENT);
				theQuestion2.setAlignmentX(Component.LEFT_ALIGNMENT);
				acrossQuestions.add(theQuestion1);
				acrossQuestions.add(theQuestion2);				
			}
			
			//If the Length is OK add it to the Panel
			else {
				theQuestion.setFont(new Font("Serif",Font.PLAIN,13));
				theQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
				acrossQuestions.add(theQuestion);
			}
		}
		//Add both components to the Panel of the Question
		setLayout(new BorderLayout());
		add(acrossTitle, BorderLayout.NORTH);
		add(acrossQuestions,BorderLayout.CENTER);
	}

}
