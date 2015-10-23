package citi.edu.cu.oracle_DB;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

import citi.edu.cu.oracle_Class.Entidad;
import citi.edu.cu.oracle_Class.Posicion;
import citi.edu.cu.oracle_Class.Terminal;

public class ConnectionOracle {

	private static Connection miCon = null;
	private static String ip;
	private static int port;
	private static String nameDB;
	private static String user;
	private static String passDB;

	private ConnectionOracle() {
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public static Connection getConexion() throws Exception {

		ConfigDB.LoadConfig();
		ip = ConfigDB.getHost();
		port = ConfigDB.getPort();
		nameDB = ConfigDB.getNameDB();
		user = ConfigDB.getUser();
		passDB = ConfigDB.getPass();

		if (miCon == null) {

			String jdbcUrl = "jdbc:oracle:thin:@//" + ip + ":" + port + "/"
					+ nameDB;
			String userid = user;
			String pass = passDB;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			miCon = DriverManager.getConnection(jdbcUrl, userid, pass);

		}
		return miCon;
	}

	// ============================================================================================

	/**
	 * Load list of Aduana
	 */
	public static LinkedList<Entidad> getAduanas() {

		try {
			// Connection con = getConexion();

			LinkedList<Entidad> listaAduanas = new LinkedList<Entidad>();
			String query = "SELECT * FROM TC_ADUANA";

			PreparedStatement pstm = miCon.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			Entidad entidad = null;

			while (rs.next()) {

				entidad = new Entidad(rs.getString("ID_ADUANA"), rs
						.getString("CODIGO"), rs.getString("DESCRIPCION"), rs
						.getString("F_INICIO"), rs.getString("F_FIN"));
				listaAduanas.add(entidad);
			}
			// con.close();
			// miCon = null;
			return listaAduanas;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ============================================================================================

	/**
	 * Load list of Terminal
	 */
	public static LinkedList<Terminal> getTerminales() {

		try {
			// Connection con = getConexion();

			LinkedList<Terminal> listaTerminales = new LinkedList<Terminal>();
			String query = "SELECT * FROM TC_TERMINAL";

			PreparedStatement pstm = miCon.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			Terminal terminal = null;

			while (rs.next()) {

				terminal = new Terminal(rs.getString("ID_TERMINAL"), rs
						.getString("CODIGO"), rs.getString("DESCRIPCION"), rs
						.getString("ID_ADUANA"), rs.getString("F_INICIO"), rs
						.getString("F_FIN"));
				listaTerminales.add(terminal);

			}
			// con.close();
			// miCon = null;
			return listaTerminales;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ============================================================================================

	/**
	 * Load list of Position
	 */
	public static LinkedList<Posicion> getPosiciones() {

		try {
			// Connection con = getConexion();

			LinkedList<Posicion> listaPosiciones = new LinkedList<Posicion>();
			String query = "SELECT * FROM TC_POSICION";

			PreparedStatement pstm = miCon.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			Posicion posicion = null;

			while (rs.next()) {

				if (!rs.getString("CODIGO_TIPO_POSICION").equals(
						"Posición CheckPoint")
						&& /*
							 * !rs.getString("DESCRIPCION").equals("POSICION CHECKPOINT"
							 * ) &&
							 */!rs.getString("CODIGO_TIPO_POSICION").equals(
								"Posición de Pesa")) {

					posicion = new Posicion(rs.getString("ID_POSICION"), rs
							.getString("CODIGO"), rs.getString("DESCRIPCION"),
							rs.getString("ID_TERMINAL"), rs.getString("IP"), rs
									.getString("MAC"), rs
									.getString("ID_TIPO_POSICION"), rs
									.getString("F_INICIO"), rs
									.getString("F_FIN"));

					listaPosiciones.add(posicion);
				}
			}
			// con.close();
			// miCon = null;
			return listaPosiciones;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Save image in DB
	 * 
	 * @param file
	 * @param IP_RX
	 * @throws Exception
	 */
	public static void SaveImageInDB(File file, String IP_RX) throws Exception {
		Connection conection = ConnectionOracle.getConexion();
		FileInputStream fis;
		PreparedStatement ps;
		try {
			fis = new FileInputStream(file);

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conection = ConnectionOracle.getConexion();

			String str = "insert into IMAGE_RX(IP_RX, IMAGE, CREATE_AT) values(?,?,?)";
			ps = conection.prepareStatement(str);
			ps.setString(1, IP_RX);
			ps.setBinaryStream(2, fis, (int) file.length());
			Calendar calendar = Calendar.getInstance();
			Timestamp time = new Timestamp(calendar.getTimeInMillis());
			ps.setTimestamp(3, time);

			System.out.println("success");
			ps.execute();
			ps.close();
			// conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// *******Functions used for MARCAJE aplications******

	public static String getCriterioDescripcion(String pIdCriterio) {

		try {
			// Connection con = getConexion();

			String queryMarcajes = "SELECT * FROM TC_CRITERIO";

			PreparedStatement pstm = miCon.prepareStatement(queryMarcajes);
			ResultSet rs = pstm.executeQuery();
			String descripcion = "";

			while (rs.next()) {

				if (rs.getString("ID_CRITERIO").equals(pIdCriterio)) {

					descripcion = rs.getString("DESCRIPCION");
				}
			}
			// miCon.close();
			return descripcion;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	// ============================================================================================

	public static String getModalidadDescripcion(String pModalidad) {

		try {
			// Connection con = getConexion();

			String queryMarcajes = "SELECT * FROM TC_MODALIDAD";

			PreparedStatement pstm = miCon.prepareStatement(queryMarcajes);
			ResultSet rs = pstm.executeQuery();
			String descripcion = "";

			while (rs.next()) {

				if (rs.getString("ID_MODALIDAD").equals(pModalidad)) {

					descripcion = rs.getString("DESCRIPCION");
				}
			}
			// miCon.close();
			return descripcion;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	// ============================================================================================

	public static String getProductoDescripcion(String pProducto) {

		try {
			// Connection con = getConexion();

			String queryMarcajes = "SELECT * FROM TC_PRODUCTO";

			PreparedStatement pstm = miCon.prepareStatement(queryMarcajes);
			ResultSet rs = pstm.executeQuery();

			String descripcion = "";

			while (rs.next()) {

				if (rs.getString("ID_PRODUCTO").equals(pProducto)) {

					descripcion = rs.getString("DESCRIPCION");
				}
			}
			// miCon.close();
			return descripcion;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	// ============================================================================================

	// ============================================================================================

	// FUNCIONES QUE DEVUELVEN LISTAS POR ID
	// ============================================================================================

	public static LinkedList<Terminal> getTerminalesAduana(
			LinkedList<Terminal> pListaTerminales, String codigo) {

		LinkedList<Terminal> listaTerminales = new LinkedList<Terminal>();

		for (int i = 0; i < pListaTerminales.size(); i++) {

			if (pListaTerminales.get(i).getCodigo().equals(codigo))
				listaTerminales.add(pListaTerminales.get(i));
		}

		return listaTerminales;
	}

	// ============================================================================================

	// OK
	public static LinkedList<Posicion> getPosicionesTerminales(
			LinkedList<Posicion> pListaPosiciones, String pIdTerminal) {

		LinkedList<Posicion> listaPosiciones = new LinkedList<Posicion>();

		for (int i = 0; i < pListaPosiciones.size(); i++) {

			if (pListaPosiciones.get(i).getIdTerminal().equals(pIdTerminal))
				listaPosiciones.add(pListaPosiciones.get(i));
		}
		return listaPosiciones;
	}

	// ============================================================================================

	// METODOS PARA ENCONTRAR LAS LISTAS EN UN RANGO DE FECHA
	// ============================================================================================
	public static LinkedList<Entidad> getAduanasRangoFecha(
			LinkedList<Entidad> pListaAduana, String pDesde, String pHasta,
			boolean pBuscando) {

		LinkedList<Entidad> lista = new LinkedList<Entidad>();

		Timestamp fecha1 = null;
		Timestamp fecha2 = null;

		if (pBuscando) {

			fecha1 = Timestamp.valueOf(pDesde);
			fecha2 = Timestamp.valueOf(pHasta);
		} else
			fecha1 = Timestamp.valueOf(pDesde);

		for (int i = 0; i < pListaAduana.size(); i++) {

			// if(pListaAduana.get(i).getId().equals(pIdAduana)){

			if (pBuscando) {

				if (fecha1.compareTo(Timestamp.valueOf(pListaAduana.get(i)
						.getFechaDesde())) < 0)
					continue;

				if (fecha2.compareTo(Timestamp.valueOf(pListaAduana.get(i)
						.getFechaHasta())) > 0)
					continue;

				/*
				 * if((fecha1.compareTo(Timestamp.valueOf(pListaAduana.get(i).
				 * getFechaDesde())) > 0 &&
				 * fecha2.compareTo(Timestamp.valueOf(pListaAduana
				 * .get(i).getFechaHasta())) < 0 ) ||
				 * (fecha1.compareTo(Timestamp
				 * .valueOf(pListaAduana.get(i).getFechaDesde())) == 0 &&
				 * fecha2.
				 * compareTo(Timestamp.valueOf(pListaAduana.get(i).getFechaHasta
				 * ())) == 0) )
				 */

				lista.add(pListaAduana.get(i));
				continue;
			} else {

				if ((fecha1.compareTo(Timestamp.valueOf(pListaAduana.get(i)
						.getFechaDesde())) > 0)
						|| (fecha1.compareTo(Timestamp.valueOf(pListaAduana
								.get(i).getFechaDesde())) == 0))
					lista.add(pListaAduana.get(i));

				/*
				 * if((fecha1.compareTo(Timestamp.valueOf(pListaAduana.get(i).
				 * getFechaDesde())) < 0 ) ||
				 * (fecha1.compareTo(Timestamp.valueOf
				 * (pListaAduana.get(i).getFechaDesde())) == 0) )
				 * lista.add(pListaAduana.get(i));
				 */
				continue;
			}
			// }
		}
		return lista;
	}

	// ============================================================================================

	public static LinkedList<Terminal> getTerminalesRangoFechaDeAduana(
			LinkedList<Terminal> pListaTerminal, String pIdAduana,
			String pDesde, String pHasta, boolean pBuscando) {

		LinkedList<Terminal> lista = new LinkedList<Terminal>();

		Timestamp fecha1 = null;
		Timestamp fecha2 = null;

		if (pBuscando) {

			fecha1 = Timestamp.valueOf(pDesde);
			fecha2 = Timestamp.valueOf(pHasta);
		} else
			fecha1 = Timestamp.valueOf(pDesde);

		for (int i = 0; i < pListaTerminal.size(); i++) {

			if (pListaTerminal.get(i).getIdAduana().equals(pIdAduana)) {

				if (pBuscando) {

					if (fecha1.compareTo(Timestamp.valueOf(pListaTerminal
							.get(i).getFechaDesde())) < 0)
						continue;

					if (fecha2.compareTo(Timestamp.valueOf(pListaTerminal
							.get(i).getFechaHasta())) > 0)
						continue;

					/*
					 * if((pDesde.compareTo(pListaTerminal.get(i).getFechaDesde()
					 * ) < 0 &&
					 * pHasta.compareTo(pListaTerminal.get(i).getFechaHasta()) >
					 * 0 ) ||
					 * (pDesde.compareTo(pListaTerminal.get(i).getFechaDesde())
					 * == 0 &&
					 * pHasta.compareTo(pListaTerminal.get(i).getFechaHasta())
					 * == 0) )
					 */

					lista.add(pListaTerminal.get(i));
					continue;
				} else {

					if ((fecha1.compareTo(Timestamp.valueOf(pListaTerminal.get(
							i).getFechaDesde())) > 0)
							|| (fecha1.compareTo(Timestamp
									.valueOf(pListaTerminal.get(i)
											.getFechaDesde())) == 0))
						lista.add(pListaTerminal.get(i));
					continue;
				}
			}
		}
		return lista;
	}

	// ============================================================================================

	public static LinkedList<Posicion> getPosicionesRangoFechaDeTerminal(
			LinkedList<Posicion> pListaPosiciones, String pIdTerminal,
			String pDesde, String pHasta, boolean pBuscando) {

		LinkedList<Posicion> lista = new LinkedList<Posicion>();

		Timestamp fecha1 = null;
		Timestamp fecha2 = null;

		if (pBuscando) {

			fecha1 = Timestamp.valueOf(pDesde);
			fecha2 = Timestamp.valueOf(pHasta);
		} else
			fecha1 = Timestamp.valueOf(pDesde);

		for (int i = 0; i < pListaPosiciones.size(); i++) {

			if (pListaPosiciones.get(i).getIdTerminal().equals(pIdTerminal)) {

				if (pBuscando) {

					if (fecha1.compareTo(Timestamp.valueOf(pListaPosiciones
							.get(i).getFechaDesde())) < 0)
						continue;

					if (fecha2.compareTo(Timestamp.valueOf(pListaPosiciones
							.get(i).getFechaHasta())) > 0)
						continue;

					lista.add(pListaPosiciones.get(i));
					continue;
				} else {

					if ((fecha1.compareTo(Timestamp.valueOf(pListaPosiciones
							.get(i).getFechaDesde())) > 0)
							|| (fecha1.compareTo(Timestamp
									.valueOf(pListaPosiciones.get(i)
											.getFechaDesde())) == 0))
						lista.add(pListaPosiciones.get(i));
					continue;
				}
			}
		}
		return lista;
	}

	// ============================================================================================

	// METODOS PARA ENCONTRAR UNA INSTANCIA SIMPLE
	// ============================================================================================
	public static Posicion foundPosicion(LinkedList<Posicion> pListaPosiciones,
			String pIdPosicion) {

		Posicion pos = null;

		boolean found = false;
		Iterator<Posicion> iterador = pListaPosiciones.iterator();

		while (iterador.hasNext() && !found) {

			pos = iterador.next();

			if (pos.getId().equals(pIdPosicion))
				found = true;
		}
		return pos;
	}

	// ============================================================================================

	public static Terminal foundTerminal(LinkedList<Terminal> pListaTerminal,
			String pIdTerminal) {

		Terminal terminal = null;

		boolean found = false;
		Iterator<Terminal> iterador = pListaTerminal.iterator();

		while (iterador.hasNext() && !found) {

			terminal = iterador.next();

			if (terminal.getId().equals(pIdTerminal))
				found = true;
		}
		return terminal;
	}

	// ============================================================================================

	public static void InsertarMarcaje(String pIdPosicion,
			String pIdCruceFrontera, String pCreateAt) {

		try {

			// Connection con = getConexion();

			String query = "INSERT INTO ADUANA.CONTROL_MARCAJE (ID_POSICION, ID_CRUCE_FRONTERA, CREATE_AT) VALUES ('"
					+ pIdPosicion
					+ "'"
					+ ","
					+ "'"
					+ pIdCruceFrontera
					+ "'"
					+ "," + "'" + pCreateAt + "')";

			PreparedStatement pstm;

			pstm = miCon.prepareStatement(query);

			pstm.executeQuery();

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ============================================================================================

}
