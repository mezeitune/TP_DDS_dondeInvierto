package ui.windows;

import parser.ParserFormulaToIndicador;
import usuario.Indicador;

public class Custom extends Indicador {

	String formula;
	
	public Custom(String nombre,String formula) {
		super(nombre);
		this.formula=formula;
	}

	
	

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public int calcular() {
		
		return ParserFormulaToIndicador.getCalculoIndicador(this.getFormula());
	
	}
	
}
