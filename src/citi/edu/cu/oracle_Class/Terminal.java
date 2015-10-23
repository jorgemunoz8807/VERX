package citi.edu.cu.oracle_Class;


public class Terminal extends Entidad{

	private String idAduana;


	public Terminal(String id, String codigo, String descripcion, String pIdAduana, String pFechaDesde, String pFechaHasta) {

		super(id, codigo, descripcion, pFechaDesde, pFechaHasta);
		idAduana =pIdAduana;
	}


	public String getIdAduana() {
		return idAduana;
	}


	public void setIdAduana(String pidAduana) {
		idAduana = pidAduana;
	}

}
