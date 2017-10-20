package comparadores;

public class ComparadorMayor extends Comparador {
	
	public ComparadorMayor(){
		this.nombre = ">";
	}
	@Override
	public boolean comparar(int valor1, int valor2){
		return valor1 > valor2;
	}
}
