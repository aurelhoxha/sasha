import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel {
	private JLabel dateLabel;
	
	public InfoPanel() {
		dateLabel = new JLabel("Mini Crossword - By JOEL FAGLIANO");
		dateLabel.setFont(new Font("Serif",Font.PLAIN,14));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(dateLabel);
		
	}

}