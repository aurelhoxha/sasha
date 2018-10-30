import java.awt.*;
import javax.swing.*;

public class TopPanel extends JPanel {
	DatePanel myDatePanel;
	InfoPanel myInfoPanel;
	public TopPanel(String dateText) {
		myDatePanel = new DatePanel(dateText);
		myInfoPanel = new InfoPanel();
		setLayout(new BorderLayout());
		add(myDatePanel,BorderLayout.NORTH);
		add(myInfoPanel,BorderLayout.SOUTH);
	}

}
