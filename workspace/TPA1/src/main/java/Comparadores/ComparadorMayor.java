package Comparadores;


public class ComparadorMayor extends Comparador {
	public String nombre = ">";
	@Override
	public boolean comparar(int valor1, int valor2){
		return valor1 > valor2;
	}
	
	public String getNombre(){
		return this.nombre;
	}
}
