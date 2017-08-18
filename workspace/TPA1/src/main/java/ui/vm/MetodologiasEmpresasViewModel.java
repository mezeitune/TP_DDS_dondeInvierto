package ui.vm;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import excepciones.EmpresasIsEmptyException;
import excepciones.MetodologiaNotFoundException;
import excepciones.PeriodosIsEmptyException;
import parserArchivos.CSVToEmpresas;
import parserArchivos.ParserJsonAObjetosJava;
import repository.EmpresasAEvaluarRepository;
import repository.EmpresasRepository;
import repository.MetodologiasRepository;
import usuario.Empresa;
import usuario.Metodologia;
@Observable
public class MetodologiasEmpresasViewModel {
	
	private List<Metodologia> metodologias;
	private Metodologia metodologia;

	private List<Empresa> empresasInvertibles = new LinkedList<>();
	private List<Empresa> empresasNoInvertibles = new LinkedList<>();

	private List<Empresa> empresas = new LinkedList<Empresa>();
	private List<String> periodos = new LinkedList<String>();
	
	public MetodologiasEmpresasViewModel() {
		ParserJsonAObjetosJava parserEmpIndicador = new ParserJsonAObjetosJava("metodologias.json");
		
		MetodologiasRepository.deleteMetodologiasDefinidasPorElUsuario();
		MetodologiasRepository.setMetodologiasDefinidasPorElUsuario(parserEmpIndicador.getMetodologiasDelArchivo());
		MetodologiasRepository.cargarMetodologiasPredefinidos();
			
		this.setMetodologias();
	}
	
	public void setMetodologias(){
		metodologias = MetodologiasRepository.getMetodologias();
	}

	public List<Metodologia> getMetodologias(){
		return this.metodologias;
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
	
	
	
	public void setEmpresas(List<Empresa> empresas){
		this.empresas = empresas;
	}
	public List<Empresa> getEmpresas(){
		return EmpresasAEvaluarRepository.getEmpresasAEvaluar();
	}
	
	public void setPeriodos(){
		this.periodos = EmpresasAEvaluarRepository.getPeriodosAEvaluar();
		
	}
	public List<String> getPeriodos(){
		return EmpresasAEvaluarRepository.getPeriodosAEvaluar();
	}
	
	
	public void vaciarListaEmpresas(){
		EmpresasAEvaluarRepository.vaciarListaDeEmpresasAEvaluar();
		ObservableUtils.firePropertyChanged(this, "empresas");
	}
	
	public void autocompletarListaEmpresasAEvaluar(){
		CSVToEmpresas parser = new CSVToEmpresas(EmpresasRepository.getArchivo());
		EmpresasAEvaluarRepository.setEmpresasAEvaluar(parser.csvFileToEmpresas());
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
