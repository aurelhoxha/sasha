import java.awt.*;
import javax.swing.*;

//Panel That Contain Information for the Author of the Puzzle
public class InfoPanel extends JPanel {
	
	//JLabel that keep the Information of the Author
	private JLabel infoLabel;
	
	//Constructor
	public InfoPanel() {
		
		//Giving value to the the infoLabel
		infoLabel = new JLabel("Mini Crossword - By JOEL FAGLIANO");
		
		//Setting the font and alignment accordingly
		infoLabel.setFont(new Font("Serif",Font.PLAIN,14));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Adding the Label to the InfoPanel
		add(infoLabel);
		
	}

}