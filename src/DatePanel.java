import java.awt.*;
import javax.swing.*;

//Panel That Contain Information for Date
public class DatePanel extends JPanel {
	
	//JLabel to keep the day and the date of the Puzzle
	private JLabel dateLabel;
	private JLabel dayLabel;
	
	//Constructor for the DatePanel
	public DatePanel(String dayText, String dateText) {
		//Initializing the Day and the Date
		dayLabel = new JLabel(dayText+" ");
		dateLabel = new JLabel(dateText);
		
		//Setting the fonts and alignment accordingly
		dayLabel.setFont(new Font("Serif",Font.BOLD,40));
		dateLabel.setFont(new Font("Serif",Font.PLAIN,40));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		dayLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Adding the component to the DatePanel
		add(dayLabel);
		add(dateLabel);
		
	}

}