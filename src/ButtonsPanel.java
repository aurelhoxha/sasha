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
	JButton store;
	File directory;
	File[] directoryListAsFile;
	FileFilter directoryFileFilter;
	ArrayList<String> foldersInDirectory;
	String[] dates;
	JComboBox<String> others;
	Dimension d;
	
	//Constructors
	public ButtonsPanel(Integer[] myClueNumber) {
		//Set up the Components
		
		//ListSubdirectories
		directory = new File("./oldPuzzles/");
	    directoryFileFilter = new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    };
		
	    //Save the name of the folders in the directory
	    directoryListAsFile = directory.listFiles(directoryFileFilter);
	    foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
	    for (File directoryAsFile : directoryListAsFile) {
	        foldersInDirectory.add(directoryAsFile.getName());
	    }
		
		
		//Adding Components to the Buttons Panel
	    store = new JButton("Store");
		solveButton = new JButton("Solve");
		clearButton = new JButton("Clear");
		revealButton = new JButton("Reveal");
		
		//Takes the Dates from the Folder
		dates = new String[foldersInDirectory.size() + 1];
		dates[0] = "Today";
		for(int i = 1; i < foldersInDirectory.size() + 1; i++)
			dates[i] = foldersInDirectory.get(i-1);
		
		//Initialize the ComboBox
		others = new JComboBox<>(dates);
		
		//Set the dimensions of d
		d = others.getPreferredSize();
		
		//Set the size of the ComboBox
		others.setPreferredSize(new Dimension(50, d.height));
		
		//Add Action Listener to listen for change in clear button
		clearButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	for(int i = 0; i < 25; i++){
	        		if(myClueNumber[i] != -1)
	        			CrosswordPanel.cellText[i].setText("");
	        	}
	        }
	    });
		
		//Add Action Listener to listen for change in reveal button
		revealButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Test.reveal = true;
	        }
	    });
		
		//Add Action Listener to listen for change in store button
	    store.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Test.store = true;
	        }
	    });
	    
	    //Add Action Listener to listen for change in ComboBox menu 
	    others.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//Get the selected item
	            String s = (String) others.getSelectedItem();
	            Test.selection = s;
	        }
	    });
	    
	    solveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Test.solve = true;
	        }
	    });
	    
		//showOtherButton = new JButton("Old Puzzles");
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		add(Box.createHorizontalGlue());
		add(Box.createRigidArea(new Dimension(90, 0)));
		add(store);
		add(Box.createRigidArea(new Dimension(10, 0)));
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
