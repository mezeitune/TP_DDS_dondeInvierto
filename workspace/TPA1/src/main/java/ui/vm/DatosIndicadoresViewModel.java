package ui.vm;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;
import com.sun.jersey.api.client.Client;

import parser.CSVToEmpresas;
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
	
	public void setPeriodos(List<String> periodos){
		this.periodos=periodos;
	}
	
	public List<String> getPeriodos(){
		return empresa.getPeriodosSinRepetidos();
	}
	
	public DatosIndicadoresViewModel() throws IOException {
		this.setEmpresas();
		
		this.empresa= empresas.get(0);
		this.periodos=this.empresa.getPeriodosSinRepetidos();
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
		ObservableUtils.firePropertyChanged(this, "cuentasFiltradas");
	}
	
	public void setEmpresa(Empresa empresaSeleccionada){
		this.empresa = empresaSeleccionada;
		this.setPeriodo(null);
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
		return indicadores;
		
	}
	
}