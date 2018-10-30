import java.awt.*;
import javax.swing.*;

public class MainCrosswordPanel extends JPanel {
	CrosswordPanel myCrosswordPanel;
	public MainCrosswordPanel() throws Exception {
		myCrosswordPanel = new CrosswordPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(0, 100)));
	}
	

}
