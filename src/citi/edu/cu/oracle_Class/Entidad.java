package citi.edu.cu.oracle_Class;



public class Entidad {
	
    private String Id;
    private String Codigo;
    private String Descripcion;
    
    private String fechaDesde;
    private String fechaHasta;
    

       
    public Entidad() {
        super();
    }
    
    
    

    public Entidad(String Id, String Codigo, String Descripcion, String pFechaDesde, String pFechaHasta) {
        super();
        this.Id = Id;
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.fechaDesde = pFechaDesde;
        this.fechaHasta = pFechaHasta;
    }
    
    
    
    
    

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
    
    
}
