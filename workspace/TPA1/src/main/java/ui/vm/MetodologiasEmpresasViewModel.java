package ui.vm;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import parser.parserArchivos.CSVToEmpresas;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
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
	private List<Empresa> empresasRankeadas;
	private List<Empresa> empresasQueNoConvieneInvertir;
	private List<Empresa> empresasAEvaluar;
	private String periodo;	
	
	public void setMetodologias (){
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("bone");
		unaMetodologia.setCondiciones(null);
		this.metodologias.add(unaMetodologia);
	}
	public List<Metodologia> getMetodologias(){
		return this.metodologias;
	}
	public void setMetodologia(Metodologia metodologiaSeleccionada){
		if (metodologia==null){
			this.metodologia=metodologias.get(0);
			
		}
		
		this.metodologia= metodologiaSeleccionada;
		//this.setPeriodo(null);
	
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
	
	public void setEmpresasRankeadas (){
		
	}
	public List<Empresa> getEmpresasRankeadas(){
		return this.empresasRankeadas;
	}
	
	public void setEmpresasQueNoConvieneInvertir (){
	
	}
	public List<Empresa> getEmpresasQueNoConvieneInvertir(){
		return this.empresasQueNoConvieneInvertir;
	}
	

	public MetodologiasEmpresasViewModel() {
		try {
			this.setEmpresas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.empresa= empresas.get(0);
		ParserFormulaToIndicador.setEmpresa(empresa);
		
	}
	
	public String getPeriodo(){
		return periodo;
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
	
}
