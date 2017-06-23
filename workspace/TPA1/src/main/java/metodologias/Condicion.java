package metodologias;

import java.util.LinkedList;
import java.util.List;

import usuario.Empresa;
import usuario.Indicador;

public class Condicion {

	private List<Indicador> indicadores = new LinkedList<>(); 
	private EstadoCondicion estado;
	private int peso;
	
	public Condicion(int peso, EstadoCondicion estado){
		this.peso=peso;
		this.estado=estado;
		
		
	}
	
	public EmpresaCumplimiento evaluar(Empresa empresa1,Empresa empresa2){
		int cumplimiento=estado.evaluar(empresa1,empresa2);
		
		return new EmpresaCumplimiento(empresa1, cumplimiento, this.getPeso());
	}
	
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
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
