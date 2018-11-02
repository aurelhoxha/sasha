import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.Caret;

public class CrosswordPanel extends JPanel{

	private final Color SIDE_COLOUR = Color.CYAN;
	private final Color ACTIVE_COLOUR = Color.yellow;

	JLabel selectedQuestion;
	JPanel thePattern;
	static JTextField[] cellText = new JTextField[25];
	public CrosswordPanel(Integer[] myClueNumber) throws Exception {
//		selectedQuestion = new JLabel("Selected Question");
		thePattern = new JPanel();
//		selectedQuestion.setFont(new Font("Serif",Font.BOLD,20));
//		selectedQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);

		thePattern.setLayout(new GridLayout(5,5));
		//int count = myClueNumber.length;
		for(int i = 0; i < myClueNumber.length; i++ )
			if (myClueNumber[i] == -1) {
				cellText[i] = new JTextField();
				UpperCaseDocument ucd = new UpperCaseDocument();
				cellText[i].setDocument(ucd);
				cellText[i].setBackground(Color.BLACK);
				cellText[i].setEditable(false);
				cellText[i].setBorder(BorderFactory.createLineBorder(Color.gray));
				thePattern.add(cellText[i]);
			} else {
				String cellimg = "./img/" + myClueNumber[i] + ".png";
				cellText[i] = new JTextField();
				UpperCaseDocument ucd = new UpperCaseDocument();
				cellText[i].setDocument(ucd);
				int countForColor=i;
				cellText[i].addFocusListener(new FocusListener(){
					@Override
					public void focusGained(java.awt.event.FocusEvent focusEvent) {

						cellText[countForColor] = (JTextField)focusEvent.getSource();
						cellText[countForColor].setOpaque(true);
						cellText[countForColor].setBackground(ACTIVE_COLOUR);
						Caret caret = cellText[countForColor].getCaret();
						caret.setSelectionVisible(false);
						caret.setVisible(false);
					}

					public void focusLost(java.awt.event.FocusEvent focusEvent) {
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
						if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							if (myClueNumber[count -1] == -1) {
								cellText[count - 2].setText("");
								cellText[count - 2].requestFocus();
							}
							else {
								cellText[count - 1].setText("");
								cellText[count - 1].requestFocus();
							}
						}
					}

					public void keyReleased(KeyEvent event) {
						int pos = cellText[count].getCaretPosition();
						cellText[count].setText(cellText[count].getText().toUpperCase());
						cellText[count].setCaretPosition(pos);
						char ch = cellText[count].getText().charAt(0);

						if (ch == 'A' || ch == 'B' || ch == 'C'|| ch == 'Q'|| ch == 'W'|| ch == 'E'|| ch == 'R'|| ch == 'T'
								|| ch == 'Y'|| ch == 'U'|| ch == 'I'|| ch == 'O'|| ch == 'P'|| ch == 'Z'|| ch == 'S'|| ch == 'D'
								|| ch == 'F'|| ch == 'G'|| ch == 'H'|| ch == 'J'|| ch == 'K'|| ch == 'L'|| ch == 'X'|| ch == 'V'
								/*|| ch == 'İ'*/|| ch == 'N'|| ch == 'M') {
							if(myClueNumber[count+1] ==-1)
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
				JLabel cell = new JLabel(new ImageIcon(cellimg));
				cell.setOpaque(true);
				cell.setLayout(new BorderLayout());
				cell.add(cellText[i]);
				thePattern.add(cell);

			}

		setLayout(new BorderLayout());
		//add(selectedQuestion, BorderLayout.NORTH);
		add(thePattern,BorderLayout.CENTER);
	}

}
