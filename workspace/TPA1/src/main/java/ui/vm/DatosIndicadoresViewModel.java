package ui.vm;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.omg.CORBA.UserException;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;
import com.sun.jersey.api.client.Client;

import parser.CSVToEmpresas;
import parser.ParserFormulaToIndicador;
import parser.ParserJsonAEmpresaAdapter;
import repository.ArchivoEIndicadoresUsuarioRepository;
import com.google.gson.Gson;
import usuario.*;


@Observable
public class DatosIndicadoresViewModel{
	
	private Empresa empresa;
	private String nombre;
	private List<Empresa> empresas;
	private String periodo;	
	private List<String> periodos;
	
	
	private List<IndicadorCustom> indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
	private String nombreIndicador;
	private String formulaIndicador;
	private IndicadorCustom indicadorSeleccionado;
	
	
	public DatosIndicadoresViewModel() throws IOException {
		this.setEmpresas();
		
		this.empresa= empresas.get(0);
		this.periodos=this.empresa.getPeriodosSinRepetidos();
	}
	
	public void setIndicadorSeleccionado(IndicadorCustom indicadorSeleccionado) throws UserException{
		this.indicadorSeleccionado=indicadorSeleccionado;
	}
	
	public IndicadorCustom getIndicadorSeleccionado(){
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
	
	public void setPeriodo(String periodo){
		this.periodo= periodo;
		ParserFormulaToIndicador.setPeriodo(periodo); /*Le setea al parser el periodo. Lo necesita para reconocer cuentas*/
		ObservableUtils.firePropertyChanged(this, "cuentasFiltradas");
	}
	
	public void setEmpresa(Empresa empresaSeleccionada){
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
	public List<IndicadorCustom> getIndicadores(){
		Collections.sort(indicadores);
		return indicadores;
	}
	
}