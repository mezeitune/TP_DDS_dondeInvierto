package Condiciones;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import usuario.Empresa;
import usuario.Indicador;
@Observable
public class Condicion {

	private TipoCondicion tipo;
	private int peso;
	private Indicador indicador;
	
	
	public Condicion(TipoCondicion tipo,Indicador indicador){
		this.tipo=tipo;
		this.indicador = indicador;
	}
	
	
	public List<Empresa> evaluar(List<Empresa> listaAEvaluar,List<String> periodos){
		return tipo.evaluar(listaAEvaluar,periodos,this);
	}
	
	public Indicador getIndicador(){
		return this.indicador;
	}
	public void setIndicador(Indicador indicador){
		this.indicador=indicador;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}


	public TipoCondicion getEstado() {
		return tipo;
	}
	
	
	public void setEstado(TipoCondicion estado) {
		this.tipo = estado;
	}
	
	
}
