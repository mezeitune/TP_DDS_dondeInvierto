package parserIndicadores;


import model.Cuenta;
import model.Indicador;

public class Constante implements Operacion {
	private int valor;
	
	public Constante(int valor){
		this.valor = valor;
	}
	
	public Constante() {
		// TODO Auto-generated constructor stub
	}

	public int calcular() {
		return this.valor;
	}
	
	public Operacion setIndicador(Indicador indicador) {
		ParserFormulaIndicador parser = ParserFormulaIndicador.getInstance();
		indicador.construirOperadorRaiz(parser.getEmpresa(), parser.getPeriodo());
		this.valor = indicador.calcular();
		return this;
	}
	
	public Operacion setCuenta(Cuenta cuenta) {
		this.valor = cuenta.calcular();
		return this;
	}
	
	public Operacion setConstante(int constante) {
		this.valor = constante;
		return this;
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
