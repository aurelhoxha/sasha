import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		revealButton.addActionListener(new ActionListener() {//Add actionlistner to listen for change
	        public void actionPerformed(ActionEvent e) {
	        	Test.reveal = true;
	        }
	    });
	    
	    
		
		
		
		String[] dates = new String[]{"Today","30 October 2018", "31 October 2018", "1 November 2018", "2 November 2018"};
		JComboBox<String> others = new JComboBox<>(dates);
		
	    Dimension d = others.getPreferredSize();
	    others.setPreferredSize(new Dimension(50, d.height));
	    others.addActionListener(new ActionListener() {//Add actionlistner to listen for change
	        public void actionPerformed(ActionEvent e) {

	            String s = (String) others.getSelectedItem();//get the selected item

	            switch (s) {//check for a match
	                case "Today":
	                    Test.selection = "https://www.nytimes.com/crosswords/game/mini";
	                    break;
	                case "30 October 2018":
	                	Test.selection = "30october2018";
	                    break;
	                case "31 October 2018":
	                	Test.selection = "31october2018";
	                    break;
	                case "1 November 2018":
	                	Test.selection = "1november2018";
	                    break;
	                case "2 November 2018":
	                	Test.selection = "2november2018";
	                    break;

	            }
	        }
	    });
	    
		//showOtherButton = new JButton("Old Puzzles");
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		add(Box.createHorizontalGlue());
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(solveButton);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(clearButton);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(revealButton);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(others);
		add(Box.createRigidArea(new Dimension(100, 0)));		
	}
	
	
}
