import java.awt.*;
import javax.swing.*;

public class DatePanel extends JPanel {
	private JLabel dateLabel;
	
	public DatePanel(String dateText) {
		dateLabel = new JLabel(dateText);
		dateLabel.setFont(new Font("Serif",Font.PLAIN,40));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(dateLabel);
		
	}

}