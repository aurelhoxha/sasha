import javafx.scene.control.Cell;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class CrosswordPanel extends JPanel{
	JLabel selectedQuestion;
	JPanel thePattern;
	static JTextField[] cellText = new JTextField[25];
	public CrosswordPanel(Integer[] myClueNumber) throws Exception {
		selectedQuestion = new JLabel("Selected Question");
		thePattern = new JPanel();
		selectedQuestion.setFont(new Font("Serif",Font.BOLD,14));
		selectedQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);

		thePattern.setLayout(new GridLayout(5,5));
		//int count = myClueNumber.length;
		for(int i = 0; i < myClueNumber.length; i++ )
			if (myClueNumber[i] == -1) {
				cellText[i] = new JTextField();
				cellText[i].setBackground(Color.BLACK);
				cellText[i].setEditable(false);
				cellText[i].setBorder(BorderFactory.createLineBorder(Color.gray));
				thePattern.add(cellText[i]);
			} else {
				String cellimg = "./img/" + myClueNumber[i] + ".png";
				cellText[i] = new JTextField();
                cellText[i].addFocusListener(ColorChange.myFocusListener);
                cellText[i].setDocument(new JTextFieldLimit(1));
				cellText[i].setOpaque(false);
				cellText[i].setHorizontalAlignment(JTextField.CENTER);
				cellText[i].setFont(new Font("Helvetica", Font.PLAIN, 33));
				cellText[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				cellText[i].setBorder(BorderFactory.createLineBorder(Color.gray));
				JLabel cell = new JLabel(new ImageIcon(cellimg));
				cell.setOpaque(true);
				cell.setLayout(new BorderLayout());
				cell.add(cellText[i]);
				thePattern.add(cell);

			}

		setLayout(new BorderLayout());
		add(selectedQuestion, BorderLayout.NORTH);
		add(thePattern,BorderLayout.CENTER);
	}

}
