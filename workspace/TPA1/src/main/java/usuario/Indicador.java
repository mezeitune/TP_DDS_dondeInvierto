package usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import parser.ParserFormulaToIndicador;

@Observable
public class Indicador {

	private String nombre;
	private String formula;
	
	public Indicador(String nombre, String formula){
		this.nombre=nombre;
		this.formula=formula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
