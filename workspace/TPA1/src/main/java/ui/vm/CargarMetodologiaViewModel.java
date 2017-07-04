package ui.vm;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.google.gson.Gson;

import metodologias.Condicion;
import parser.ParserJsonString;
import repository.MetodologiasUsuarioRepository;
import usuario.Metodologia;


@Observable
public class CargarMetodologiaViewModel {
	private static List<Metodologia> metodologias;
	private static String nombreMetodologia ;
	private static List<Condicion> condiciones;
	private static Condicion condicion;
	
	
	public static List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	
	
	
	public static List<Metodologia> getMetodologias() {
		return CargarMetodologiaViewModel.metodologias;
	}
	
	public static Condicion getCondicion() {
		return condicion;
	}
	public static void setCondicion(Condicion condicion) {
		CargarMetodologiaViewModel.condicion = condicion;
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombre) {
		this.nombreMetodologia = nombre;
	}
	
	public static void generarMetodologia() throws IOException{
		
		Metodologia nuevaMetodologia = new Metodologia();
		//nuevaMetodologia.setCondiciones(condiciones);
		nuevaMetodologia.setNombre(nombreMetodologia);
		
		//String jsonElement = new Gson().toJson(nuevaMetodologia); 
	

		//ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("metodologias",jsonElement );	
		
	}
	
}