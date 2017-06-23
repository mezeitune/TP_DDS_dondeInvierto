package ui.vm;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import parser.parserArchivos.CSVToEmpresas;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
import usuario.Empresa;
@Observable
public class AgregarEmpresaViewModel {

	private Empresa empresa;
	private String nombre;
	private List<Empresa> empresas;
	public AgregarEmpresaViewModel() {
		try {
			this.setEmpresas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.empresa= empresas.get(0);
		ParserFormulaToIndicador.setEmpresa(empresa);
	
	}
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		this.empresas=parser.csvFileToEmpresas();
		
	}
	public void setEmpresa(Empresa unaEmpresa){
		EmpresasAEvaluarRepository.agregarEmpresaAEvaluar(unaEmpresa);
		this.empresa=unaEmpresa;
	}
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}
	public String getNombre(){
		return empresa.getNombre();
	}
	
}
