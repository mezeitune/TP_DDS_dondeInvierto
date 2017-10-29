package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import excepciones.AccountNotFoundException;
import parserIndicadores.Operacion;
import parserIndicadores.ParserFormulaIndicador;

@Entity
@Table(name="Indicadores")
public class Indicador extends PersistentObject implements Comparable<Indicador>{
	
	protected String nombre;
	protected String formula;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	//@JoinColumn(name = "indicador_id")
	protected Usuario usuario;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

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
		
		if(!(periodo == null)){
			parser.setPeriodo(periodo);
		}
		
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
	


	
}
