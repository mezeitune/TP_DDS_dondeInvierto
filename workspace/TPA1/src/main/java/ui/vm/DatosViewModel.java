package ui.vm;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.omg.CORBA.UserException;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;
import com.sun.jersey.api.client.Client;

import parser.parserArchivos.CSVToEmpresas;
import parser.parserArchivos.ParserJsonAObjetosJava;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import com.google.gson.Gson;
import usuario.*;


@Observable
public class DatosViewModel{
	
	private Empresa empresa;
	private String nombre;
	private List<Empresa> empresas;
	private String periodo;	
	private List<String> periodos;
	private int calculo;
	
	private List<Indicador> indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
	private String nombreIndicador;
	private String formulaIndicador;
	
	private Indicador indicadorAEvaluar = new IndicadorCustom("Actual", "0");
	//private String formulaAEvaluar;
	private Indicador indicadorSeleccionado;
	
	
	public DatosViewModel() {
		try {
			this.setEmpresas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.empresa= empresas.get(0);
		ParserFormulaToIndicador.setEmpresa(empresa);
		this.periodos=this.empresa.getPeriodosSinRepetidos();
		this.periodo=empresas.get(0).getPeriodosSinRepetidos().get(0);
		ParserFormulaToIndicador.setPeriodo(periodo);
		this.indicadorSeleccionado=indicadores.get(0);
		System.out.println(indicadores);
	}
	
	
	
	public void setIndicadorAEvaluar(String formula) throws UserException{
		this.indicadorAEvaluar.setFormula(formula);
	
		this.setCalculo();
		
	}
	
	public String getIndicadorAEvaluar(){
		return this.indicadorAEvaluar.getFormula();
	}
	
	
	
	public int getCalculo(){
		return calculo;
	}
	public void setCalculo() throws UserException{
		this.calculo = this.indicadorAEvaluar.calcular();
					
	}
	
	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) throws UserException{
		this.indicadorSeleccionado=indicadorSeleccionado;
	}
	
	public Indicador getIndicadorSeleccionado(){
		return this.indicadorSeleccionado;
	}
	
	
	public void setPeriodos(List<String> periodos){
		this.periodos=periodos;
	}
	
	public List<String> getPeriodos(){
		return empresa.getPeriodosSinRepetidos();
	}
	
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		this.empresas=parser.csvFileToEmpresas();
		
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	public String getPeriodo(){
		return periodo;
	}
	
	public void setPeriodo(String periodoSeleccionado){
		if(periodoSeleccionado==null){
			
		}
		this.periodo= periodoSeleccionado;
		ParserFormulaToIndicador.setPeriodo(periodo); /*Le setea al parser el periodo. Lo necesita para reconocer cuentas*/
		ObservableUtils.firePropertyChanged(this, "cuentasFiltradas");
	}
	
	public void setEmpresa(Empresa empresaSeleccionada){
		if (empresa==null){
			this.empresa=empresas.get(0);
			ParserFormulaToIndicador.setEmpresa(empresa);
		}
		
		this.empresa = empresaSeleccionada;
		this.setPeriodo(null);
		ParserFormulaToIndicador.setEmpresa(empresaSeleccionada); /*Le setea al parser la empresa seleccionada. Lo necesita para reconocer cuentas*/
		ObservableUtils.firePropertyChanged(this, "periodos");
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}
	
	public List<Cuenta> getCuentasFiltradas(){
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
	
}