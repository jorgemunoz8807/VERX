package citi.edu.cu.utils;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.TextField;

import javax.swing.JTextField;

/**
 * @author Manuel Morejon.
 *
 */
public class Validate{

	public static void validateLetter(TextField nombretextField) {
		nombretextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE) ||
						(Character.isWhitespace(c))))) {
					e.consume();
				}
			}
		});
	}
	
	public static void validateDigitAndComma(TextField hosttextField) {
		hosttextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) ||
						(c == '.') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
	}
	
	public static void validateDigit(TextField hosttextField) {
		hosttextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
	}
}