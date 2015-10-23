package citi.edu.cu.oracle_DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import citi.edu.cu.viewer_RX.ConfigDBVisual;

public class ConfigDB {
	private static String nameDB;
	private static String host;
	private static int port;
	private static String user;
	private static String pass;
	private static String newPass;
	private static String repeatPass;

	public ConfigDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String getNameDB() {
		return nameDB;
	}

	public static void setNameDB(String nameDB) {
		ConfigDB.nameDB = nameDB;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ConfigDB.host = host;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		ConfigDB.port = port;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		ConfigDB.user = user;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		ConfigDB.pass = pass;
	}

	public static String getNewPass() {
		return newPass;
	}

	public static void setNewPass(String newPass) {
		ConfigDB.newPass = newPass;
	}

	public static String getRepeatPass() {
		return repeatPass;
	}

	public static void setRepeatPass(String repeatPass) {
		ConfigDB.repeatPass = repeatPass;
	}

	// ******************Functions******************************************************
	/**
	 * Save in file configDB.cfg
	 */
	public static void SaveConfig(ConfigDB config) {
		try {
			File file = new File("configDB.cfg");
			FileOutputStream fosConfig = new FileOutputStream(file);

			ObjectOutputStream oosConfig = new ObjectOutputStream(fosConfig);
			oosConfig.writeObject(config.getNameDB());
			oosConfig.writeObject(config.getHost());
			oosConfig.writeObject(config.getPort());
			oosConfig.writeObject(config.getUser());
			oosConfig.writeObject(config.getPass());
			oosConfig.writeObject(config.getNewPass());
			oosConfig.writeObject(config.getRepeatPass());
			oosConfig.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Load DB config from file configDB.cfg
	 */

	public static void LoadConfig() {
		try {

			File file = new File("configDB.cfg");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			nameDB = (String) ois.readObject();
			host = (String) ois.readObject();
			port = (Integer) ois.readObject();
			user = (String) ois.readObject();
			pass = (String) ois.readObject();
			newPass = (String) ois.readObject();
			repeatPass = (String) ois.readObject();
			ois.close();
		} catch (Exception e) {

		}
	}

}
