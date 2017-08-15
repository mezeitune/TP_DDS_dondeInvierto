package parserFormulaInidicador;

public class Constante implements Operacion{
	private int valor;
	
	public Constante(int valor){
		this.valor = valor;
	}
	
	@Override
	public int calcular(){
		return this.valor;
	}

	@Override
	public void setOperador1(Operacion operador1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOperador2(Operacion operador2) {
		// TODO Auto-generated method stub
		
	}

	
}
