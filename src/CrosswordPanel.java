import java.awt.*;
import javax.swing.*;

public class CrosswordPanel extends JPanel{
	JLabel selectedQuestion;
	JPanel thePattern;
	public CrosswordPanel() throws Exception {
		selectedQuestion = new JLabel("Selected Question");
		thePattern = new JPanel();
		selectedQuestion.setFont(new Font("Serif",Font.BOLD,14));
		selectedQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		GameInformation game = new GameInformation();
		
		
		thePattern.setLayout(new GridLayout(5,5));
		for(int i = 0; i < 25; i++ ) {
			JTextField cellText = new JTextField("A");
			cellText.setHorizontalAlignment(JTextField.CENTER);
			cellText.setFont(new Font("Serif",Font.PLAIN,13));
			cellText.setAlignmentX(Component.CENTER_ALIGNMENT);
			thePattern.add(cellText);
		}
		setLayout(new BorderLayout());
		add(selectedQuestion, BorderLayout.NORTH);
		add(thePattern,BorderLayout.CENTER);
	}

}
