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
public class EliminarEmpresaViewModel {

	private Empresa empresa;
	private String nombre;
	private List<Empresa> empresas;
	public EliminarEmpresaViewModel() {
		
		ParserFormulaToIndicador.setEmpresa(empresa);
	
	}
	
	public void setEmpresa(Empresa unaEmpresa){
		EmpresasAEvaluarRepository.eliminarEmpresaAEvaluar(unaEmpresa);
		this.empresa=unaEmpresa;
	}
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return EmpresasAEvaluarRepository.getEmpresasAEvaluar();
	}
	public String getNombre(){
		return empresa.getNombre();
	}
	
}
