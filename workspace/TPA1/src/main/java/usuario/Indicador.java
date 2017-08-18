package usuario;

import org.uqbar.commons.utils.Observable;
import excepciones.AccountNotFoundException;
import parserIndicadores.Operacion;
import parserIndicadores.ParserFormulaToIndicador;

@Observable
public class Indicador implements Operacion,Comparable<Indicador> {

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
	public void setResultado(){
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
		int valor = 0;
		//ParserFormulaToIndicador.setEmpresa(empresa);
		//ParserFormulaToIndicador.setPeriodo(periodo);
		try {
			valor = ParserFormulaToIndicador.construirArbolOperaciones(this.formula).calcular();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return valor;
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
