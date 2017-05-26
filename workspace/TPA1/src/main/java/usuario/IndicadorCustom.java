package usuario;

import org.omg.CORBA.UserException;
import org.uqbar.commons.utils.Observable;

import parser.ParserFormulaToIndicador;
@Observable
public class IndicadorCustom extends Indicador implements Comparable<IndicadorCustom>  {

	private String formula;
	private int resultado;
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

	public int calcular() {
		
		try {
			return ParserFormulaToIndicador.getCalculoIndicador(this.getFormula());
		} catch (UserException e) {
			e.printStackTrace();
		}
		return 0;
	
	}
	
	public int getResultado(){
		return this.resultado;
	}
	public void setResultado(){
		this.resultado = this.calcular();
	}
	
	
	
	public int compareTo(IndicadorCustom unIndicador) {
        return this.getNombre().compareTo(unIndicador.getNombre());
    }
	
	
}
