package citi.edu.cu.image_played.Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileSystemView;
import sun.reflect.generics.tree.BottomSignature;

import java.awt.Font;
import java.awt.Dimension;

public class VImage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel ShowPanel = null;
	private JPanel ToolsPanel = null;
	private JButton BackButton = null;
	private JButton PlayButton = null;
	private JButton NextButton = null;
	private Timer timer = null;
	private LinkedList<ImageIcon> img_list = null;
	private JScrollPane ShowScrollPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu FileMenu = null;
	private JMenuItem OpenMenuItem = null;
	private JMenuItem ExitMenuItem = null;
	private JSlider SpeedSlider = null;
	private JPanel ContentToolsPanel = null;
	private DefaultBoundedRangeModel Hmodel = null; // @jve:decl-index=0:
	private DefaultBoundedRangeModel Speedmodel = null;  //  @jve:decl-index=0:
	private static final int DEFAULT_SPEED = 200;
	private int speed_increm = DEFAULT_SPEED;
	private int scroll_sense = 1;

	public VImage() {
		super();

		img_list = new LinkedList<ImageIcon>();
		timer = new Timer(speed_increm, Move_Event());

		Load_IMG();
		Load_IMG_In_Panel();
		initialize();
	}

	private void initialize() {
		this.setSize(454, 338);
		this.setUndecorated(true);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Visor de imágenes");
		this.setExtendedState(MAXIMIZED_BOTH);
	}

	private JScrollPane getShowScrollPane() {
		if (ShowScrollPane == null) {
			ShowScrollPane = new JScrollPane();
			ShowScrollPane.setViewportView(getShowPanel());
			ShowScrollPane.getHorizontalScrollBar().setModel(getHModel());
		}
		return ShowScrollPane;
	}

	private DefaultBoundedRangeModel getHModel() {

		if (Hmodel == null) {

			Hmodel = new DefaultBoundedRangeModel();
		}
		return Hmodel;
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			
		}
		return jJMenuBar;
	}

	private JMenu getFileMenu() {
		if (FileMenu == null) {
			FileMenu = new JMenu();
			FileMenu.setText("Archivo");
			FileMenu.add(getOpenMenuItem());
			FileMenu.add(getExitMenuItem());
		}
		return FileMenu;
	}

	private JMenuItem getOpenMenuItem() {
		if (OpenMenuItem == null) {
			OpenMenuItem = new JMenuItem();
			OpenMenuItem.setText("Abrir");

			OpenMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()

				}
			});
		}
		return OpenMenuItem;
	}

	private JMenuItem getExitMenuItem() {
		if (ExitMenuItem == null) {
			ExitMenuItem = new JMenuItem();
			ExitMenuItem.setText("Salir");
			ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					dispose();
				}
			});
		}
		return ExitMenuItem;
	}

	/*
	 * private JSlider getSpeedSlider() { if (SpeedSlider == null) { SpeedSlider
	 * = new JSlider(getSpeedModel()); SpeedSlider.setSnapToTicks(true);
	 * SpeedSlider.setPaintTicks(true); SpeedSlider.setPaintLabels(true);
	 * SpeedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
	 * public void stateChanged(javax.swing.event.ChangeEvent e) {
	 * System.out.println("stateChanged()"); // TODO Auto-generated Event stub
	 * stateChanged()
	 * 
	 * speed_increm=1000 - getSpeedModel().getValue()*100;
	 * timer.setDelay(speed_increm); ValueSpeedLabel.setText("  " +
	 * String.valueOf(getSpeedModel().getValue()) + "x   "); } }); } return
	 * SpeedSlider; }
	 */

	private DefaultBoundedRangeModel getSpeedModel() {

		if (Speedmodel == null) {

			Speedmodel = new DefaultBoundedRangeModel(2, 0, 0, 10);

		}

		return Speedmodel;
	}

	private JPanel getContentToolsPanel() {
		if (ContentToolsPanel == null) {
			ContentToolsPanel = new JPanel();
			ContentToolsPanel.setLayout(new BorderLayout());
			ContentToolsPanel.add(getToolsPanel(), BorderLayout.CENTER);
		}
		return ContentToolsPanel;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VImage thisClass = new VImage();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getShowScrollPane(), BorderLayout.CENTER);
			jContentPane.add(getContentToolsPanel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private JPanel getShowPanel() {
		if (ShowPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			ShowPanel = new JPanel();
			ShowPanel.setLayout(gridLayout);
			ShowPanel.setBackground(SystemColor.controlDkShadow);
		}
		return ShowPanel;
	}

	private JPanel getToolsPanel() {
		if (ToolsPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			ToolsPanel = new JPanel();
			ToolsPanel.setLayout(new GridBagLayout());
			ToolsPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			ToolsPanel.add(getBackButton(), new GridBagConstraints());
			ToolsPanel.add(getPlayButton(), gridBagConstraints);
			ToolsPanel.add(getNextButton(), new GridBagConstraints());

		}
		return ToolsPanel;
	}

	private JButton getBackButton() {
		if (BackButton == null) {
			BackButton = new JButton();
			BackButton.setText("");
			BackButton.setSelected(false);
			BackButton.setBorderPainted(false);
			BackButton.setContentAreaFilled(false);
			BackButton.setIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Back_Button.png")));
			BackButton.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Back_Button_Rollover.png")));
			BackButton.setPressedIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Back_Button_Pressed.png")));
			BackButton.setMargin(new Insets(1, 8, 1, 8));
			BackButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()

					if (scroll_sense > 0)
						scroll_sense *= (-1);

					if (!timer.isRunning()) {
						timer.start();

						PlayButton
								.setIcon(new ImageIcon(
										getClass()
												.getResource(
														"/cu/proyect/image_played/image/Stop_Button.png")));
						PlayButton
								.setRolloverIcon(new ImageIcon(
										getClass()
												.getResource(
														"/cu/proyect/image_played/image/Stop_Button_Rollover.png")));
						PlayButton
								.setPressedIcon(new ImageIcon(
										getClass()
												.getResource(
														"/cu/proyect/image_played/image/Stop_Button_Pressed.PNG")));
					}
				}
			});
		}
		return BackButton;
	}

	private JButton getPlayButton() {
		if (PlayButton == null) {
			PlayButton = new JButton();
			PlayButton.setText("");
			PlayButton.setBorderPainted(false);
			PlayButton.setSelected(true);
			PlayButton.setContentAreaFilled(false);
			PlayButton.setIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Play.png")));
			PlayButton.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Play_Rollover.png")));
			PlayButton.setPressedIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Play_Pressed.png")));
			PlayButton.setMargin(new Insets(1, 8, 1, 8));
			PlayButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()

					if (timer.isRunning()) {
						timer.stop();

						PlayButton
								.setIcon(new ImageIcon(getClass().getResource(
										"/citi/edu/cu/image_played/image/Play.png")));
						PlayButton
								.setRolloverIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Play_Rollover.png")));
						PlayButton
								.setPressedIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Play_Pressed.png")));
					} else {

						timer.start();

						PlayButton
								.setIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Stop_Button.png")));
						PlayButton
								.setRolloverIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Stop_Button_Rollover.png")));
						PlayButton
								.setPressedIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Stop_Button_Pressed.PNG")));
					}

				}
			});
		}
		return PlayButton;
	}

	private JButton getNextButton() {
		if (NextButton == null) {
			NextButton = new JButton();
			NextButton.setText("");
			NextButton.setIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Next_Button.png")));
			NextButton.setContentAreaFilled(false);
			NextButton.setBorderPainted(false);
			NextButton.setPressedIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Next_Button_Pressed.png")));
			NextButton.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/citi/edu/cu/image_played/image/Next_Button_Rollover.png")));
			NextButton.setMargin(new Insets(1, 8, 1, 8));
			NextButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()

					if (scroll_sense < 0)
						scroll_sense *= (-1);

					if (!timer.isRunning()) {
						timer.start();

						PlayButton
								.setIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Stop_Button.png")));
						PlayButton
								.setRolloverIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Stop_Button_Rollover.png")));
						PlayButton
								.setPressedIcon(new ImageIcon(
										getClass()
												.getResource(
														"/citi/edu/cu/image_played/image/Stop_Button_Pressed.PNG")));
					}
				}
			});
		}
		return NextButton;
	}

	// ******************************************MEthods****************************************************

	private void Load_IMG() {

		FileSystemView fsv = FileSystemView.getFileSystemView();

		File files[] = fsv.getFiles(new File(
				"Imagenes Radiologicas"), false);

		for (int i = 0; i < 20; i++) {

			File iter = files[i];
			String dir = iter.getAbsolutePath();

			ImageIcon img = new ImageIcon(dir);
			Image scale = img.getImage().getScaledInstance(
					img.getIconWidth() /*- 350*/, img.getIconHeight() /*- 350*/,
					Image.SCALE_FAST);

			img_list.add(new ImageIcon(scale));

		}

	}

	/**
	 * Cargo las imágenes que estan en la lista img_list para el ShowPanel
	 */
	private void Load_IMG_In_Panel() {

		for (int i = 0; i < img_list.size(); i++) {
			JLabel label = new JLabel(img_list.get(i));
			label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			getShowPanel().add(label);
		}
		repaint();
	}

	private java.awt.event.ActionListener Move_Event() {

		java.awt.event.ActionListener e = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				getHModel()
						.setValue(getHModel().getValue() + 20 * scroll_sense);

				if (getHModel().getValue() == getHModel().getMaximum()
						|| getHModel().getValue() == getHModel().getMinimum()) {

					getPlayButton().setIcon(
							new ImageIcon(getClass().getResource(
									"/citi/edu/cu/image_played/image/Play.png")));
					getPlayButton()
							.setRolloverIcon(
									new ImageIcon(
											getClass()
													.getResource(
															"/citi/edu/cu/image_played/image/Play_Rollover.png")));
					getPlayButton()
							.setPressedIcon(
									new ImageIcon(
											getClass()
													.getResource(
															"/citi/edu/cu/image_played/image/Play_Pressed.png")));
					getPlayButton().repaint();

					timer.stop();
				}

			}
		};

		return e;
	}

}  //  @jve:decl-index=0:visual-constraint="27,10"
