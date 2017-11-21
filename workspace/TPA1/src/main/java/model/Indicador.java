package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import Server.Controller.ControllerIndicadores;
import excepciones.AccountNotFoundException;
import parserIndicadores.Operacion;
import parserIndicadores.ParserFormulaIndicador;

@Entity
@Table(name="Indicadores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Indicador extends PersistentObject implements Comparable<Indicador>{
	
	protected String nombre;
	protected String formula;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	protected Usuario usuario;
	@Transient
	protected Operacion raiz;


	public Indicador(){
		
	}
	
	public Indicador(String nombre,String formula){
		this.nombre=nombre;
		this.formula=formula;
	}
	
	public Indicador(String nombre,String formula,Usuario usuario){
		this.nombre=nombre;
		this.formula=formula;
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	
	public IndicadorPrecalculado precalcular(Empresa empresa,String periodo) throws AccountNotFoundException {
		int resultado;
		ParserFormulaIndicador parser = ParserFormulaIndicador.getInstance();
		try {
		resultado = parser.calcularIndicador(this.formula,empresa,periodo);
		}catch(AccountNotFoundException exception) {
			return new IndicadorPrecalculado(empresa,periodo,0,this,false);
		}
		return new IndicadorPrecalculado(empresa,periodo,resultado,this,true);
	}

	public void construirOperadorRaiz(Empresa empresa,String periodo){
		ParserFormulaIndicador parser = ParserFormulaIndicador.getInstance();
		parser.setEmpresa(empresa);
		
		if(!(periodo == null)){
			parser.setPeriodo(periodo);
		}
		
		try {
			this.raiz = parser.construirArbolOperaciones(this.formula);
		} catch (AccountNotFoundException e) {
			ControllerIndicadores.setErrorMessage("La empresa " +e.getEmpresa()+
												  " no contiene la cuenta "+e.getNombreCuenta()+
												  " en el periodo "+e.getPeriodo()+
												  " para el indicador " + this.nombre);
		}
	}
	
	public int calcular(){

		try{
			return 	raiz.calcular();
		}catch (NullPointerException e) {
			ControllerIndicadores.setErrorMessage("Cuenta inexistente en el periodo indicado");
		}
		return 0;
		
	}
	
	//Para testear solo indicadores con formulas de numeros////////////////////////////////////////
	public void construirOperadorRaiz() throws NumberFormatException, AccountNotFoundException{
		ParserFormulaIndicador parser = ParserFormulaIndicador.getInstance();
		
		this.raiz = parser.construirArbolOperaciones(this.formula);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int compareTo(Indicador unIndicador) {
        return this.getNombre().compareTo(unIndicador.getNombre());
    }

	
}
