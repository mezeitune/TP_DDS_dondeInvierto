package comparadores;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Comparador { //Si senor, otra clase abstracta para la persistencia en Json
	@Id @GeneratedValue
	private Long id;
	
	public String nombre;
	public boolean comparar(int valor1, int valor2){return true;}
	public String getNombre(){
		return this.nombre;
	}
	
	
	
	
	
}














