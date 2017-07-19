package ui.vm;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.experimental.theories.Theories;
import org.uqbar.commons.utils.Observable;

import com.google.gson.Gson;

import Condiciones.Condicion;
import parser.ParserJsonString;
import repository.MetodologiasRepository;
import usuario.Metodologia;


@Observable
public class CargarMetodologiaViewModel {
	private List<Metodologia> metodologias;
	private Metodologia metodologia;
	private String nombreMetodologia ;
	private List<Condicion> condiciones;
	private Condicion condicion;
	
	
	public  List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	public void setMetodologias(){
		metodologias = MetodologiasRepository.getMetodologias();
	}
	
	
	public List<Metodologia> getMetodologias() {
		return this.metodologias;
	}
	
	public Condicion getCondicion() {
		return condicion;
	}
	public void setCondicion(Condicion condicion) {
		condicion = condicion;
	}
	
	public String getNombreMetodologia() {
		return metodologia.getNombre();
	}
	public void setNombreMetodologia(String nombre) {
		this.nombreMetodologia = nombre;
	}
	
	public void generarMetodologia() throws IOException{
		
		Metodologia nuevaMetodologia = new Metodologia();
		//nuevaMetodologia.setCondiciones(condiciones);
		nuevaMetodologia.setNombre(nombreMetodologia);
		
		//String jsonElement = new Gson().toJson(nuevaMetodologia); 
	

		//ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("metodologias",jsonElement );	
		
	}
	
}
