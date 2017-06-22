package metodologias;

import java.util.LinkedList;
import java.util.List;

import usuario.Indicador;

public class Condicion {

	private List<Indicador> indicadores = new LinkedList<>(); 
	private EstadoCondicion estado;
	
	
	
	
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	public EstadoCondicion getEstado() {
		return estado;
	}
	public void setEstado(EstadoCondicion estado) {
		this.estado = estado;
	}
	
	
}
