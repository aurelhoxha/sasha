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
		
		//GameInformation game = new GameInformation();
		
		thePattern.setLayout(new GridLayout(5,5));
		for(int i = 0; i < 25; i++ ) {
			JTextField cellText = new JTextField("A");
			cellText.setOpaque(false);
			cellText.setHorizontalAlignment(JTextField.CENTER);
			cellText.setFont(new Font("Serif",Font.PLAIN,33));
			cellText.setAlignmentX(Component.CENTER_ALIGNMENT);
			cellText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel cell = new JLabel(new ImageIcon("C:\\Users\\User\\Desktop\\BILKENT\\CS461\\Proj\\sasha\\img\\2.png"));
			cell.setOpaque(true);
			cell.setLayout(new BorderLayout());
			cell.add(cellText);
			thePattern.add(cell);
		}
		
		setLayout(new BorderLayout());
		add(selectedQuestion, BorderLayout.NORTH);
		add(thePattern,BorderLayout.CENTER);
	}

}
