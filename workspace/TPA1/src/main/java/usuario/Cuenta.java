package usuario;

import org.uqbar.commons.utils.Observable;
@Observable
public class Cuenta {

	private String nombre;
	private int periodo;
	private int valor;
	
	public String getNombre (){
		return this.nombre;
	}
	
	public int getPeriodo (){
		return this.periodo;
	}
	
	public int getValor (){
		return this.valor;
	}
	
	
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}
	
	public void setPeriodo(int unPeriodo){
		this.periodo = unPeriodo;
	}
	
	public void setValor(int unValor){
		this.valor = unValor;
	}
}



