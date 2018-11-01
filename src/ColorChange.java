import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.FocusListener;

public class ColorChange {

    public static FocusListener myFocusListener = new java.awt.event.FocusListener() {
        private final Color ACTIVE_COLOUR = Color.YELLOW;
        public void focusGained(java.awt.event.FocusEvent focusEvent) {
            JTextField src = (JTextField)focusEvent.getSource();
            src.setOpaque(true);
            src.setBackground(ACTIVE_COLOUR);
            Caret caret = src.getCaret();
            caret.setSelectionVisible(false);
            caret.setVisible(false);
        }

        public void focusLost(java.awt.event.FocusEvent focusEvent) {
            JTextField src = (JTextField)focusEvent.getSource();
            src.setOpaque(false);

            Caret caret = src.getCaret();
            caret.setSelectionVisible(false);
            caret.setVisible(false);
        }
    };
}