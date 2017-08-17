package ui.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.experimental.theories.Theories;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import com.google.gson.Gson;

import Comparadores.ComparadorMayor;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import metodologias.Predefinidas.WarrenBuffet;
import parser.ParserJsonString;
import parserArchivos.ParserJsonAObjetosJava;
import parserIndicadores.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.CriteriosSeleccionadosRepository;
import repository.MetodologiasRepository;
import usuario.Indicador;
import usuario.Metodologia;


@Observable
public class CargarMetodologiaViewModel {
	private List<Metodologia> metodologias;
	private Metodologia metodologia;
	private static String nombreMetodologia ;
	private static List<Condicion> condiciones = new ArrayList<>();
	private Condicion condicion ;
	private List<Condicion> criteriosSeleccionados = new LinkedList<Condicion>();

	public CargarMetodologiaViewModel(){
		this.setMetodologias();
		this.setCondiciones();
		CargarMetodologiaViewModel.nombreMetodologia=null;
		System.out.println(condiciones);
	
	}
	
	public void setCriteriosSeleccionados (){
		criteriosSeleccionados = CriteriosSeleccionadosRepository.criteriosSeleccionados;
	}

	public List<Condicion> getCriteriosSeleccionados(){
		return CriteriosSeleccionadosRepository.criteriosSeleccionados;
		
	}
	
	private void setCondiciones() {
		ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("condiciones.json");
		
		MetodologiasRepository.deleteCondicionesDefinidasPorElUsuario();
		MetodologiasRepository.setCondicionesDefinidasPorElUsuario(parser.getCondicionesDelArchivo());
		
		CargarMetodologiaViewModel.condiciones = MetodologiasRepository.getCondiciones();
		ObservableUtils.firePropertyChanged(this, "condiciones");
		
	}
	public  List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	public void setMetodologias(){
		ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("metodologias.json");
		MetodologiasRepository.deleteMetodologiasDefinidasPorElUsuario();
		MetodologiasRepository.setMetodologiasDefinidasPorElUsuario(parser.getMetodologiasDelArchivo());
		
		metodologias = MetodologiasRepository.getMetodologias();
		ObservableUtils.firePropertyChanged(this, "metodologias");
	}
	
	
	public List<Metodologia> getMetodologias() {
		return this.metodologias;
	
	}
	
	public Condicion getCondicion() {
		return condicion;
	}
	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
		Condicion condicionMet = new Condicion(condicion.nombre, condicion.getEstado(), condicion.getIndicador(), condicion.getPeso());
		condiciones.add(condicionMet);
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombre) {
		CargarMetodologiaViewModel.nombreMetodologia = nombre;
	}
	
	public static void generarMetodologia() throws IOException{
		
		Metodologia nuevaMetodologia = new Metodologia();
		nuevaMetodologia.setNombre(nombreMetodologia);
		nuevaMetodologia.setCondiciones(condiciones);
	
		String jsonElement = new Gson().toJson(nuevaMetodologia); 
	
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("metodologias",jsonElement );	
		
	}
	
	
	
}
