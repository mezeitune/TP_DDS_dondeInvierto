package usuario;

import org.omg.CORBA.UserException;
import org.uqbar.commons.utils.Observable;

import parser.ParserFormulaToIndicador;
@Observable
public class IndicadorCustom extends Indicador {

	private String formula;
	
	public IndicadorCustom(String nombre,String formula) {
		super(nombre);
		this.formula=formula;
	}

	
	

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public int calcular() throws UserException {
		
		return ParserFormulaToIndicador.getCalculoIndicador(this.getFormula());
	
	}
	
}
