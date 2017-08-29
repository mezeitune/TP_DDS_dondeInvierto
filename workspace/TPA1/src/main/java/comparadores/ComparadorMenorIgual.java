package comparadores;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class ComparadorMenorIgual extends Comparador{
	public ComparadorMenorIgual(){
		this.nombre = "<=";
	}
	@Override
	public boolean comparar(int valor1, int valor2){
		return valor1 <= valor2;
	}
	public String getNombre(){
		return this.nombre;
	}

}
