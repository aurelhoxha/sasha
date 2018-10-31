import java.awt.*;
import javax.swing.*;

public class TopPanel extends JPanel {
	DatePanel myDatePanel;
	InfoPanel myInfoPanel;
	public TopPanel(String dayText, String dateText) {
		myDatePanel = new DatePanel(dayText, dateText);
		myInfoPanel = new InfoPanel();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(myDatePanel);
		add(myInfoPanel);
	}

}
