package parserFormulaInidicador;

public class Constante extends Operacion{
	private int valor;
	
	public Constante(int valor){
		this.valor = valor;
	}
	
	@Override
	public int calcular(){
		return this.valor;
	}
}
