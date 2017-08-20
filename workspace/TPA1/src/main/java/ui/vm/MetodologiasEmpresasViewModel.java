package ui.vm;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import excepciones.EmpresasIsEmptyException;
import excepciones.MetodologiaNotFoundException;
import excepciones.PeriodosIsEmptyException;
import repository.EmpresasAEvaluarRepository;
import repository.MetodologiasRepository;
import usuario.Empresa;
import usuario.Metodologia;
@Observable
public class MetodologiasEmpresasViewModel {
	
	private Metodologia metodologia;

	private List<Empresa> empresasInvertibles = new LinkedList<>();
	private List<Empresa> empresasNoInvertibles = new LinkedList<>();

	public MetodologiasEmpresasViewModel() {
		
	}
	
	public List<Metodologia> getMetodologias(){
		return MetodologiasRepository.getMetodologias();
	}
	
	
	public void setMetodologia(Metodologia metodologiaSeleccionada){
		this.metodologia = metodologiaSeleccionada;
	}
	public Metodologia getMetodologia(){
		return this.metodologia;
	}
	
	public void evaluar() throws PeriodosIsEmptyException, EmpresasIsEmptyException, MetodologiaNotFoundException{
		
		if(getMetodologia() == null) throw new MetodologiaNotFoundException();
		if(getPeriodos().isEmpty()) throw new PeriodosIsEmptyException();
		if(getEmpresas().isEmpty()) throw new EmpresasIsEmptyException();
		
		metodologia.setEmpresasAEvaluar(getEmpresas());
		
		
		List<List<Empresa>> resultado = metodologia.evaluar(getPeriodos());
		setEmpresasInvertibles(resultado.get(0));
		setEmpresasNoInvertibles(resultado.get(1));
	}
	
	
	public List<Empresa> getEmpresas(){
		return EmpresasAEvaluarRepository.getEmpresasAEvaluar();
	}
	
	public List<String> getPeriodos(){
		return EmpresasAEvaluarRepository.getPeriodosAEvaluar();
	}
	
	
	public void vaciarListaEmpresas(){
		EmpresasAEvaluarRepository.vaciarListaDeEmpresasAEvaluar();
		ObservableUtils.firePropertyChanged(this, "empresas");
	}
	
	public void autocompletarListaEmpresasAEvaluar(){
		EmpresasAEvaluarRepository.cargarTodasLasEmpresas();
		ObservableUtils.firePropertyChanged(this, "empresas");	
	}


	public List<Empresa> getEmpresasInvertibles() {
		return empresasInvertibles;
	}


	public void setEmpresasInvertibles(List<Empresa> empresasInvertibles) {
		this.empresasInvertibles = empresasInvertibles;
	}


	public List<Empresa> getEmpresasNoInvertibles() {
		return empresasNoInvertibles;
	}


	public void setEmpresasNoInvertibles(List<Empresa> empresasNoInvertibles) {
		this.empresasNoInvertibles = empresasNoInvertibles;
	}
	
	
}
