package ui.vm;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import repository.EmpresasAEvaluarRepository;
import repository.EmpresasRepository;
import usuario.Empresa;
@Observable
public class AgregarEmpresaViewModel {

	private Empresa empresa = new Empresa();
	private List<Empresa> empresas = new LinkedList<>();

	public AgregarEmpresaViewModel() {
			this.setEmpresas();
	}

	public void setEmpresas()  {
		this.empresas=EmpresasRepository.getEmpresas();
	}
	public List<Empresa> getEmpresas(){
		return this.empresas;
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
