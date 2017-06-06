package parserFormulaInidicador;

public class Division extends Operacion{

	@Override
	public int calcular(){
		return this.operador1.calcular() / this.operador2.calcular();
	}
}
