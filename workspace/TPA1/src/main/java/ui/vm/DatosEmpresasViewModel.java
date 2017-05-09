package ui.vm;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;
import com.sun.jersey.api.client.Client;

import parser.CSVToEmpresas;
import parser.ParserJsonAEmpresaAdapter;
import repository.ArchivoRepository;
import com.google.gson.Gson;
import usuario.*;


@Observable
public class DatosEmpresasViewModel{
	
	private Empresa empresa;
	private String nombre;
	private List<Empresa> empresas;
	private String periodo;	
	private List<String> periodos;
	
	
	public void setPeriodos(List<String> periodos){
		this.periodos=periodos;
	}
	
	public List<String> getPeriodos(){
		return empresa.getPeriodosSinRepetidos();
	}

	
	public DatosEmpresasViewModel() throws IOException {
		this.setEmpresas();
		
		this.empresa= empresas.get(0);
		this.periodos=this.empresa.getPeriodosSinRepetidos();
	}
	

	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoRepository.getArchivo());
		this.empresas=parser.csvFileToEmpresas();
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	public String getPeriodo(){
		return periodo;
	}
	
	public void setPeriodo(String periodo){
		
		this.periodo= periodo;//this.cuenta.getPeriodo();
		ObservableUtils.firePropertyChanged(this, "cuentasFiltradas");
	}
	


	public void setEmpresa(Empresa empresaSeleccionada){
		this.empresa = empresaSeleccionada;
		System.out.print(this.empresa.getNombre());
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
		return this.empresa.getCuentasPorPeriodo(this.getPeriodo());
	}
	
	
}