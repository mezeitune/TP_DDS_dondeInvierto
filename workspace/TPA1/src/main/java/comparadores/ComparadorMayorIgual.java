package comparadores;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class ComparadorMayorIgual extends Comparador {
	
	public ComparadorMayorIgual(){
		this.nombre = ">=";
	}
	
	@Override
	public boolean comparar(int valor1, int valor2){
		return valor1 >= valor2;
	}
	public String getNombre(){
		return this.nombre;
	}
}
