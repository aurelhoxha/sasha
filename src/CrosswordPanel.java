//PANEL THAT WILL CONTAIN THE PUZZLE CELLS AND WILL BE PLACED ON THE LEFT PART OF CENTER PANEL

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.Caret;

public class CrosswordPanel extends JPanel{

	private final Color SIDE_COLOUR = Color.CYAN;
	private final Color ACTIVE_COLOUR = Color.yellow;

	JPanel thePattern;
	static JTextField[] cellText = new JTextField[25];
	public CrosswordPanel(Integer[] myClueNumber) throws Exception {
		thePattern = new JPanel();
		thePattern.setLayout(new GridLayout(5,5));
		
		int fin = myClueNumber.length;
		for(int i = 0; i < fin; i++ )
			if (myClueNumber[i] == -1) {
				cellText[i] = new JTextField();
				UpperCaseDocument ucd = new UpperCaseDocument();
				cellText[i].setDocument(ucd);
				cellText[i].setBackground(Color.BLACK);
				cellText[i].setEditable(false);
				cellText[i].setBorder(BorderFactory.createLineBorder(Color.gray));
				thePattern.add(cellText[i]);
			} else {
				cellText[i] = new JTextField();
				UpperCaseDocument ucd = new UpperCaseDocument();
				cellText[i].setDocument(ucd);
				int countForColor=i;
				cellText[i].addFocusListener(new FocusListener(){
					@Override
					public void focusGained(java.awt.event.FocusEvent focusEvent) {
					/*Creating a focus event, when we click on a box or move to it by writing,it gets focused and turn yellow
						to be able to have benefits of celltext array, I had to use this focus listener as this unlike jtextfieldlimit
						as a separate class*/
						cellText[countForColor] = (JTextField)focusEvent.getSource();
						cellText[countForColor].setOpaque(true);
						cellText[countForColor].setBackground(ACTIVE_COLOUR);
						Caret caret = cellText[countForColor].getCaret();
						caret.setSelectionVisible(false);
						caret.setVisible(false);
						/*only for making cursor invisible,pls dont change if you dont know any method better*/
					}

					public void focusLost(java.awt.event.FocusEvent focusEvent) { //when you move to another box,it gets white again
						cellText[countForColor] = (JTextField)focusEvent.getSource();
						cellText[countForColor].setOpaque(false);
						Caret caret = cellText[countForColor].getCaret();
						caret.setSelectionVisible(false);
						caret.setVisible(false);
					}
				});
				int count = i;
				cellText[i].addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE) { //backspace function,i made with via blanking that button maybe there is better option
							if (myClueNumber[count -1] == -1) { //if black, just go and delete two cells back
								cellText[count - 2].setText("");
								cellText[count - 2].requestFocus();
							}
							else {
								cellText[count - 1].setText(""); //if not go and delete one cell back
								cellText[count - 1].requestFocus();
							}
						}
					}

					public void keyReleased(KeyEvent event) { /*when you push a key, and than release it, it automatically detects that you filled that box
																and moves*/
						int pos = cellText[count].getCaretPosition();
						cellText[count].setText(cellText[count].getText().toUpperCase());
						cellText[count].setCaretPosition(pos);
						char ch = cellText[count].getText().charAt(0); //for detecting key,i get char 0 which is the only char can box have.

						if (ch == 'A' || ch == 'B' || ch == 'C'|| ch == 'Q'|| ch == 'W'|| ch == 'E'|| ch == 'R'|| ch == 'T'
								|| ch == 'Y'|| ch == 'U'|| ch == 'I'|| ch == 'O'|| ch == 'P'|| ch == 'Z'|| ch == 'S'|| ch == 'D'
								|| ch == 'F'|| ch == 'G'|| ch == 'H'|| ch == 'J'|| ch == 'K'|| ch == 'L'|| ch == 'X'|| ch == 'V'
								/*|| ch == 'İ'*/|| ch == 'N'|| ch == 'M') {  //shitty solution for detecting alphabet.
							if(myClueNumber[count+1] == -1)
								cellText[count+2].requestFocus();
							else
								cellText[count+1].requestFocus();
						}
					}
				});

				cellText[i].setDocument(new JTextFieldLimit(1));
				cellText[i].setOpaque(false);
				cellText[i].setHorizontalAlignment(JTextField.CENTER);
				cellText[i].setFont(new Font("Helvetica", Font.PLAIN, 33));
				cellText[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				cellText[i].setBorder(BorderFactory.createLineBorder(Color.gray));
				
				if(myClueNumber[i] == 0){
					thePattern.add(cellText[i]);
				}
				else {
					String cellimg = "./img/" + myClueNumber[i] + ".png";
					JLabel cell = new JLabel(new ImageIcon(cellimg));
					cell.setOpaque(true);
					cell.setLayout(new BorderLayout());
					cell.add(cellText[i]);
					thePattern.add(cell);
				}
			}

		setLayout(new BorderLayout());
		//add(selectedQuestion, BorderLayout.NORTH);
		add(thePattern,BorderLayout.CENTER);
	}
}
