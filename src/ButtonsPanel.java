import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

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
		//ListSubdirectories
		File directory = new File("./oldPuzzles/");
	    FileFilter directoryFileFilter = new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    };
			
	    File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
	    ArrayList<String> foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
	    for (File directoryAsFile : directoryListAsFile) {
	        foldersInDirectory.add(directoryAsFile.getName());
	    }
		
		
		//Adding Components to the Buttons Panel
		solveButton = new JButton("Solve");
		clearButton = new JButton("Clear");
		revealButton = new JButton("Reveal");
		revealButton.addActionListener(new ActionListener() {//Add actionlistner to listen for change in reveal button
	        public void actionPerformed(ActionEvent e) {
	        	Test.reveal = true;
	        }
	    });
	    
		//String[] dates = new String[]{"Today","30 October 2018", "31 October 2018", "1 November 2018", "2 November 2018"};
		String[] dates = new String[foldersInDirectory.size()];
		for(int i = 0; i < foldersInDirectory.size(); i++)
			dates[i] = foldersInDirectory.get(i);
		
		JComboBox<String> others = new JComboBox<>(dates);
		
	    Dimension d = others.getPreferredSize();
	    others.setPreferredSize(new Dimension(50, d.height));
	    others.addActionListener(new ActionListener() {//Add actionlistner to listen for change in dropdown menu
	        public void actionPerformed(ActionEvent e) {

	            String s = (String) others.getSelectedItem();//get the selected item
	            Test.selection = s;
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
