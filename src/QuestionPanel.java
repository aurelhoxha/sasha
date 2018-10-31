import java.awt.*;
import java.util.*;

import javax.swing.*;

public class QuestionPanel extends JPanel {
	AcrossPanel myAcrossPanel;
	DownPanel myDownPanel;
	public QuestionPanel(ArrayList<String> myAcrossClues, ArrayList<String> myDownClues) {
		myAcrossPanel = new AcrossPanel(myAcrossClues);
		myDownPanel = new DownPanel(myDownClues);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 120)));
		add(myAcrossPanel);
		add(Box.createRigidArea(new Dimension(0, 120)));
		add(myDownPanel);
		add(Box.createRigidArea(new Dimension(0, 120)));
	}

}
