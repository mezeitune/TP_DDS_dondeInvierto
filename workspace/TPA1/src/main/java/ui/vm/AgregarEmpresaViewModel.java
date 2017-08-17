package ui.vm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import parserArchivos.CSVToEmpresas;
import parserIndicadores.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
import usuario.Empresa;
@Observable
public class AgregarEmpresaViewModel {

	private Empresa empresa = new Empresa();
	private String nombre;
	
	private String periodo;
	private List<String> periodos = new LinkedList<>();
	
	private List<Empresa> empresas = new LinkedList<>();
	private static int codigoError;
	public static int getCodigoError(){
		return codigoError;
	}
	public AgregarEmpresaViewModel() {
		try {
			this.setEmpresas();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setPeriodo(String periodoSeleccionada) throws IOException{
		EmpresasAEvaluarRepository.agregarPeriodoAEvaluar(periodoSeleccionada);
	}
	
	public String getPeriodo(){
		return this.periodo;
	}
	
	public void setPeriodos(List<String> periodos){
		this.periodos = periodos;
	}
	
	public List<String> getPeriodos(){
		return empresa.getPeriodosSinRepetidos();
	}
	
	
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		this.empresas = parser.csvFileToEmpresas();
		
		
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	public void setEmpresa(Empresa empresaSeleccionada) throws IOException{
		if(ParserFormulaToIndicador.validarEmpresaRepetidaAntesDePrecargar(empresaSeleccionada)==true){
	
		AgregarEmpresaViewModel.codigoError=1;
		}else{
			
			EmpresasAEvaluarRepository.agregarEmpresaAEvaluar(empresaSeleccionada);
			AgregarEmpresaViewModel.codigoError=0;
		}
		this.empresa = empresaSeleccionada;
		ObservableUtils.firePropertyChanged(this, "periodos");
		
		
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}
}
