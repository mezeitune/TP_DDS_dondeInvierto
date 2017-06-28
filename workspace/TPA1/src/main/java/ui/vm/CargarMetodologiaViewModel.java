package ui.vm;

import java.io.IOException;
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
private static List<Metodologia> metodologias = MetodologiasUsuarioRepository.getMetodologiasDefinidosPorElUsuario();
private static String nombre;
private static Condicion condicion;
public static List<Metodologia> getMetodologias() {
	return metodologias;
}
public static void setMetodologias(List<Metodologia> metodologias) {
	CargarMetodologiaViewModel.metodologias = metodologias;
}
	
	
	public static Condicion getCondicion() {
		return condicion;
	}
	public static void setCondicion(Condicion condicion) {
		CargarMetodologiaViewModel.condicion = condicion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public static void generarMetodologia() throws IOException{
		
		Metodologia nuevaMetodologia = new Metodologia();
		//nuevaMetodologia.setCondiciones(condiciones);
		nuevaMetodologia.setNombre(nombre);
		
		//String jsonElement = new Gson().toJson(nuevaMetodologia); 
	

		//ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("metodologias",jsonElement );	
		
	}
	
}
