//PANEL THAT WILL CONTAIN THE BUTTONS OF THE APPLICATION

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import javax.swing.*;

public class ButtonsPanel extends JPanel {
	//Components
	JButton solveButton;
	JButton clearButton;
	JButton showOtherButton;
	JButton store;
	File directory;
	File[] directoryListAsFile;
	FileFilter directoryFileFilter;
	ArrayList<String> foldersInDirectory;
	String[] dates;
	String[] datesUpdated;
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
	    
	    if(Test.selection != "Today"){
	    	store.setEnabled(false);
	    }
	    
		solveButton = new JButton("Solve");
		clearButton = new JButton("Clear");
		
		//Takes the Dates from the Folder
		dates = new String[foldersInDirectory.size() + 1];
		dates[0] = "Today";
		int index = -1;
		boolean flag = false;
		
		//System.out.println("Getting all possible puzzles from folder");
		for(int i = 1; i < foldersInDirectory.size() + 1; i++) {
			dates[i] = foldersInDirectory.get(i-1);
			
			if(dates[i].equals(Test.gameDate) && Test.selection.equals("Today")) {
				index = i;
				flag = true;
			}
		}
		
		//Initialize the ComboBox
		others = new JComboBox<>(dates);
		
		if(Test.selection.equals("Today") && flag == true){
			datesUpdated = new String[foldersInDirectory.size()];
			for(int i = index; i < foldersInDirectory.size(); i++){
				dates[i] = dates[i + 1];
			}
			for(int i = 0; i < foldersInDirectory.size(); i++)
				datesUpdated[i] = dates[i];
			
			//Change options if selection is Today, so that we do not display the today's date too.
			others = new JComboBox<>(datesUpdated);
		}
		
		//Set the dimensions of d
		d = others.getPreferredSize();
		
		//Set the size of the ComboBox
		others.setPreferredSize(new Dimension(50, d.height));
		
		//Add Action Listener to listen for change in clear button
		clearButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//System.out.println("Clearing all puzzle cells");
	        	for(int i = 0; i < 25; i++){
	        		if(myClueNumber[i] != -1)
	        			CrosswordPanel.cellText[i].setText("");
	        	}
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
	            String s = (String)others.getSelectedItem();
	            System.out.println("Selected Date has changed to " + s);
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
		add(others);
		add(Box.createRigidArea(new Dimension(300, 0)));		
	}
}
