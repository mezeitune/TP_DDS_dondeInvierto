package parserFormulaInidicador;

public class Operacion {

	protected Operacion operador1;
	protected Operacion operador2;
	
	public void setOperador1(Operacion operador){
		operador1=operador;
	}
	public void setOperador2(Operacion operador){
		operador2=operador;
	}
	
	public Operacion getOperador1(){
		return this.operador1;
	}
	
	public Operacion getOperador2(){
		return this.operador2;
	}
	
	public int calcular(){
		return 0;
	}
}
