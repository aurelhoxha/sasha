import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class CrosswordPanel extends JPanel{
	JLabel selectedQuestion;
	JPanel thePattern;
	public CrosswordPanel(ArrayList<Integer> blockPosition) throws Exception {
		selectedQuestion = new JLabel("Selected Question");
		thePattern = new JPanel();
		selectedQuestion.setFont(new Font("Serif",Font.BOLD,14));
		selectedQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//GameInformation game = new GameInformation();
		Integer[][] cells = new Integer[5][5];
		for(int k = 0; k < 5; k++){
			for(int l = 0; l < 5; l++){
				if(blockPosition.contains(4*k + l))
					cells[k][l] = 1;
				else
					cells[k][l] = 0;
			}
		}
		
		JTextField[] cellText = new JTextField[25];
		thePattern.setLayout(new GridLayout(5,5));
		for(int i = 0; i < 25; i++ ) {
			if(blockPosition.contains(i)){
				cellText[i] = new JTextField();
				cellText[i].setBackground(Color.BLACK);
				cellText[i].setEditable(false);
				cellText[i].setBorder(BorderFactory.createLineBorder(Color.gray));
				thePattern.add(cellText[i]);
			}
			else {		
				cellText[i] = new JTextField("");
				cellText[i].setOpaque(false);
				cellText[i].setHorizontalAlignment(JTextField.CENTER);
				cellText[i].setFont(new Font("Helvetica",Font.PLAIN,33));
				cellText[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				cellText[i].setBorder(BorderFactory.createLineBorder(Color.gray));				
				JLabel cell = new JLabel(new ImageIcon("./img/3.png"));
				cell.setOpaque(true);
				cell.setLayout(new BorderLayout());
				cell.add(cellText[i]);
				thePattern.add(cell);
			}
		}
		
		setLayout(new BorderLayout());
		add(selectedQuestion, BorderLayout.NORTH);
		add(thePattern,BorderLayout.CENTER);
	}

}
