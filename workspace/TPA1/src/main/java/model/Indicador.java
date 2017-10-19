package model;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;
import excepciones.AccountNotFoundException;
import parserIndicadores.Operacion;
import parserIndicadores.ParserFormulaIndicador;

@Observable
@Entity
@Table(name="Indicadores")
public class Indicador implements Comparable<Indicador> {
	@Id @GeneratedValue
	private Long id;
	protected String nombre;
	protected String formula;
	

	@Transient
	protected Operacion raiz;
	
	
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
	
	public void setNombre(String nombreIndicador) {
		this.nombre = nombreIndicador;
	}
	
	public void setRaiz(Operacion raiz) {
		this.raiz = raiz;
	}
	public Operacion getRaiz() {
		return this.raiz;
	}
	

	@Override
	public int compareTo(Indicador unIndicador) {
        return this.getNombre().compareTo(unIndicador.getNombre());
    }
	
	
	public void construirOperadorRaiz(Empresa empresa,String periodo){
		ParserFormulaIndicador parser = ParserFormulaIndicador.getInstance();
		parser.setEmpresa(empresa);
		parser.setPeriodo(periodo);
		
		try {
			this.raiz = parser.construirArbolOperaciones(this.formula);
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Para testear solo indicadores con formulas de numeros
	public void construirOperadorRaiz() {
		ParserFormulaIndicador parser = ParserFormulaIndicador.getInstance();
		try {
			this.raiz = parser.construirArbolOperaciones(this.formula);
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int calcular() {
		return raiz.calcular();
	}
	
	public String toString (){
        String mensaje="El indicador"+id+" es "+nombre+" con la formula: "+formula;
        return mensaje;
    }


	
}
