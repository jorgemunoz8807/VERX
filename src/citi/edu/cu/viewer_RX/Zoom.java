package citi.edu.cu.viewer_RX;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import java.awt.ComponentOrientation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import citi.edu.cu.utils.Test;

import com.glavsoft.viewer.Viewer;

import java.awt.GridBagLayout;

public class Zoom extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JButton jButton = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JPanel jPanel = null;
	private boolean pause=false;

	private static Viewer viewer=null;
	/**
	 * Variable para el conteo del panel q dio entrada al Zoom
	 */
	private int numpanel=-1;

	/**
	 * @param owner
	 */
	public Zoom(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowClosing(java.awt.event.WindowEvent e) {    
				System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
//				SelectorVisor visor=Main.getVisor();
//				visor.setJPanel1(jPanel);
				
			}
			public void windowOpened(java.awt.event.WindowEvent e) {
				System.out.println("windowOpened()"); // TODO Auto-generated Event stub windowOpened()
				jPanel.add(viewer.getSurface());
				viewer.getUiSettings().zoomToFit(jPanel.getWidth() , jPanel.getHeight(),Test.getViewer().getWorkingProtocol().getFbWidth(),Test.getViewer().getWorkingProtocol().getFbHeight() );
			}
		});
		this.jContentPane.updateUI();

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJJToolBarBar(), BorderLayout.NORTH);
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jJToolBarBar.setPreferredSize(new Dimension(11, 30));
			jJToolBarBar.setBackground(new Color(255, 255, 255));
			jJToolBarBar.add(getJButton());
			jJToolBarBar.add(getJButton2());
			jJToolBarBar.add(getJButton3());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setIcon(new ImageIcon(getClass().getResource("/com/glavsoft/viewer/images/player_play.gif")));
			jButton.setText("");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(pause ==false){
						jButton.setIcon(new ImageIcon(getClass().getResource("/com/glavsoft/viewer/images/player_pause.gif")));
						pause=true;
					}else{
						jButton.setIcon(new ImageIcon(getClass().getResource("/com/glavsoft/viewer/images/player_play.gif")));
						pause =false;
					}
					
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setIcon(new ImageIcon(getClass().getResource("/com/glavsoft/viewer/images/viewmag+.gif")));
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setIcon(new ImageIcon(getClass().getResource("/com/glavsoft/viewer/images/viewmag-.gif")));
		}
		return jButton3;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}

	public static Viewer getViewer() {
		return viewer;
	}

	public static void setViewer(Viewer viewer) {
		Zoom.viewer = viewer;
	}


}
