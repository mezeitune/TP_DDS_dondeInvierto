package usuario;

import org.omg.CORBA.UserException;
import org.uqbar.commons.utils.Observable;

import parserFormulaInidicador.Operacion;
import parserFormulaInidicador.ParserFormulaToIndicador;

@Observable
public class  Indicador extends Operacion implements Comparable<Indicador> {

	private String nombre;
	private String formula;
	private int resultado;
	
	
	public Indicador(String nombre,String formula){
		this.nombre=nombre;
		this.formula=formula;

	}
	
	public Indicador(){
		
	}
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getResultado(){
		return this.resultado;
	}
	public void setResultado() throws UserException{
		this.resultado = this.calcular();
	}


	public void setNombre(String nombreIndicador) {
		this.nombre = nombreIndicador;
	}
	

	@Override
	public int compareTo(Indicador unIndicador) {
        return this.getNombre().compareTo(unIndicador.getNombre());
    }


	
	public int calcular() {
		return ParserFormulaToIndicador.getCalculoIndicador(formula);
	}

	
	
}
