package ui.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import parserArchivos.CSVToEmpresas;
import parserArchivos.ParserJsonAObjetosJava;
import parserIndicadores.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.CriteriosSeleccionadosRepository;
import repository.EmpresasAEvaluarRepository;
import repository.MetodologiasRepository;
import usuario.Empresa;
@Observable
public class AgregarCondicionSeleccionadaViewModel {

	private static int codigoError;
	private  List<Condicion> criteriosSeleccionados = new LinkedList<Condicion>();
	private static Condicion condicion;
	
	public AgregarCondicionSeleccionadaViewModel(){
		setCriterios();
		try {
			this.setEmpresas();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
	
		
		
	}
	public List<Condicion> getCriteriosSeleccionados() {
		return this.criteriosSeleccionados;
	}
	
	
	public void setCriterios() {
		ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("condiciones.json");
		MetodologiasRepository.deleteCondicionesDefinidasPorElUsuario();
		MetodologiasRepository.setCondicionesDefinidasPorElUsuario(parser.getCondicionesDelArchivo());
		
		this.criteriosSeleccionados = MetodologiasRepository.getCondiciones();
		
	}

	public Condicion getCondicion() {
		return this.condicion;
	}
	public void setCondicion(Condicion criterioSeleccionado) {
	this.condicion=criterioSeleccionado;
	//	System.out.println("no lo agregue");
	}
	public static int getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}
	
	public static void agregarCondicionALaLista() {
		if(CriteriosSeleccionadosRepository.criteriosSeleccionados.contains(condicion)==true){
			
			AgregarCondicionSeleccionadaViewModel.codigoError=1;
			System.out.println("criterio ya seleccionado, seleccione otro por favor");
			}else{
				
				//System.out.println(condicion);
				CriteriosSeleccionadosRepository.agregarCriterioSeleccionado(condicion);
				AgregarCondicionSeleccionadaViewModel.codigoError=0;
				
	}
	
	}
	
}
