package citi.edu.cu.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class StatusBar extends JWindow // implements Runnable {
{

	private static final long serialVersionUID = 1L;

	private static JProgressBar jProgressBar = null;


	/**
	 * Constructor
	 */
	public StatusBar() {

		this.setAlwaysOnTop(true);
		this.setSize(500, 45);
		this.setLocationRelativeTo(null);
		jProgressBar = new JProgressBar();
		jProgressBar.setString("Conectando con base de datos Aduana");
		jProgressBar.setStringPainted(true);
		jProgressBar.setFont(new Font("BOLD", Font.BOLD, 26));
		jProgressBar.setIndeterminate(true);
		
		this.add(jProgressBar, BorderLayout.CENTER); // BorderLayout.EAST

	}


	public static JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setSize(new Dimension(316, 62));
			jProgressBar.setMinimum(200);

		}
		return jProgressBar;
	}

} /* end class StatusBar */
