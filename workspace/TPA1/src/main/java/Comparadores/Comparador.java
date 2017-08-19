package Comparadores;

import org.uqbar.commons.utils.Observable;

@Observable
public class Comparador { //Si senor, otra clase abstracta para la persistencia en Json
	public String nombre;
	public boolean comparar(int valor1, int valor2){return true;}
	public String getNombre(){
		return this.nombre;
	}
}
