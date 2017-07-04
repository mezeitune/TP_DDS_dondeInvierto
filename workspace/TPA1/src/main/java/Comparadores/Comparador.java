package Comparadores;

import org.uqbar.commons.utils.Observable;

@Observable
public interface Comparador {
	boolean comparar(int valor1,int valor2);
	
}
