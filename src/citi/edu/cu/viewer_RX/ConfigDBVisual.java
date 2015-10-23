package citi.edu.cu.viewer_RX;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import citi.edu.cu.oracle_DB.ConfigDB;
import citi.edu.cu.utils.Validate;

import java.awt.GridBagLayout;

public class ConfigDBVisual extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel dbConfigjPanel = null;
	private JLabel hostjLabel = null;
	private JLabel portjLabel = null;
	private TextField hosttextField = null;
	private TextField porttextField = null;
	private TextField usertextField = null;
	private JPasswordField passjPasswordField = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JButton AplicarjButton = null;
	private JButton canceljButton = null;
	private String host;
	private String pot;
	private String user;
	private String pass;
	private JLabel IPjLabel5 = null;
	private JLabel portjLabel5 = null;
	private JLabel userjLabel5 = null;
	private JPanel jPanel = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private TextField nombretextField = null;
	private JLabel jLabel8 = null;
	private JLabel userjLabel9 = null;

	/**
	 * @param owner
	 */
	public ConfigDBVisual(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(561, 306);
		this.setTitle("VERX-Configuración");
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setResizable(false);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				System.out.println("windowOpened()"); // TODO Auto-generated
				// Event stub
				// windowOpened()
				ConfigDB.LoadConfig();

				userjLabel5.setText(ConfigDB.getNameDB());
				IPjLabel5.setText(ConfigDB.getHost());
				portjLabel5.setText(String.valueOf(ConfigDB.getPort()));
				userjLabel9.setText(ConfigDB.getUser());

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
			userjLabel5 = new JLabel();
			userjLabel5.setText("");
			userjLabel5.setBounds(new Rectangle(121, 33, 93, 25));
			portjLabel5 = new JLabel();
			portjLabel5.setText("");
			portjLabel5.setBounds(new Rectangle(121, 111, 93, 25));
			IPjLabel5 = new JLabel();
			IPjLabel5.setText("");
			IPjLabel5.setBounds(new Rectangle(121, 72, 93, 25));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getDbConfigjPanel(), null);
			jContentPane.add(getAplicarjButton(), null);
			jContentPane.add(getCanceljButton(), null);
			jContentPane.add(getJPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes dbConfigjPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getDbConfigjPanel() {
		if (dbConfigjPanel == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(25, 27, 100, 16));
			jLabel7.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			jLabel7.setText("Nombre de la BD:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(54, 158, 71, 16));
			jLabel1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			jLabel1.setText("Contraseña:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(76, 126, 49, 16));
			jLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			jLabel.setText("Usuario:");
			portjLabel = new JLabel();
			portjLabel.setBounds(new Rectangle(83, 94, 42, 16));
			portjLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			portjLabel.setText("Puerto:");
			hostjLabel = new JLabel();
			hostjLabel.setBounds(new Rectangle(52, 62, 73, 16));
			hostjLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			hostjLabel.setText("Dirección IP:");
			dbConfigjPanel = new JPanel();
			dbConfigjPanel.setLayout(null);
			dbConfigjPanel.setBounds(new Rectangle(4, 9, 310, 255));
			dbConfigjPanel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black),
					"Configurar base de datos", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("Dialog", Font.BOLD, 14),
					new Color(51, 51, 51)));
			dbConfigjPanel.add(hostjLabel, null);
			dbConfigjPanel.add(portjLabel, null);
			dbConfigjPanel.add(getHosttextField(), null);
			dbConfigjPanel.add(getPorttextField(), null);

			dbConfigjPanel.add(getUsertextField(), null);
			dbConfigjPanel.add(getPassjPasswordField(), null);
			dbConfigjPanel.add(jLabel, null);
			dbConfigjPanel.add(jLabel1, null);
			dbConfigjPanel.add(jLabel7, null);
			dbConfigjPanel.add(getNombretextField(), null);
		}
		return dbConfigjPanel;
	}

	/**
	 * This method initializes hosttextField
	 * 
	 * @return java.awt.TextField
	 */
	private TextField getHosttextField() {
		if (hosttextField == null) {
			hosttextField = new TextField();
			hosttextField.setBounds(new Rectangle(136, 57, 152, 26));
			Validate.validateDigitAndComma(hosttextField);
		}
		return hosttextField;
	}

	/**
	 * This method initializes porttextField
	 * 
	 * @return java.awt.TextField
	 */
	private TextField getPorttextField() {
		if (porttextField == null) {
			porttextField = new TextField();
			porttextField.setBounds(new Rectangle(136, 89, 152, 26));
			Validate.validateDigit(porttextField);
		}
		return porttextField;
	}

	/**
	 * This method initializes usertextField
	 * 
	 * @return java.awt.TextField
	 */
	private TextField getUsertextField() {
		if (usertextField == null) {
			usertextField = new TextField();
			usertextField.setBounds(new Rectangle(136, 121, 152, 26));
			Validate.validateLetter(usertextField);

		}
		return usertextField;
	}

	/**
	 * This method initializes passjPasswordField
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getPassjPasswordField() {
		if (passjPasswordField == null) {
			passjPasswordField = new JPasswordField();
			passjPasswordField.setBounds(new Rectangle(136, 153, 152, 26));

		}
		return passjPasswordField;
	}

	/**
	 * This method initializes AplicarjButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAplicarjButton() {
		if (AplicarjButton == null) {
			AplicarjButton = new JButton();
			AplicarjButton.setBounds(new Rectangle(351, 238, 85, 26));
			AplicarjButton.setText("Aplicar");
			AplicarjButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); // TODO
							// Auto-generated
							// Event
							// stub
							// actionPerformed()

							ConfigDB configDB = new ConfigDB();
							configDB.setNameDB(nombretextField.getText());
							configDB.setHost(hosttextField.getText());
							configDB.setPort(Integer.valueOf(porttextField
									.getText()));
							configDB.setUser(usertextField.getText());
							configDB.setPass(passjPasswordField.getText());

							ConfigDB.SaveConfig(configDB);

							ConfigDB.LoadConfig();

							userjLabel5.setText(ConfigDB.getNameDB());
							IPjLabel5.setText(ConfigDB.getHost());
							portjLabel5.setText(String.valueOf(ConfigDB
									.getPort()));
							userjLabel9.setText(ConfigDB.getUser());
							
							JOptionPane.showMessageDialog(jContentPane, "La configuración de la base de datos ha sido cambiada satisfactoriamente,"+"\n"+"para utilizar la nueva configuración se recomienda reinicar el sistema.");

						}
					});
		}
		return AplicarjButton;
	}

	/**
	 * This method initializes canceljButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCanceljButton() {
		if (canceljButton == null) {
			canceljButton = new JButton();
			canceljButton.setBounds(new Rectangle(462, 238, 85, 26));
			canceljButton.setText("Cerrar");
			canceljButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); // TODO
							// Auto-generated
							// Event
							// stub
							// actionPerformed()
							dispose();
						}
					});
		}
		return canceljButton;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			userjLabel9 = new JLabel();
			userjLabel9.setBounds(new Rectangle(121, 150, 93, 25));
			userjLabel9.setText("");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(69, 154, 49, 16));
			jLabel8.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			jLabel8.setText("Usuario:");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(18, 37, 100, 16));
			jLabel6.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			jLabel6.setText("Nombre de la BD:");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(73, 115, 45, 16));
			jLabel5.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			jLabel5.setText("Puerto: ");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(42, 76, 76, 16));
			jLabel4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			jLabel4.setText("Dirección IP: ");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(320, 9, 227, 196));
			jPanel.add(IPjLabel5, null);
			jPanel.add(portjLabel5, null);
			jPanel.add(userjLabel5, null);
			jPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
					.createMatteBorder(1, 1, 1, 1, Color.black),
					"Configuración actual", TitledBorder.CENTER,
					TitledBorder.TOP, new Font("Dialog", Font.BOLD, 14),
					new Color(51, 51, 51)));
			jPanel.add(jLabel4, null);
			jPanel.add(jLabel5, null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel8, null);
			jPanel.add(userjLabel9, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes nombretextField
	 * 
	 * @return java.awt.TextField
	 */
	private TextField getNombretextField() {
		if (nombretextField == null) {
			nombretextField = new TextField();
			nombretextField.setBounds(new Rectangle(136, 25, 152, 26));
			Validate.validateLetter(nombretextField);
		}
		return nombretextField;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
