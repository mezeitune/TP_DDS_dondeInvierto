package ui.vm;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import repositorios.EmpresasRepository;
import repositorios.IndicadoresRepository;
import usuario.*;


@Observable
public class DatosCuentasViewModel{
	
	private Empresa empresa;
	private String periodo;	
	private int calculo;
	
	private List<Indicador> indicadores = IndicadoresRepository.getIndicadores();
	private String nombreIndicador;
	private String formulaIndicador;
	
	private Indicador indicadorSeleccionado;
	
	
	public int getCalculo(){
		return this.calculo;
	}
	
	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado=indicadorSeleccionado;
	}
	
	public Indicador getIndicadorSeleccionado(){
		return this.indicadorSeleccionado;
	}
	
	public List<String> getPeriodos(){
		if(this.empresa == null) return new LinkedList<String>();
		return this.empresa.getPeriodosSinRepetidos();
	}
	
	public String getPeriodo(){
		return periodo;
	}
	
	public void setPeriodo(String periodoSeleccionado){
		
		this.periodo= periodoSeleccionado;
		ObservableUtils.firePropertyChanged(this, "cuentasFiltradas");
	}
	
	public void setEmpresa(Empresa empresaSeleccionada){
		this.empresa = empresaSeleccionada;
		this.periodo = null;
		ObservableUtils.firePropertyChanged(this, "periodos");
		ObservableUtils.firePropertyChanged(this, "cuentasFiltradas");
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return EmpresasRepository.getEmpresas();
	}
	
	public List<Cuenta> getCuentasFiltradas(){
		if(empresa == null) return new LinkedList<Cuenta>();
		if (periodo == null) return empresa.getCuentas();
		return this.empresa.getCuentasPorPeriodo(this.getPeriodo());
	}
	
	
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
	public String getFormulaIndicador() {
		return formulaIndicador;
	}
	public void setFormulaIndicador(String formulaIndicador) {

		this.formulaIndicador = formulaIndicador;
	}
	public List<Indicador> getIndicadores(){
		Collections.sort(indicadores);
		return indicadores;
	}
	
	public void calcular(){
		this.indicadorSeleccionado.construirOperadorRaiz(this.empresa, this.periodo);
		this.calculo = this.indicadorSeleccionado.calcular();
		
		ObservableUtils.firePropertyChanged(this, "calculo");
	}
	
}