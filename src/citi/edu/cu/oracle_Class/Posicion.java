package citi.edu.cu.oracle_Class;

import java.io.Serializable;


public class Posicion extends Entidad implements Serializable{

	private static final long serialVersionUID = 1L;
	private String idTerminal;
	private String ip;
	private String mac;
	private String idTipoPosicion;
	private String codigoTipoPos;



	public Posicion() {
		super();
	}


	public Posicion(String idPosicion, String codigoPosicion, String descripcion, String terminal, String pip, String pmac, 
			String pIdTipoPosicion, String pFechaDesde, String pFechaHasta) {

		super(idPosicion, codigoPosicion, descripcion, pFechaDesde, pFechaHasta);
		idTerminal = terminal;        
		ip = pip;
		mac = pmac;
		idTipoPosicion = pIdTipoPosicion;
		codigoTipoPos=codigoPosicion;
	}
	
	


	
	public String getCodigoTipoPos() {
		return codigoTipoPos;
	}


	public void setCodigoTipoPos(String codigoTipoPos) {
		this.codigoTipoPos = codigoTipoPos;
	}


	public  String getIdTerminal() {
		return idTerminal;
	}


	public  void setIdTerminal(String terminal) {
		idTerminal = terminal;
	}


	public  String getIp() {
		return ip;
	}


	public  void setIp(String pip) {
		ip = pip;
	}


	public  String getMac() {
		return mac;
	}


	public  void setMac(String pmac) {
		mac = pmac;
	}


	public  long getSerialversionuid() {
		return serialVersionUID;
	}


	public  String getIdTipoPosicion() {
		return idTipoPosicion;
	}


	public  void setIdTipoPosicion(String pidTipoPosicion) {
		idTipoPosicion = pidTipoPosicion;
	}
	
}
