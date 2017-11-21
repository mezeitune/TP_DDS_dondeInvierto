package model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class IndicadorPrecalculado extends PersistentObject {

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	@OneToOne(cascade = CascadeType.ALL)
	private Indicador indicador;
	
	private String periodo;
	private int resultado;
	
	@Transient
	boolean flagResultado;

	public IndicadorPrecalculado(Empresa empresa, String periodo, int resultado,Indicador indicador, boolean flag) {
		this.empresa = empresa;
		this.periodo = periodo;
		this.resultado = resultado;
		this.indicador = indicador;
		this.flagResultado = flag;
	}

	public IndicadorPrecalculado(){
		
	}

	public int getResultado() {
		return resultado;
	}
	public Indicador getIndicador() {
		return indicador;
	}

	public String getPeriodo() {
		return periodo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public boolean getFlag() {
		return flagResultado;
	}

	
	

}
