package model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class IndicadorPrecalculado extends PersistentObject {

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	private String periodo;
	private int resultado;
	private String nombre;
	

	public IndicadorPrecalculado(Empresa empresa, String periodo, int resultado,Indicador indicador) {
		this.empresa = empresa;
		this.periodo=periodo;
		this.resultado=resultado;
		this.nombre = indicador.getNombre();
	}

}
