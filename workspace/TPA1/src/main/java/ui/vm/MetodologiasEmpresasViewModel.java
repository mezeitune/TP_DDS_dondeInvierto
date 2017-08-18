package ui.vm;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

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
			this.setMetodologia(metodologias.get(0));

			List<Empresa> conjuntoDeEmpresasAEvaluar = new LinkedList<Empresa>();
		Empresa empresa1 = new Empresa("Facebook");
		Empresa empresa2 = new Empresa("Apple");
		Empresa empresa3 = new Empresa("Oracle");
		Empresa empresa4 = new Empresa("Genius");
		Empresa empresa5 = new Empresa("IBM");
		conjuntoDeEmpresasAEvaluar.add(empresa1);
		conjuntoDeEmpresasAEvaluar.add(empresa2);
		conjuntoDeEmpresasAEvaluar.add(empresa3);
		conjuntoDeEmpresasAEvaluar.add(empresa4);
		conjuntoDeEmpresasAEvaluar.add(empresa5);
		metodologia.setEmpresasAEvaluar(conjuntoDeEmpresasAEvaluar);
		empresas= metodologia.getConjuntoDeEmpresasAEvaluar();
		System.out.println(empresas);
		
	}
	
	
	public void setMetodologias(){
		metodologias = MetodologiasRepository.getMetodologias();
	}

	public List<Metodologia> getMetodologias(){
		return this.metodologias;
	}
	public void setMetodologia(Metodologia metodologiaSeleccionada){
		
		this.metodologia = metodologiaSeleccionada;
		getMetodologia().setEmpresasAEvaluar(getEmpresas());
		
	}
	
	public void evaluar(){
		empresasInvertibles = metodologia.evaluar(periodos).get(0);
		empresasNoInvertibles = metodologia.evaluar(periodos).get(1);
		
		ObservableUtils.firePropertyChanged(this, "empresasInvertibles");
		ObservableUtils.firePropertyChanged(this, "empresasNoInvertibles");

	}
	
	public Metodologia getMetodologia(){
		return this.metodologia;
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
	
	
	public List<Empresa> getEmpresasInvertibles() {
		return empresasInvertibles;
	}
	
	
	public List<Empresa> getEmpresasNoInvertibles() {
		return empresasNoInvertibles;
		
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
	
	
}
