import java.awt.*;
import javax.swing.*;

public class DatePanel extends JPanel {
	private JLabel dateLabel;
	private JLabel dayLabel;
	
	public DatePanel(String dayText, String dateText) {
		dayLabel = new JLabel(dayText+" ");
		dateLabel = new JLabel(dateText);
		dayLabel.setFont(new Font("Serif",Font.BOLD,40));
		dateLabel.setFont(new Font("Serif",Font.PLAIN,40));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		dayLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(dayLabel);
		add(dateLabel);
		
	}

}