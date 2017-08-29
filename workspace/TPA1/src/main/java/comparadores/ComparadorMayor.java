package comparadores;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class ComparadorMayor extends Comparador {
	
	public ComparadorMayor(){
		this.nombre = ">";
	}
	@Override
	public boolean comparar(int valor1, int valor2){
		return valor1 > valor2;
	}
}
