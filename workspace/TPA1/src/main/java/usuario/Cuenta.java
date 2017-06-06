package usuario;

import org.uqbar.commons.utils.Observable;

import parserFormulaInidicador.Operacion;
@Observable
public class Cuenta extends Operacion {

	private String nombre;
	private String periodo;
	private int valor;
	
	public Cuenta(String nombre, String periodo, int valor){
		this.nombre=nombre;
		this.periodo=periodo;
		this.valor=valor;
	}
	
	public String getNombre (){
		return this.nombre;
	}
	
	public String getPeriodo (){
		return this.periodo;
	}
	
	public int getValor (){
		return this.valor;
	}
	
	
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}
	
	public void setPeriodo(String unPeriodo){
		this.periodo = unPeriodo;
	}
	
	public void setValor(int unValor){
		this.valor = unValor;
	}
	
	public int calcular(){
		return valor;
	}
}



