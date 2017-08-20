package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import repositorios.EmpresasAEvaluarRepository;
import repositorios.EmpresasRepository;
import usuario.Empresa;
@Observable
public class AgregarEmpresaViewModel {

	private Empresa empresa = new Empresa();

	public List<Empresa> getEmpresas(){
		return EmpresasRepository.getEmpresas();
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
