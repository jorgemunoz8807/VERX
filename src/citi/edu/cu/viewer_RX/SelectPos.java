package citi.edu.cu.viewer_RX;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import citi.edu.cu.oracle_Class.Entidad;
import citi.edu.cu.oracle_Class.Posicion;
import citi.edu.cu.oracle_Class.Terminal;
import citi.edu.cu.oracle_DB.ConnectionOracle;
import citi.edu.cu.utils.StatusBar;
import citi.edu.cu.utils.Test;

public class SelectPos extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JComboBox terminaljComboBox = null;

	private JComboBox aduanjComboBox = null;

	private JButton conectarjButton = null;

	private LinkedList<Entidad> listEntidad = new LinkedList<Entidad>(); // @jve:decl-index=0:

	private LinkedList<Terminal> listTer = new LinkedList<Terminal>(); // @jve:decl-index=0:

	private LinkedList<Posicion> listPosicions = new LinkedList<Posicion>(); // @jve:decl-index=0:

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	private DefaultTableModel defaultTableModel = null; // @jve:decl-index=0:visual-constraint="718,11"

	private JPanel jPanel = null;

	StatusBar bar = new StatusBar();

	/**
	 * @param owner
	 */
	public SelectPos(LinkedList<Entidad> listEntidad,
			LinkedList<Terminal> lstTerminal, LinkedList<Posicion> listPos) {
		super();
		this.listEntidad = listEntidad;
		this.listTer = lstTerminal;
		this.listPosicions = listPos;

		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		bar.setFont(new Font("Dialog", Font.BOLD, 14));
		this.setSize(644, 336);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("VERX-Seleccionar Posición RX.");
		this.setModal(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				System.out.println("windowOpened()"); // TODO Auto-generated
				// Event stub
				// windowOpened()

				LlenarComboAduana();

			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(354, 42, 77, 19));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel1.setText("Terminales");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(25, 42, 60, 19));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel.setText("Aduanas");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
//			jContentPane.setBackground(SystemColor.controlDkShadow);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getTerminaljComboBox(), null);
			jContentPane.add(getConectarjButton(), null);
			jContentPane.add(getAduanjComboBox(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJPanel(), null);

		}
		return jContentPane;
	}

	/**
	 * This method initializes terminaljComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	public JComboBox getTerminaljComboBox() {
		if (terminaljComboBox == null) {
			terminaljComboBox = new JComboBox();
			terminaljComboBox.setBounds(new Rectangle(440, 35, 155, 30));
			terminaljComboBox
					.addItemListener(new java.awt.event.ItemListener() {
						public void itemStateChanged(java.awt.event.ItemEvent e) {
							System.out.println("itemStateChanged()"); // TODO
							// Auto-generated
							// Event stub
							// itemStateChanged()
							LlenarTablaPosiciones();
						}
					});

		}
		return terminaljComboBox;
	}

	/**
	 * This method initializes conectarjButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getConectarjButton() {
		if (conectarjButton == null) {
			conectarjButton = new JButton();
			conectarjButton.setBounds(new Rectangle(508, 260, 104, 36));
			conectarjButton.setFont(new Font("Dialog", Font.BOLD, 16));
			conectarjButton.setText("Conectar");
			conectarjButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (jTable.getSelectedRow() == -1) {
								JOptionPane.showMessageDialog(jContentPane,
										"Debe seleccionar una posición","VERX-Mensaje",JOptionPane.ERROR_MESSAGE);
							} else {
								String host = jTable.getValueAt(
										jTable.getSelectedRow(), 1).toString();

								Test.StartViewer(new String[] { host,
										"-port=5900", "-password=" });
								// Test.StartViewer(new
								// String[]{"-host=192.168.202.104","-port=5900","-password=a"});

								new Thread(new Runnable() {
									@Override
									public void run() {
										try {
											Thread.currentThread().sleep(1000);
										} catch (InterruptedException e) {
											System.out.println("asdsf");
										}
										SwingUtilities
												.invokeLater(new Runnable() {
													@Override
													public void run() {
														SelectorVisor
																.ConectionVisor();
													}
												});
									}
								}).start();
								dispose();

							}
						}
					});
		}
		return conectarjButton;
	}

	/**
	 * This method initializes aduanjComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	public JComboBox getAduanjComboBox() {
		if (aduanjComboBox == null) {
			aduanjComboBox = new JComboBox();
			aduanjComboBox.setBounds(new Rectangle(133, 35, 155, 30));
			aduanjComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {

					terminaljComboBox.removeAllItems();
					String idAduan = null;
					for (int i = 0; i < listEntidad.size(); i++) {
						if (listEntidad.get(i).getCodigo().equals(
								aduanjComboBox.getSelectedItem().toString())) {
							idAduan = listEntidad.get(i).getId();
							break;
						}
					}
					for (int j = 0; j < listTer.size(); j++) {
						if (listTer.get(j).getIdAduana().equals(idAduan)) {
							terminaljComboBox.addItem(listTer.get(j)
									.getCodigo());
						}
					}

				}
			});
		}
		return aduanjComboBox;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(21, 105, 592, 141));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setModel(getDefaultTableModel());

		}
		return jTable;
	}

	/**
	 * This method initializes defaultTableModel
	 * 
	 * @return javax.swing.table.DefaultTableModel
	 */
	private DefaultTableModel getDefaultTableModel() {
		if (defaultTableModel == null) {
			defaultTableModel = new DefaultTableModel(new Object[] {
					"Posición", "Dirección IP" }, 0);

		}
		return defaultTableModel;
	}

	// -----------------------------------
	/**
	 * Conecta a la Base de Datos y carga las Aduanas, Terminales y Posiciones.
	 * 
	 * @throws Exception
	 */
	public void ConectToDataBase() throws Exception {

	}

	/**
	 * Llena el combo de Aduanas de la Base de Datos
	 */
	public void LlenarComboAduana() {
		for (int i = 0; i < listEntidad.size(); i++) {
			aduanjComboBox.addItem(listEntidad.get(i).getCodigo());
		}
	}

	/**
	 * Llena la tabla de posiciones
	 */
	public void LlenarTablaPosiciones() {
		defaultTableModel.setRowCount(0);
		if (terminaljComboBox.getItemCount() != 0) {
			for (int i = 0; i < listTer.size(); i++) {
				if (listTer.get(i).getCodigo().equals(
						terminaljComboBox.getSelectedItem().toString())) {
					for (int j = 0; j < listPosicions.size(); j++) {
						if (listPosicions.get(j).getIdTerminal().equals(
								listTer.get(i).getId())) {
							defaultTableModel.addRow(new Object[] {
									listPosicions.get(j).getCodigo(),
									listPosicions.get(j).getIp() });
						}
					}
					break;
				}
			}
		}
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
			jPanel.setBounds(new Rectangle(12, 83, 612, 175));
			jPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
					.createLineBorder(Color.black, 1), "Posiciones RX",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("sansserif",
							Font.BOLD, 12), new Color(59, 59, 59)));
			jPanel.setBackground(new Color(255, 255, 255));
		}
		return jPanel;
	}

}
