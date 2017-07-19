package Condiciones;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import usuario.Empresa;
import usuario.Indicador;
@Observable
public class Condicion {

	protected TipoCondicion tipo;
	public int peso;
	public Indicador indicador;
	
	
	public Condicion(TipoCondicion tipo,Indicador indicador,int peso){
		this.tipo=tipo;
		this.indicador = indicador;
		this.peso=peso;
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
