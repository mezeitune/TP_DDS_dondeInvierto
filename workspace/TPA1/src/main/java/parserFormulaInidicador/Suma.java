package parserFormulaInidicador;

public class Suma extends Operacion {

	@Override
	public int calcular(){
		return this.operador1.calcular() + this.operador2.calcular();
	}
}
