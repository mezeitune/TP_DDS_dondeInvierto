package ui.vm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import parser.parserArchivos.CSVToEmpresas;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
import repository.MetodologiasRepository;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;
@Observable
public class MetodologiasEmpresasViewModel {
	private List<Empresa> empresas;
	private String nombre;
	private Empresa empresa;
	private Boolean seleccionoTodasLasEmpresas;
	private List<Metodologia> metodologias;
	private Metodologia metodologia;
	private List<List<Empresa>> empresasRankeadas;
	
	private List<Empresa> empresasValidas = new LinkedList<>();
	private List<Empresa> empresasNoValidas = new LinkedList<>();
	
	private List<Empresa> empresasQueNoConvieneInvertir;
	private List<Empresa> empresasAEvaluar;
	private List<String> periodos;	
	


	public MetodologiasEmpresasViewModel() {
		try {
			
			this.setEmpresas();
			this.setMetodologias();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.empresa= empresas.get(0);
		ParserFormulaToIndicador.setEmpresa(empresa);
		
	}
	
	
	public void setMetodologias(){
		
		metodologias = MetodologiasRepository.getMetodologias();
	}

	public List<Metodologia> getMetodologias(){
		return this.metodologias;
	}
	public void setMetodologia(Metodologia metodologiaSeleccionada){
		if (metodologia==null){
			this.metodologia=metodologias.get(0);
			
		}
		
		this.metodologia = metodologiaSeleccionada;
		this.evaluar();
		/*this.empresasRankeadas = this.metodologia.evaluar(this.getPeriodos());
		ObservableUtils.firePropertyChanged(this, "empresasRankeadas");*/
		//this.setPeriodo(null);
	
	}
	
	public void evaluar(){
		this.empresasRankeadas = this.metodologia.evaluar(this.getPeriodos());
		this.empresasValidas = empresasRankeadas.get(0);
		this.empresasNoValidas = empresasRankeadas.get(1);
		
		ObservableUtils.firePropertyChanged(this, "empresasValidas");
		ObservableUtils.firePropertyChanged(this, "empresasNoValidas");
	}
	
	public Metodologia getMetodologia(){
		return this.metodologia;
	}
	
	
	public void setEmpresasAEvaluar(){
		this.empresasAEvaluar=EmpresasAEvaluarRepository.getEmpresasAEvaluar();
		
	}
	public List<Empresa> getEmpresasAEvaluar(){
		return EmpresasAEvaluarRepository.getEmpresasAEvaluar();
	}
	
	public void setPeriodos(){
		this.periodos = EmpresasAEvaluarRepository.getPeriodosAEvaluar();
		
	}
	public List<String> getPeriodos(){
		return EmpresasAEvaluarRepository.getPeriodosAEvaluar();
	}
	
	public void setEmpresasRankeadas (){
		
	}
	public List<List<Empresa>> getEmpresasRankeadas(){
		return this.empresasRankeadas;
	}
	
	public void setEmpresasQueNoConvieneInvertir (){
	
	}
	public List<Empresa> getEmpresasQueNoConvieneInvertir(){
		return this.empresasQueNoConvieneInvertir;
	}
	
	public void setSeleccionoTodasLasEmpresas(boolean seleccionoTodas){
		this.seleccionoTodasLasEmpresas=seleccionoTodas;
	}
	public Boolean getSeleccionoTodasLasEmpresas(){
		return this.seleccionoTodasLasEmpresas;
	}
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		this.empresas=parser.csvFileToEmpresas();
		
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	
	public void setEmpresa(Empresa empresaSeleccionada){
		if (empresa==null){
			this.empresa=empresas.get(0);
			ParserFormulaToIndicador.setEmpresa(empresa);
		}
		
		this.empresa = empresaSeleccionada;
		//this.setPeriodo(null);
		ParserFormulaToIndicador.setEmpresa(empresaSeleccionada); /*Le setea al parser la empresa seleccionada. Lo necesita para reconocer cuentas*/
		ObservableUtils.firePropertyChanged(this, "periodos");
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}
	
	public List<Empresa> getEmpresasValidas() {
		return empresasValidas;
	}
	
	public List<Empresa> getEmpresasNoValidas() {
		return empresasNoValidas;
	}
}
