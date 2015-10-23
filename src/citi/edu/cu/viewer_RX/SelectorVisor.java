package citi.edu.cu.viewer_RX;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import citi.edu.cu.oracle_Class.Entidad;
import citi.edu.cu.oracle_Class.Posicion;
import citi.edu.cu.oracle_Class.Terminal;
import citi.edu.cu.oracle_DB.ConnectionOracle;
import citi.edu.cu.utils.StatusBar;
import citi.edu.cu.utils.Test;

import com.glavsoft.viewer.Viewer;

public class SelectorVisor extends JFrame {

	private static final long serialVersionUID = 1L;

	private static JPanel jContentPane = null;

	private static JPanel jPanel = null;

	private JToolBar jToolBar = null;

	private JButton conection_buttom = null;

	private JButton exit_buttom = null;

	private static JPanel jPanel1 = null;

	private static JPanel jPanel2 = null;

	private static JPanel jPanel3 = null;

	private static JPanel jPanel4 = null;

	/**
	 * Viewer utilizados para el control de cada conexion
	 */
	private static Viewer viewer1 = null;

	private static Viewer viewer2 = null;

	private static Viewer viewer3 = null;

	private static Viewer viewer4 = null;

	private static int time = 1500;

	/**
	 * Hilos para el control de salvar imagenes en BD
	 */
	private static Thread thread1 = null;
	private static Thread thread2 = null;
	private static Thread thread3 = null;
	private static Thread thread4 = null;
	/**
	 * Variable para llevar la disponibilidad de los paneles segun los viewer
	 */

	/**
	 * Variable para llevar el conteo de paneles en uso
	 */
	// private static int cantPanel = 0;
	/**
	 * Variable para el cotrol de Selector Visor en Zoom.
	 */
	private int flag = 0;

	private JPanel jPanel5 = null;

	/**
	 * This is the default constructor
	 */
	public SelectorVisor() {
		super();
		initialize();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);

	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	// Point point = new Point(); // @jve:decl-index=0:
	private JButton back_buttom = null;

	private JButton play_buttom = null;

