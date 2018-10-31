import java.awt.*;
import java.util.*;

import javax.swing.*;

public class MainCrosswordPanel extends JPanel {
	CrosswordPanel myCrosswordPanel;
	public MainCrosswordPanel(ArrayList<Integer> myBlockCells) throws Exception {
		myCrosswordPanel = new CrosswordPanel(myBlockCells);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(myCrosswordPanel);
		add(Box.createRigidArea(new Dimension(0, 100)));
	}
	

}
