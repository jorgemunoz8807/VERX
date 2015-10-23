package citi.edu.cu.oracle_Class;

import java.io.Serializable;

public class Aduana extends Entidad implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tipo;
    private String nivel;

    public Aduana() {
        super();
    }
    
    public Aduana(String idAduana, String codigo, String descripcion, String tipo,String nivel, 
    		String pFechaDesde, String pFechaHasta) {
        super(idAduana, codigo, descripcion, pFechaDesde, pFechaHasta);        
        this.tipo = tipo;
        this.nivel = nivel;        
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }
}
