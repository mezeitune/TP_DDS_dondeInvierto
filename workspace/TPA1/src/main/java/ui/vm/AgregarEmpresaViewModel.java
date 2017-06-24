package ui.vm;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import parser.parserArchivos.CSVToEmpresas;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
import usuario.Cuenta;
import usuario.Empresa;
@Observable
public class AgregarEmpresaViewModel {

	private Empresa empresa;
	private String nombre;
	private List<Empresa> empresas;
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
		
		ParserFormulaToIndicador.setEmpresa(empresa);
	
	}

	
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		this.empresas=parser.csvFileToEmpresas();
		
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
		
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}
}
