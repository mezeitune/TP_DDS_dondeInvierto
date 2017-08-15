package parserFormulaInidicador;

public class Division implements Operacion{

	public Operacion operador1;
	public Operacion operador2;
	
	@Override
	public int calcular(){
		return this.operador1.calcular() / this.operador2.calcular();
	}

	public void setOperador1(Operacion operador) {
		this.operador1=operador;
	}

	public void setOperador2(Operacion operador) {
		this.operador2=operador;
	}

	public Operacion getOperador1() {
		return this.operador1;
	}

	public Operacion getOperador2() {
		return this.operador2;
	}

	
}
