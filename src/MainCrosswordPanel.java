import java.awt.*;
import java.util.*;

import javax.swing.*;

public class MainCrosswordPanel extends JPanel {
	CrosswordPanel myCrosswordPanel;
	public MainCrosswordPanel(Integer[] myClueNumber) throws Exception {
		myCrosswordPanel = new CrosswordPanel(myClueNumber);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(0, 100)));
	}
	

}
