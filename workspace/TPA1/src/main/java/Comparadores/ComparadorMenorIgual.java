package Comparadores;


public class ComparadorMenorIgual extends Comparador{
	@Override
	public boolean comparar(int valor1, int valor2){
		return valor1 <= valor2;
	}

}
