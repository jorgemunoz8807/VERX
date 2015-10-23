package citi.edu.cu.viewer_RX;

import javax.swing.UIManager;

public class Main {
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			// UIManager.setLookAndFeel(new TonicLookAndFeel());
			// UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
		} catch (Exception ex) {
		}
		SelectorVisor visor = new SelectorVisor();
		visor.setVisible(true);
	}
}