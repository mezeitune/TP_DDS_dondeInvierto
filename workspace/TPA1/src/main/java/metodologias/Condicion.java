package metodologias;

import java.util.LinkedList;
import java.util.List;

import usuario.Empresa;
import usuario.Indicador;

public class Condicion {

	private List<Indicador> indicadores = new LinkedList<>(); 
	private EstadoCondicion estado;
	
	public void evaluar(Empresa empresa1,Empresa empresa2){
		estado.evaluar(empresa1,empresa2);
	}
	
	
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
