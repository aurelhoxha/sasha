import java.awt.*;
import javax.swing.*;

//Panel that Contains the Button of the Game
public class ButtonsPanel extends JPanel {
	
	//Components
	JButton solveButton;
	JButton clearButton;
	JButton revealButton;
	JButton showOtherButton;
	
	//Constuctors
	public ButtonsPanel() {
		
		//Set up the Components
		//Adding Components to the Buttons Panel
		solveButton = new JButton("Solve");
		clearButton = new JButton("Clear");
		revealButton = new JButton("Reveal");
		showOtherButton = new JButton("Old Puzzles");
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		add(Box.createHorizontalGlue());
		add(solveButton);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(clearButton);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(revealButton);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(showOtherButton);
		
	}

}
