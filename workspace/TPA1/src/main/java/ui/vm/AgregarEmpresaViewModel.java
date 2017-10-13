package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import repositorios.EmpresasAEvaluarRepository;
import repositorios.EmpresasRepository;
import usuario.Empresa;
import utilities.JPAUtility;
@Observable
public class AgregarEmpresaViewModel {

	private static EmpresasRepository repositorio_empresas=new EmpresasRepository();
	private Empresa empresa = new Empresa();
	
	public List<Empresa> getEmpresas(){
		return repositorio_empresas.getEmpresas();
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	public void setEmpresa(Empresa empresaSeleccionada) {
		this.empresa = empresaSeleccionada;
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public boolean esUnaEmpresaYaCargada(){
		return EmpresasAEvaluarRepository.esEmpresaRepetida(this.empresa);
		
	}
	public void agregarNuevaEmpresaAEvaluar() {
		EmpresasAEvaluarRepository.agregarEmpresaAEvaluar(this.empresa);
	}
}
