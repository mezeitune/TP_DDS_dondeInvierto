package Comparadores;

import org.uqbar.commons.utils.Observable;

@Observable
public class ComparadorMayor implements Comparador {

	public boolean comparar(int valor1, int valor2){
		return valor1 > valor2;
	}
}
