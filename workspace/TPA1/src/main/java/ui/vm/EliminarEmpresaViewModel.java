package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import parserIndicadores.ParserFormulaIndicador;
import repository.EmpresasAEvaluarRepository;
import usuario.Empresa;
@Observable
public class EliminarEmpresaViewModel {

	private Empresa empresa;
	public EliminarEmpresaViewModel() {
		
		ParserFormulaIndicador.setEmpresa(empresa);
	
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
