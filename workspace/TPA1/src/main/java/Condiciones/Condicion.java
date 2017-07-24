package Condiciones;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import usuario.Empresa;
import usuario.Indicador;
@Observable
public class Condicion {

	protected TipoCondicion tipo;
	public int peso;
	public Indicador indicador;
	public String nombre;
	
	
	public Condicion(String nombre,TipoCondicion tipo,Indicador indicador,int peso){
		this.nombre = nombre;
		this.tipo=tipo;
		this.indicador = indicador;
		this.peso=peso;
	}
	
	
	public List<Empresa> evaluar(List<Empresa> listaAEvaluar,List<String> periodos){
		List<Empresa> resultado = new LinkedList<Empresa>(this.tipo.evaluar(listaAEvaluar,periodos,this));
		//this.imprimirResultado(resultado);
		return resultado;
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
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void imprimirResultado(List<Empresa> empresas){
		int i;
		System.out.println(this.getNombre());
		for(i=0;i<empresas.size();i++){
			System.out.println(empresas.get(i).getNombre());
		}
		System.out.println("****************");
	}
	
}
