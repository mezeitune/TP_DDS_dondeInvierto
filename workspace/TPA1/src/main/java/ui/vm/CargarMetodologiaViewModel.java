package ui.vm;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.experimental.theories.Theories;
import org.uqbar.commons.utils.Observable;

import com.google.gson.Gson;

import Comparadores.ComparadorMayor;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import metodologias.Predefinidas.WarrenBuffet;
import parser.ParserJsonString;
import parser.parserArchivos.ParserJsonAObjetosJava;
import repository.MetodologiasRepository;
import usuario.Indicador;
import usuario.Metodologia;


@Observable
public class CargarMetodologiaViewModel {
	private List<Metodologia> metodologias;
	private Metodologia metodologia;
	private static String nombreMetodologia ;
	private static List<Condicion> condiciones;
	private Condicion condicion;
	
	public CargarMetodologiaViewModel(){
		this.setMetodologias();
		this.setCondiciones();
		System.out.println(condiciones);
	}
	private void setCondiciones() {
		
		this.condiciones = MetodologiasRepository.getCondiciones();
		
	}
	public  List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	public void setMetodologias(){
		ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("metodologias.json");
		MetodologiasRepository.setMetodologiasDefinidasPorElUsuario(parser.getMetodologiasDelArchivo());
		
		metodologias = MetodologiasRepository.getMetodologias();
	}
	
	
	public List<Metodologia> getMetodologias() {
		return this.metodologias;
	
	}
	
	public Condicion getCondicion() {
		return condicion;
	}
	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombre) {
		this.nombreMetodologia = nombre;
	}
	
	public static void generarMetodologia() throws IOException{
		int pesoRoe = 10;
		Indicador roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
		Condicion maximizarROE = new Condicion("maximizarRoe",new Comparativa(new ComparadorMayor()),roe,pesoRoe);
		condiciones.add(maximizarROE);
		
		Metodologia nuevaMetodologia = new Metodologia();
		nuevaMetodologia.setNombre(nombreMetodologia);
		nuevaMetodologia.setCondiciones(condiciones);
	
		
		
		String jsonElement = new Gson().toJson(nuevaMetodologia); 
	

		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("metodologias",jsonElement );	
		
	}
	
}