	public JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
					Color.black));
			// jPanel1.setBackground(SystemColor.controlDkShadow);
			jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent evento) {

					if (evento.getClickCount() == 1) {
						if (evento.getButton() == MouseEvent.BUTTON1) {
							System.out.println("click izquierdo");
						}
						if (evento.getButton() == MouseEvent.BUTTON3) {
							System.out.println("click derescho");

							JPopupMenu menuContextual = new JPopupMenu();
							// ****Pantalla
							// completa*************************************************

							final JMenuItem zoomMenuitem = new JMenuItem(
									"Pantalla completa");
							zoomMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/viewmag+.gif")));
							menuContextual.add(zoomMenuitem);
							// Guardar imagen***********
							JMenuItem saveMenuitem = new JMenuItem(
									"Guardar imagen a: ");
							saveMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/filesave.gif")));
							menuContextual.add(saveMenuitem);

							/*
							 * // ***Guardar imágenes cada*************** JMenu
							 * saveAllMenu = new JMenu(
							 * "Guardar imágenes cada:"); final JCheckBox ms100
							 * = new JCheckBox("100ms"); final JCheckBox ms200 =
							 * new JCheckBox("200ms"); final JCheckBox ms300 =
							 * new JCheckBox("300ms"); final JCheckBox ms400 =
							 * new JCheckBox("400ms"); final JCheckBox ms500 =
							 * new JCheckBox("500ms");
							 * 
							 * saveAllMenu.add(ms100); saveAllMenu.add(ms200);
							 * saveAllMenu.add(ms300); saveAllMenu.add(ms400);
							 * saveAllMenu.add(ms500);
							 * menuContextual.add(saveAllMenu);
							 */

							// *******Actualizar**************************
							JMenuItem refreshMenuItem = new JMenuItem(
									"Actualizar");
							refreshMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/reload3.gif")));
							menuContextual.add(refreshMenuItem);

							// **************Aplicar Filtro***************

							JMenu filtro = new JMenu("Aplicar Filtro");

							filtro
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/thumbnail.gif")));

							JMenuItem inorganic = new JMenuItem("Inorgánico");
							JMenuItem grayScale = new JMenuItem(
									"Escala de grises");
							JMenuItem binary = new JMenuItem("Binario");
							JMenuItem armBlan = new JMenuItem(
									"Detectar armas blancas");
							JMenuItem muni = new JMenuItem(
									"Detectar municiones");

							filtro.add(inorganic);
							filtro.add(grayScale);
							filtro.add(binary);
							filtro.add(armBlan);
							filtro.add(muni);
							menuContextual.add(filtro);

							// *********Info Visor**********
							JMenuItem info = new JMenuItem(
									"Informacion del visor");

							info.setIcon(new ImageIcon(getClass().getResource(
									"/com/glavsoft/viewer/images/info.gif")));

							final StringBuilder message = new StringBuilder();

							message.append("Conectado a: ").append(
									viewer1.getWorkingProtocol()
											.getRemoteDesktopName()).append(
									"\n");
							message.append("IP: ").append(
									viewer1.getConnectionParams().hostName)
									.append("\n");
							message.append("Puerto: ").append(
									viewer1.getConnectionParams()
											.getPortNumber()).append("\n");

							message.append("Resolución: ").append(
									String.valueOf(viewer1.getSurface()
											.getWidth())).append(" \u00D7 ") // multiplication
									// sign
									.append(
											String.valueOf(viewer1.getSurface()
													.getHeight())).append("\n");
							message.append("Formato de color: ").append(
									String.valueOf(Math.round(Math.pow(2,
											viewer1.getWorkingProtocol()
													.getPixelFormat().depth))))
									.append(" colors (").append(
											String.valueOf(viewer1
													.getWorkingProtocol()
													.getPixelFormat().depth))
									.append(" bits)\n");
							message.append("Versión del protocolo: ").append(
									viewer1.getWorkingProtocol()
											.getProtocolVersion());
							if (viewer1.getWorkingProtocol().isTight()) {
								message.append("tight");
							}
							message.append("\n");

							menuContextual.add(info);

							// *********Desconectar********
							JMenuItem disconetMenuItem = new JMenuItem(
									"Desconectar");
							disconetMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/connect_no.gif")));
							menuContextual.add(disconetMenuItem);
							// *********************************************
							menuContextual.show(evento.getComponent(), evento
									.getX(), evento.getY());
							// ****Eventos de los Menu***********

							// ****Event Zoom*******
							zoomMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {

											setTitle(viewer1
													.getWorkingProtocol()
													.getRemoteDesktopName());
											jPanel.removeAll();

											BorderLayout borderLayout = new BorderLayout();
											jPanel.setLayout(borderLayout);

											viewer1
													.getUiSettings()
													.zoomToFit(
															jPanel.getWidth(),
															jPanel.getHeight(),
															viewer1
																	.getWorkingProtocol()
																	.getFbWidth(),
															viewer1
																	.getWorkingProtocol()
																	.getFbHeight());

											jPanel.add(jPanel1,
													BorderLayout.CENTER);

											play_buttom.setVisible(true);
											back_buttom.setVisible(true);

											numViewer = 1;

											jContentPane.updateUI();
											jPanel.updateUI();

											/*
											 * play_buttom
											 * .addActionListener(new
											 * java.awt.event.ActionListener() {
											 * public void actionPerformed(
											 * java.awt.event.ActionEvent e) {
											 * System.out .println("Boton====="
											 * + pause); // TODO
											 * Play_Pause(viewer1);
											 * 
											 * 
											 * } });
											 */

										}
									});
							inorganic.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer1.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Inorganic");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							grayScale.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer1.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"GrayScale");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							binary.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer1.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Binary");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							armBlan.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer1.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorArmasB");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							muni.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer1.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorMuniciones");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});

							/*
							 * // ******Event Save Image Intervall ms100
							 * .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false); if (ms100.isSelected())
							 * { time = 100; } else { time = 300; }
							 * System.out.println(time); } });
							 * 
							 * ms200 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms200.isSelected()) { time = 200; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms300 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms300.isSelected()) { time = 300; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms400 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms400.isSelected()) { time = 400; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms500 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * 
							 * if (ms500.isSelected()) { time = 500; } else {
							 * time = 300; } System.out.println(time); } });
							 */
							// *****Event Refresh******
							refreshMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											if (viewer1 != null)
												viewer1.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer2 != null)
												viewer2.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer3 != null)
												viewer3.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer4 != null)
												viewer4.getWorkingProtocol()
														.sendRefreshMessage();
										}
									});
							// ********Event Disconect
							disconetMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											viewer1
													.cleanUpUISessionAndConnection();
											jPanel1.removeAll();
											jPanel.updateUI();
											list.set(0, 0);

											thread1.stop();

										}
									});
							// *********Event Save Image TO:*********
							saveMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {

											JFileChooser chooser = new JFileChooser();
											FileNameExtensionFilter filter = new FileNameExtensionFilter(
													".JPG", "jpwwww");
											chooser.setFileFilter(filter);

											int we = chooser
													.showSaveDialog(jContentPane);
											if (we == JFileChooser.APPROVE_OPTION) {
												String imgName = chooser
														.getSelectedFile()
														.getAbsolutePath();

												Image img = viewer1
														.getSurface()
														.getRenderer()
														.getOffscreenImage();
												BufferedImage bimg = (BufferedImage) img;
												File file = new File(imgName
														+ ".jpg");

												try {
													ImageIO.write(bimg, "jpg",
															file);
												} catch (IOException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}

											}

										}

									});
							info
									.addActionListener(new java.awt.event.ActionListener() {
										public void actionPerformed(
												java.awt.event.ActionEvent e) {
											JOptionPane.showMessageDialog(
													jContentPane, message,
													"Información del visor", 1);
										}
									});
						}
					}

				}
			});
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
					Color.black));
			// jPanel2.setBackground(SystemColor.controlDkShadow);
			jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent evento) {

					if (evento.getClickCount() == 1) {
						if (evento.getButton() == MouseEvent.BUTTON1) {
							System.out.println("click izquierdo");
						}
						if (evento.getButton() == MouseEvent.BUTTON3) {
							System.out.println("click derescho");

							JPopupMenu menuContextual = new JPopupMenu();

							// ********Zoom Menu*********
							JMenuItem zoomMenuitem = new JMenuItem(
									"Pantalla completa");
							zoomMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/viewmag+.gif")));
							menuContextual.add(zoomMenuitem);

							/*
							 * // ********Save Image Interval********* JMenu
							 * saveAllMenu = new JMenu(
							 * "Guardar imágenes cada:"); final JCheckBox ms100
							 * = new JCheckBox("100ms"); final JCheckBox ms200 =
							 * new JCheckBox("200ms"); final JCheckBox ms300 =
							 * new JCheckBox("300ms"); final JCheckBox ms400 =
							 * new JCheckBox("400ms"); final JCheckBox ms500 =
							 * new JCheckBox("500ms");
							 * 
							 * saveAllMenu.add(ms100); saveAllMenu.add(ms200);
							 * saveAllMenu.add(ms300); saveAllMenu.add(ms400);
							 * saveAllMenu.add(ms500);
							 * menuContextual.add(saveAllMenu);
							 */
							// ********Save Image********
							JMenuItem saveMenuitem = new JMenuItem(
									"Guardar imagen a:");
							saveMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/filesave.gif")));
							menuContextual.add(saveMenuitem);

							// ********Refresh*********
							JMenuItem refreshMenuItem = new JMenuItem(
									"Actualizar");
							refreshMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/reload3.gif")));
							menuContextual.add(refreshMenuItem);

							// **************Aplicar Filtro***************

							JMenu filtro = new JMenu("Aplicar Filtro");
							filtro
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/thumbnail.gif")));
							JMenuItem inorganic = new JMenuItem("Inorgánico");
							JMenuItem grayScale = new JMenuItem(
									"Escala de grises");
							JMenuItem binary = new JMenuItem("Binario");
							JMenuItem armBlan = new JMenuItem(
									"Detectar armas blancas");

							JMenuItem muni = new JMenuItem(
									"Detectar municiones");

							filtro.add(inorganic);
							filtro.add(grayScale);
							filtro.add(binary);
							filtro.add(armBlan);
							filtro.add(muni);
							menuContextual.add(filtro);

							// *********Info Visor**********
							JMenuItem info = new JMenuItem(
									"Informacion del visor");

							info.setIcon(new ImageIcon(getClass().getResource(
									"/com/glavsoft/viewer/images/info.gif")));
							final StringBuilder message = new StringBuilder();

							message.append("Conectado a: ").append(
									viewer2.getWorkingProtocol()
											.getRemoteDesktopName()).append(
									"\n");
							message.append("IP: ").append(
									viewer2.getConnectionParams().hostName)
									.append("\n");
							message.append("Puerto: ").append(
									viewer2.getConnectionParams()
											.getPortNumber()).append("\n");

							message.append("Resolución: ").append(
									String.valueOf(viewer2.getSurface()
											.getWidth())).append(" \u00D7 ") // multiplication
									// sign
									.append(
											String.valueOf(viewer2.getSurface()
													.getHeight())).append("\n");
							message.append("Formato de color: ").append(
									String.valueOf(Math.round(Math.pow(2,
											viewer2.getWorkingProtocol()
													.getPixelFormat().depth))))
									.append(" colors (").append(
											String.valueOf(viewer2
													.getWorkingProtocol()
													.getPixelFormat().depth))
									.append(" bits)\n");
							message.append("Versión del protocolo: ").append(
									viewer2.getWorkingProtocol()
											.getProtocolVersion());
							if (viewer2.getWorkingProtocol().isTight()) {
								message.append("tight");
							}
							message.append("\n");

							menuContextual.add(info);
							// ********Disconect*********
							JMenuItem disconetMenuItem = new JMenuItem(
									"Desconectar");
							disconetMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/connect_no.gif")));
							menuContextual.add(disconetMenuItem);

							menuContextual.show(evento.getComponent(), evento
									.getX(), evento.getY());

							// ********EVENT MENU*********
							// ********EVENT MENU*********
							zoomMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jPanel.removeAll();
											BorderLayout borderLayout = new BorderLayout();
											jPanel.setLayout(borderLayout);
											viewer2
													.getUiSettings()
													.zoomToFit(
															jPanel.getWidth(),
															jPanel.getHeight(),
															viewer2
																	.getWorkingProtocol()
																	.getFbWidth(),
															viewer2
																	.getWorkingProtocol()
																	.getFbHeight());
											jPanel.add(jPanel2,
													BorderLayout.CENTER);

											play_buttom.setVisible(true);
											back_buttom.setVisible(true);

											jContentPane.updateUI();
											jPanel.updateUI();

											numViewer = 2;

										}
									});

							inorganic.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer2.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Inorganic");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							grayScale.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer2.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"GrayScale");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							binary.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer2.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Binary");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							armBlan.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer2.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorArmasB");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});

							muni.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer2.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorMuniciones");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});

							/*
							 * ms100 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false); if (ms100.isSelected())
							 * { time = 100; } else { time = 300; }
							 * System.out.println(time); } });
							 * 
							 * ms200 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms200.isSelected()) { time = 200; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms300 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms300.isSelected()) { time = 300; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms400 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms400.isSelected()) { time = 400; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms500 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * 
							 * if (ms500.isSelected()) { time = 500; } else {
							 * time = 300; } System.out.println(time); } });
							 */

							refreshMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											if (viewer1 != null)
												viewer1.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer2 != null)
												viewer2.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer3 != null)
												viewer3.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer4 != null)
												viewer4.getWorkingProtocol()
														.sendRefreshMessage();
										}
									});

							disconetMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											viewer2
													.cleanUpUISessionAndConnection();

											jPanel2.removeAll();
											jPanel.updateUI();
											list.set(1, 0);

										}
									});

							// *********Event Save Image TO:*********
							saveMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {

											JFileChooser chooser = new JFileChooser();
											FileNameExtensionFilter filter = new FileNameExtensionFilter(
													".JPG", "DAT");
											chooser.setFileFilter(filter);

											int we = chooser
													.showSaveDialog(jContentPane);
											if (we == JFileChooser.APPROVE_OPTION) {
												String imgName = chooser
														.getSelectedFile()
														.getAbsolutePath();

												Image img = viewer2
														.getSurface()
														.getRenderer()
														.getOffscreenImage();
												BufferedImage bimg = (BufferedImage) img;
												File file = new File(imgName
														+ ".jpg");

												try {
													ImageIO.write(bimg, "jpg",
															file);
												} catch (IOException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}

											}

										}

									});
							info
									.addActionListener(new java.awt.event.ActionListener() {
										public void actionPerformed(
												java.awt.event.ActionEvent e) {
											JOptionPane.showMessageDialog(
													jContentPane, message,
													"Información del visor", 1);
										}
									});
						}
					}

				}
			});

		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
					Color.black));
			// jPanel3.setBackground(SystemColor.controlDkShadow);
			jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent evento) {

					if (evento.getClickCount() == 1) {
						if (evento.getButton() == MouseEvent.BUTTON1) {
							System.out.println("click izquierdo");
						}
						if (evento.getButton() == MouseEvent.BUTTON3) {
							System.out.println("click derescho");

							JPopupMenu menuContextual = new JPopupMenu();
							JMenuItem zoomMenuitem = new JMenuItem(
									"Pantalla completa");
							zoomMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/viewmag+.gif")));
							menuContextual.add(zoomMenuitem);

							/*
							 * // ******Save Image Intervall**** JMenu
							 * saveAllMenu = new JMenu(
							 * "Guardar imágenes cada:"); final JCheckBox ms100
							 * = new JCheckBox("100ms"); final JCheckBox ms200 =
							 * new JCheckBox("200ms"); final JCheckBox ms300 =
							 * new JCheckBox("300ms"); final JCheckBox ms400 =
							 * new JCheckBox("400ms"); final JCheckBox ms500 =
							 * new JCheckBox("500ms");
							 * 
							 * saveAllMenu.add(ms100); saveAllMenu.add(ms200);
							 * saveAllMenu.add(ms300); saveAllMenu.add(ms400);
							 * saveAllMenu.add(ms500);
							 * menuContextual.add(saveAllMenu);
							 */

							// ****Save image********
							JMenuItem saveMenuitem = new JMenuItem(
									"Guardar imagen a:");
							saveMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/filesave.gif")));
							menuContextual.add(saveMenuitem);

							// ****Refresh********
							JMenuItem refreshMenuItem = new JMenuItem(
									"Actualizar");
							refreshMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/reload3.gif")));
							menuContextual.add(refreshMenuItem);

							// **************Aplicar Filtro***************

							JMenu filtro = new JMenu("Aplicar Filtro");
							filtro
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/thumbnail.gif")));
							JMenuItem inorganic = new JMenuItem("Inorgánico");
							JMenuItem grayScale = new JMenuItem(
									"Escala de grises");
							JMenuItem binary = new JMenuItem("Binario");

							JMenuItem armBlan = new JMenuItem(
									"Detectar armas blancas");

							JMenuItem muni = new JMenuItem(
									"Detectar municiones");

							filtro.add(inorganic);
							filtro.add(grayScale);
							filtro.add(binary);
							filtro.add(armBlan);
							filtro.add(muni);
							menuContextual.add(filtro);

							// *********Info Visor**********
							JMenuItem info = new JMenuItem(
									"Informacion del visor");

							info.setIcon(new ImageIcon(getClass().getResource(
									"/com/glavsoft/viewer/images/info.gif")));
							final StringBuilder message = new StringBuilder();

							message.append("Conectado a: ").append(
									viewer3.getWorkingProtocol()
											.getRemoteDesktopName()).append(
									"\n");
							message.append("IP: ").append(
									viewer3.getConnectionParams().hostName)
									.append("\n");
							message.append("Puerto: ").append(
									viewer3.getConnectionParams()
											.getPortNumber()).append("\n");

							message.append("Resolución: ").append(
									String.valueOf(viewer3.getSurface()
											.getWidth())).append(" \u00D7 ") // multiplication
									// sign
									.append(
											String.valueOf(viewer3.getSurface()
													.getHeight())).append("\n");
							message.append("Formato de color: ").append(
									String.valueOf(Math.round(Math.pow(2,
											viewer3.getWorkingProtocol()
													.getPixelFormat().depth))))
									.append(" colors (").append(
											String.valueOf(viewer3
													.getWorkingProtocol()
													.getPixelFormat().depth))
									.append(" bits)\n");
							message.append("Versión del protocolo: ").append(
									viewer3.getWorkingProtocol()
											.getProtocolVersion());
							if (viewer3.getWorkingProtocol().isTight()) {
								message.append("tight");
							}
							message.append("\n");

							menuContextual.add(info);
							// ****Disconect********
							JMenuItem disconetMenuItem = new JMenuItem(
									"Desconectar");
							disconetMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/connect_no.gif")));
							menuContextual.add(disconetMenuItem);

							menuContextual.show(evento.getComponent(), evento
									.getX(), evento.getY());

							// ****EVENT MENU********
							zoomMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jPanel.removeAll();
											BorderLayout borderLayout = new BorderLayout();
											jPanel.setLayout(borderLayout);
											viewer3
													.getUiSettings()
													.zoomToFit(
															jPanel.getWidth(),
															jPanel.getHeight(),
															viewer3
																	.getWorkingProtocol()
																	.getFbWidth(),
															viewer3
																	.getWorkingProtocol()
																	.getFbHeight());
											jPanel.add(jPanel3,
													BorderLayout.CENTER);

											play_buttom.setVisible(true);
											back_buttom.setVisible(true);

											jContentPane.updateUI();
											jPanel.updateUI();

											numViewer = 3;

										}
									});

							inorganic.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer3.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Inorganic");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							grayScale.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer3.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"GrayScale");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							binary.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer3.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Binary");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							armBlan.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer3.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorArmasB");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});

							muni.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer3.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorMuniciones");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});

							/*
							 * ms100 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false); if (ms100.isSelected())
							 * { time = 100; } else { time = 300; }
							 * System.out.println(time); } });
							 * 
							 * ms200 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms200.isSelected()) { time = 200; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms300 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms300.isSelected()) { time = 300; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms400 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms400.isSelected()) { time = 400; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms500 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * 
							 * if (ms500.isSelected()) { time = 500; } else {
							 * time = 300; } System.out.println(time); } });
							 */

							refreshMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											if (viewer1 != null)
												viewer1.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer2 != null)
												viewer2.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer3 != null)
												viewer3.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer4 != null)
												viewer4.getWorkingProtocol()
														.sendRefreshMessage();
										}
									});

							disconetMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {

											viewer3
													.cleanUpUISessionAndConnection();

											jPanel3.removeAll();
											jPanel.updateUI();
											list.set(2, 0);
										}
									});
							// *********Event Save Image TO:*********
							saveMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {

											JFileChooser chooser = new JFileChooser();
											FileNameExtensionFilter filter = new FileNameExtensionFilter(
													".JPG", "DAT");
											chooser.setFileFilter(filter);

											int we = chooser
													.showSaveDialog(jContentPane);
											if (we == JFileChooser.APPROVE_OPTION) {
												String imgName = chooser
														.getSelectedFile()
														.getAbsolutePath();

												Image img = viewer3
														.getSurface()
														.getRenderer()
														.getOffscreenImage();
												BufferedImage bimg = (BufferedImage) img;
												File file = new File(imgName
														+ ".jpg");

												try {
													ImageIO.write(bimg, "jpg",
															file);
												} catch (IOException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}

											}

										}

									});
							info
									.addActionListener(new java.awt.event.ActionListener() {
										public void actionPerformed(
												java.awt.event.ActionEvent e) {
											JOptionPane.showMessageDialog(
													jContentPane, message,
													"Información del visor", 1);
										}
									});
						}
					}

				}
			});
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
			jPanel4.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
					Color.black));
			// jPanel4.setBackground(SystemColor.controlDkShadow);
			jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent evento) {

					if (evento.getClickCount() == 1) {
						if (evento.getButton() == MouseEvent.BUTTON1) {
							System.out.println("click izquierdo");
						}
						if (evento.getButton() == MouseEvent.BUTTON3) {
							System.out.println("click derescho");

							JPopupMenu menuContextual = new JPopupMenu();
							// ********Zoom***********
							JMenuItem zoomMenuitem = new JMenuItem(
									"Pantalla completa");
							zoomMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/viewmag+.gif")));
							menuContextual.add(zoomMenuitem);

							/*
							 * // ***Save Image Intervall********** JMenu
							 * saveAllMenu = new JMenu(
							 * "Guardar imágenes cada:"); final JCheckBox ms100
							 * = new JCheckBox("100ms"); final JCheckBox ms200 =
							 * new JCheckBox("200ms"); final JCheckBox ms300 =
							 * new JCheckBox("300ms"); final JCheckBox ms400 =
							 * new JCheckBox("400ms"); final JCheckBox ms500 =
							 * new JCheckBox("500ms");
							 * 
							 * saveAllMenu.add(ms100); saveAllMenu.add(ms200);
							 * saveAllMenu.add(ms300); saveAllMenu.add(ms400);
							 * saveAllMenu.add(ms500);
							 * menuContextual.add(saveAllMenu);
							 */
							// ********Save Image***********
							JMenuItem saveMenuitem = new JMenuItem(
									"Guardar imagen a:");
							saveMenuitem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/filesave.gif")));
							menuContextual.add(saveMenuitem);

							// ********Refresh***********
							JMenuItem refreshMenuItem = new JMenuItem(
									"Actualizar");
							refreshMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/reload3.gif")));
							menuContextual.add(refreshMenuItem);

							// **************Aplicar Filtro***************

							JMenu filtro = new JMenu("Aplicar Filtro");
							filtro
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/thumbnail.gif")));
							JMenuItem inorganic = new JMenuItem("Inorgánico");
							JMenuItem grayScale = new JMenuItem(
									"Escala de grises");
							JMenuItem binary = new JMenuItem("Binario");

							JMenuItem armBlan = new JMenuItem(
									"Detectar armas blancas");

							JMenuItem muni = new JMenuItem(
									"Detectar municiones");

							filtro.add(inorganic);
							filtro.add(grayScale);
							filtro.add(binary);
							filtro.add(armBlan);
							filtro.add(muni);
							menuContextual.add(filtro);

							// *********Info Visor**********
							JMenuItem info = new JMenuItem(
									"Informacion del visor");

							info.setIcon(new ImageIcon(getClass().getResource(
									"/com/glavsoft/viewer/images/info.gif")));

							final StringBuilder message = new StringBuilder();

							message.append("Conectado a: ").append(
									viewer4.getWorkingProtocol()
											.getRemoteDesktopName()).append(
									"\n");
							message.append("IP: ").append(
									viewer4.getConnectionParams().hostName)
									.append("\n");
							message.append("Puerto: ").append(
									viewer4.getConnectionParams()
											.getPortNumber()).append("\n");

							message.append("Resolución: ").append(
									String.valueOf(viewer4.getSurface()
											.getWidth())).append(" \u00D7 ") // multiplication
									// sign
									.append(
											String.valueOf(viewer4.getSurface()
													.getHeight())).append("\n");
							message.append("Formato de color: ").append(
									String.valueOf(Math.round(Math.pow(2,
											viewer4.getWorkingProtocol()
													.getPixelFormat().depth))))
									.append(" colors (").append(
											String.valueOf(viewer4
													.getWorkingProtocol()
													.getPixelFormat().depth))
									.append(" bits)\n");
							message.append("Versión del protocolo: ").append(
									viewer4.getWorkingProtocol()
											.getProtocolVersion());
							if (viewer4.getWorkingProtocol().isTight()) {
								message.append("tight");
							}
							message.append("\n");

							menuContextual.add(info);
							// ********Disconect***********
							JMenuItem disconetMenuItem = new JMenuItem(
									"Desconectar");
							disconetMenuItem
									.setIcon(new ImageIcon(
											getClass()
													.getResource(
															"/com/glavsoft/viewer/images/connect_no.gif")));
							menuContextual.add(disconetMenuItem);

							menuContextual.show(evento.getComponent(), evento
									.getX(), evento.getY());

							// ********EVENT MENU***********
							// ********EVENT MENU***********
							zoomMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jPanel.removeAll();
											BorderLayout borderLayout = new BorderLayout();
											jPanel.setLayout(borderLayout);
											viewer4
													.getUiSettings()
													.zoomToFit(
															jPanel.getWidth(),
															jPanel.getHeight(),
															viewer4
																	.getWorkingProtocol()
																	.getFbWidth(),
															viewer4
																	.getWorkingProtocol()
																	.getFbHeight());
											jPanel.add(jPanel4,
													BorderLayout.CENTER);

											play_buttom.setVisible(true);
											back_buttom.setVisible(true);

											jContentPane.updateUI();
											jPanel.updateUI();

											numViewer = 4;

										}
									});

							inorganic.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer4.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Inorganic");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							grayScale.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer4.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"GrayScale");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							binary.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer4.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"Binary");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							armBlan.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer4.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorArmasB");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});

							muni.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {

									try {
										File file = new File("Buen Estado/1"
												+ ".bmp");

										Image img = viewer4.getSurface()
												.getRenderer()
												.getOffscreenImage();
										BufferedImage bimg = (BufferedImage) img;
										try {
											ImageIO.write(bimg, "bmp", file);
										} catch (IOException e2) { // TODO

										}
										Process p = Runtime.getRuntime().exec(
												"DetectorMuniciones");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});

							/*
							 * ms100 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false); if (ms100.isSelected())
							 * { time = 100; } else { time = 300; }
							 * System.out.println(time); } });
							 * 
							 * ms200 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms200.isSelected()) { time = 200; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms300 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms400.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms300.isSelected()) { time = 300; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms400 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms500.setSelected(false);
							 * 
							 * if (ms400.isSelected()) { time = 400; } else {
							 * time = 300; } System.out.println(time); } });
							 * 
							 * ms500 .addActionListener(new
							 * java.awt.event.ActionListener() { public void
							 * actionPerformed( java.awt.event.ActionEvent e) {
							 * System.out .println("actionPerformed()"); // TODO
							 * 
							 * ms100.setSelected(false);
							 * ms200.setSelected(false);
							 * ms300.setSelected(false);
							 * ms400.setSelected(false);
							 * 
							 * if (ms500.isSelected()) { time = 500; } else {
							 * time = 300; } System.out.println(time); } });
							 */

							refreshMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											if (viewer1 != null)
												viewer1.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer2 != null)
												viewer2.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer3 != null)
												viewer3.getWorkingProtocol()
														.sendRefreshMessage();
											if (viewer4 != null)
												viewer4.getWorkingProtocol()
														.sendRefreshMessage();
										}
									});

							disconetMenuItem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {

											viewer4
													.cleanUpUISessionAndConnection();

											jPanel4.removeAll();
											jPanel.updateUI();
											list.set(3, 0);
										}
									});

							// *********Event Save Image TO:*********
							saveMenuitem
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {

											JFileChooser chooser = new JFileChooser();
											FileNameExtensionFilter filter = new FileNameExtensionFilter(
													".JPG", "DAT");
											chooser.setFileFilter(filter);

											int we = chooser
													.showSaveDialog(jContentPane);
											if (we == JFileChooser.APPROVE_OPTION) {
												String imgName = chooser
														.getSelectedFile()
														.getAbsolutePath();

												Image img = viewer4
														.getSurface()
														.getRenderer()
														.getOffscreenImage();
												BufferedImage bimg = (BufferedImage) img;
												File file = new File(imgName
														+ ".jpg");

												try {
													ImageIO.write(bimg, "jpg",
															file);
												} catch (IOException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}

											}

										}

									});
							info
									.addActionListener(new java.awt.event.ActionListener() {
										public void actionPerformed(
												java.awt.event.ActionEvent e) {
											JOptionPane.showMessageDialog(
													jContentPane, message,
													"Información del visor", 1);
										}
									});
						}
					}

				}
			});
		}
		return jPanel4;
	}

	/**
	 * This method initializes exit_buttom
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getExitButton() {
		if (exit_buttom == null) {
			exit_buttom = new JButton();
			exit_buttom.setText("Salir");
			exit_buttom.setFont(new Font("Dialog", Font.BOLD, 13));
			exit_buttom.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/exit.gif")));
			exit_buttom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO

					int confirmacion1 = JOptionPane.showConfirmDialog(
							jContentPane, "Desea salir del sistema",
							"VERX-Salir", 0);
					if (confirmacion1 == 0) {
						System.exit(0);
					}

				}
			});
		}
		return exit_buttom;
	}

	/**
	 * This method initializes jPanel5
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(new GridBagLayout());
		}
		return jPanel5;
	}

	/**
	 * This method initializes back_buttom
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBackButtom() {
		if (back_buttom == null) {
			back_buttom = new JButton();
			back_buttom.setText("Ir atras");
			back_buttom.setVisible(false);
			back_buttom.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/undo.png")));
			back_buttom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					// Auto-generated
					// Event stub
					// actionPerformed()
					GridLayout gridLayout = new GridLayout();
					gridLayout.setRows(2);
					jPanel.setLayout(gridLayout);
					jPanel.add(getJPanel1(), null);
					jPanel.add(getJPanel2(), null);
					jPanel.add(getJPanel3(), null);
					jPanel.add(getJPanel4(), null);

					jPanel1.setSize(jPanel.getWidth() / 2,
							jPanel.getHeight() / 2);
					jPanel2.setSize(jPanel.getWidth() / 2,
							jPanel.getHeight() / 2);
					jPanel3.setSize(jPanel.getWidth() / 2,
							jPanel.getHeight() / 2);
					jPanel4.setSize(jPanel.getWidth() / 2,
							jPanel.getHeight() / 2);

					if (viewer1 != null)
						viewer1.getUiSettings().zoomToFit(
								jPanel1.getWidth() - 5,
								jPanel1.getHeight() - 5,
								viewer1.getWorkingProtocol().getFbWidth(),
								viewer1.getWorkingProtocol().getFbHeight());

					viewer1.getWorkingProtocol().getReceiverTask()
							.setPauseTask(false);

					if (viewer2 != null) {
						viewer2.getUiSettings().zoomToFit(
								jPanel2.getWidth() - 5,
								jPanel2.getHeight() - 5,
								viewer2.getWorkingProtocol().getFbWidth(),
								viewer2.getWorkingProtocol().getFbHeight());

						viewer2.getWorkingProtocol().getReceiverTask()
								.setPauseTask(false);
					}
					if (viewer3 != null) {
						viewer3.getUiSettings().zoomToFit(
								jPanel3.getWidth() - 5,
								jPanel3.getHeight() - 5,
								viewer3.getWorkingProtocol().getFbWidth(),
								viewer3.getWorkingProtocol().getFbHeight());

						viewer3.getWorkingProtocol().getReceiverTask()
								.setPauseTask(false);
					}
					if (viewer4 != null) {
						viewer4.getUiSettings().zoomToFit(
								jPanel4.getWidth() - 5,
								jPanel4.getHeight() - 5,
								viewer4.getWorkingProtocol().getFbWidth(),
								viewer4.getWorkingProtocol().getFbHeight());

						viewer4.getWorkingProtocol().getReceiverTask()
								.setPauseTask(false);

					}
					play_buttom.setVisible(false);
					back_buttom.setVisible(false);

					play_buttom.setText("Detener");
					play_buttom.setIcon(new ImageIcon(getClass().getResource(
							"/com/glavsoft/viewer/images/player_pause.gif")));
					pause = true;

					jContentPane.updateUI();

					System.out.println("Back=====" + pause);

				}
			});
		}
		return back_buttom;
	}

	/**
	 * This method initializes play_Buttom
	 * 
	 * @return javax.swing.JButton
	 */
	private boolean pause = true;
	private int numViewer;

	private JButton getPlay_Buttom() {
		if (play_buttom == null) {
			play_buttom = new JButton();
			play_buttom.setText("Detener");
			play_buttom.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/player_pause.gif")));
			play_buttom.setVisible(false);

			play_buttom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Boton=====" + pause); // TODO

					if (numViewer == 1)
						Play_Pause(viewer1);

					if (numViewer == 2)
						Play_Pause(viewer2);

					if (numViewer == 3)
						Play_Pause(viewer3);

					if (numViewer == 4)
						Play_Pause(viewer4);

				}
			});

		}
		return play_buttom;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	static ArrayList<Integer> list = new ArrayList<Integer>(); // @jve:decl-index=0:

	private JButton aboutVERX = null;

	private JButton configjButton1 = null;

	private JSeparator jSeparator1 = null;

	private void initialize() {

		this.setResizable(false);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("VERX-Visor de Equipos RX.");
		this.setVisible(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.out.println("windowClosing()"); // TODO Auto-generated
				// Event stub
				// windowClosing()
				int confirmacion1 = JOptionPane.showConfirmDialog(jContentPane,
						"Desea salir del sistema", "VERX-Salir", 0);
				if (confirmacion1 == 0) {
					System.exit(0);
				}

			}
		});
		this.setExtendedState(MAXIMIZED_BOTH);
		this.jPanel1.setSize(jPanel.getWidth() / 2, jPanel.getHeight() / 2);
		this.jPanel2.setSize(jPanel.getWidth() / 2, jPanel.getHeight() / 2);
		this.jPanel3.setSize(jPanel.getWidth() / 2, jPanel.getHeight() / 2);
		this.jPanel4.setSize(jPanel.getWidth() / 2, jPanel.getHeight() / 2);

		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
			jContentPane.add(getJToolBar(), BorderLayout.NORTH);
			jContentPane.add(getJPanel5(), BorderLayout.EAST);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			jPanel = new JPanel();
			jPanel.setBackground(Color.white);
			jPanel.setLayout(gridLayout);
			jPanel.add(getJPanel1(), null);
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJPanel3(), null);
			jPanel.add(getJPanel4(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	public JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setPreferredSize(new Dimension(25, 30));
			jToolBar
					.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			// jToolBar.setBackground(Color.white);
			jToolBar.add(getConectionButtom());
			jToolBar.addSeparator();
			jToolBar.add(getConfigjButton1());
			jToolBar.addSeparator();
			jToolBar.add(getBackButtom());
			jToolBar.addSeparator();
			jToolBar.add(getPlay_Buttom());
			jToolBar.addSeparator();
			jToolBar.add(getJSeparator1());
			jToolBar.add(getJButton());
			jToolBar.addSeparator();
			jToolBar.add(getExitButton());
		}
		return jToolBar;
	}

	public JButton getConectionButtom() {
		if (conection_buttom == null) {
			conection_buttom = new JButton();

			conection_buttom.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/connect_creating.gif")));
			conection_buttom.setBounds(new Rectangle(16, 1, 28, 20));
			conection_buttom.setFont(new Font("Dialog", Font.BOLD, 13));
			conection_buttom.setText("Nueva Conexión");
			conection_buttom
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {

							if (list.get(0) == 1 && list.get(1) == 1
									&& list.get(2) == 1 && list.get(3) == 1) {
								JOptionPane
										.showMessageDialog(
												jContentPane,
												"Solo puede visualizar 4 equipos simultaneamente",
												"Mensaje",
												JOptionPane.INFORMATION_MESSAGE);

							} else {
								Thread thread = new Thread(
										new ConnectionRunnable());
								thread.start();
							}
						}
					});

		}
		return conection_buttom;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (aboutVERX == null) {
			aboutVERX = new JButton();
			aboutVERX.setText("Acerca de VERX");
			aboutVERX.setFont(new Font("Dialog", Font.BOLD, 13));
			aboutVERX.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/info.gif")));

			aboutVERX.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO

					final StringBuilder message = new StringBuilder();

					message.append("VERX  v_1.0").append("\n");
					message.append("Fecha de creación: 15/6/2013").append("\n");
					message.append("Autor: Jorge Alberto Muñoz Chaviano")
							.append("\n\n");

					message.append("Sistema creado para visualizar el trabajo "
							+ "\n" + "de los equipos de RX desde el centro "
							+ "\n" + "" + "de supervisión radiológica ");

					JOptionPane.showMessageDialog(jContentPane, message,
							"Acerca de VERX", JOptionPane.INFORMATION_MESSAGE);

				}
			});
		}
		return aboutVERX;
	}

	/**
	 * This method initializes jSeparator1
	 * 
	 * @return javax.swing.JSeparator
	 */
	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	/**
	 * This method initializes configjButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getConfigjButton1() {
		if (configjButton1 == null) {
			configjButton1 = new JButton();
			configjButton1.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/configure.gif")));
			configjButton1.setBounds(new Rectangle(16, 1, 28, 20));
			configjButton1.setText("Opciones");
			configjButton1.setFont(new Font("Dialog", Font.BOLD, 13));

			configjButton1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); // TODO
							// Auto-generated
							// Event
							// stub
							// actionPerformed()
							ConfigDBVisual configDBVisual = new ConfigDBVisual(
									null);
							configDBVisual.setVisible(true);
						}
					});
		}
		return configjButton1;
	}

	/**
	 * clase para el manejo de el progress bar y el llenado de las listas de
	 * datos (Aduanas, Terminales, Posiciones)
	 * 
	 * @author DraKo
	 * 
	 */
	class ConnectionRunnable implements Runnable {
		@Override
		public void run() {

			StatusBar bar = new StatusBar();

			bar.setVisible(true);

			try {
				ConnectionOracle.getConexion();
				bar.dispose();

				LinkedList<Entidad> listEntidad = ConnectionOracle.getAduanas();
				LinkedList<Terminal> listTer = ConnectionOracle.getTerminales();
				LinkedList<Posicion> listPosicions = ConnectionOracle
						.getPosiciones();

				SelectPos pos = new SelectPos(listEntidad, listTer,
						listPosicions);
				pos.setVisible(true);

			} catch (Exception e1) {
				e1.printStackTrace();
				bar.dispose();
				JOptionPane.showMessageDialog(SelectorVisor.this,
						"No se pudo conectar con la base de datos." + "\n"
								+ "Revise la configuración y asegúrese" + "\n"
								+ "que este activa", "Error de conexión",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// ---------------------------------------------------------
	/**
	 *Retorna el panel disponible para visualizar
	 */
	public static int AvailablePanel() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 0) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Crea la conexion con un equipo de RX
	 */
	public static void ConectionVisor() {
		int pos = AvailablePanel();

		if (pos == 0) {
			viewer1 = Test.getViewer();
			viewer1.setSurface(Test.getViewer().getSurface());
			jPanel1.add(viewer1.getSurface());
			viewer1.getUiSettings().zoomToFit(jPanel1.getWidth() - 5,
					jPanel1.getHeight() - 5,
					viewer1.getWorkingProtocol().getFbWidth(),
					viewer1.getWorkingProtocol().getFbHeight());
			jContentPane.updateUI();
			list.set(0, 1);

			thread1 = new Thread(new AutoSaveImage(viewer1, time));
			thread1.start();

		}
		if (pos == 1) {

			viewer2 = Test.getViewer();
			viewer2.setSurface(Test.getViewer().getSurface());
			jPanel2.add(viewer2.getSurface());
			viewer2.getUiSettings().zoomToFit(jPanel2.getWidth() - 5,
					jPanel2.getHeight() - 5,
					viewer2.getWorkingProtocol().getFbWidth(),
					viewer2.getWorkingProtocol().getFbHeight());
			jContentPane.updateUI();

			list.set(1, 1);

			thread2 = new Thread(new AutoSaveImage(viewer2, time));
			thread2.start();

		}
		if (pos == 2) {

			viewer3 = Test.getViewer();
			viewer3.setSurface(Test.getViewer().getSurface());
			jPanel3.add(viewer3.getSurface());
			viewer3.getUiSettings().zoomToFit(jPanel3.getWidth() - 5,
					jPanel3.getHeight() - 5,
					viewer3.getWorkingProtocol().getFbWidth(),
					viewer3.getWorkingProtocol().getFbHeight());
			jContentPane.updateUI();

			list.set(2, 1);

			thread3 = new Thread(new AutoSaveImage(viewer3, time));
			thread3.start();

		}
		if (pos == 3) {

			viewer4 = Test.getViewer();
			viewer4.setSurface(Test.getViewer().getSurface());
			jPanel4.add(viewer4.getSurface());
			viewer4.getUiSettings().zoomToFit(jPanel4.getWidth() - 5,
					jPanel4.getHeight() - 5,
					viewer4.getWorkingProtocol().getFbWidth(),
					viewer4.getWorkingProtocol().getFbHeight());
			jContentPane.updateUI();

			list.set(3, 1);

			thread4 = new Thread(new AutoSaveImage(viewer4, time));
			thread4.start();
		}

	}

	/**
	 * Metodo para poner pausa al visor de imagenes.
	 * 
	 * @param viewer
	 */
	public void Play_Pause(Viewer viewer) {
		if (pause == false) {
			play_buttom.setText("Detener");
			play_buttom.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/player_pause.gif")));

			viewer.getWorkingProtocol().getReceiverTask().setPauseTask(pause);
		}
		if (pause == true) {
			play_buttom.setText("Continuar");
			play_buttom.setIcon(new ImageIcon(getClass().getResource(
					"/com/glavsoft/viewer/images/player_play.gif")));
			viewer.getWorkingProtocol().getReceiverTask().setPauseTask(pause);

		}

		pause = !pause;

	}

	// ==========================
	/**
	 * Save image in DB
	 * 
	 */
	static class AutoSaveImage implements Runnable {
		private Viewer viewer;
		private int time;

		public AutoSaveImage(Viewer viewer, int time) {
			this.viewer = viewer;
			this.time = time;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {

				Image img = viewer.getSurface().getRenderer()
						.getOffscreenImage();
				BufferedImage bimg = (BufferedImage) img;
				try {
					Calendar calendar = Calendar.getInstance();

					Timestamp time = new Timestamp(calendar.getTimeInMillis());

					File file = new File("imgSave" + ".jpg");
					ImageIO.write(bimg, "jpg", file);

					/**
					 * Para salvar sin tener que escribir primero en Hdd pero
					 * falta crear la funcion q salve en DB recibiendo un
					 * BufferImage
					 */
					/*
					 * Image img1 = viewer1.getSurface().getRenderer()
					 * .getOffscreenImage(); BufferedImage bimg1 =
					 * (BufferedImage) img1;
					 */

					ConnectionOracle.SaveImageInDB(file, viewer
							.getConnectionParams().getHostName());

				} catch (IOException e) {
					// TODO Auto-generated catch
					// block
					e.printStackTrace();
				} catch (Exception e) {

					// TODO Auto-generated catch
					// block
					e.printStackTrace();
				}

				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

} // @jve:decl-index=0:visual-constraint="6,3"
