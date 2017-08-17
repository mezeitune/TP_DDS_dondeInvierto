package ui.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.internal.C;
import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMayorIgual;
import Comparadores.ComparadorMenor;
import Comparadores.ComparadorMenorIgual;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.Mixta;
import Condiciones.Taxativa;
import Condiciones.TipoCondicion;
import parser.ParserJsonString;
import parserArchivos.CSVToEmpresas;
import parserArchivos.ParserJsonAObjetosJava;
import parserIndicadores.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Empresa;
import usuario.Indicador;

@Observable
public class CargarCondicionViewModel {
	private static String nombreCondicion;
	
	private static List<Condicion> condiciones;
	private static Condicion condicion;
	
	private static List<String> tipoCondiciones = new ArrayList<>();
	
	private static String tipoCondicion;
	
	private static int pesoCondicion;
	
	
	private static List<String> comparadores = new ArrayList<>();
	private static String comparador;
	
	private static List<Indicador> indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
	private static Indicador indicador;

	private List<Empresa> empresas;

	private Empresa empresa;

	private List<String> periodos;

	private String periodo;
	
	public CargarCondicionViewModel(){
		ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");
		indicadores=parser.getIndicadoresDelArchivo();
		
		CargarComparadoresParaElUsuario();
		CargarTipoCondicionesParaElUsuario();
		nombreCondicion=null;
		tipoCondicion= tipoCondiciones.get(0);
		comparador= comparadores.get(0);
		
	}
	
	
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		this.empresas=parser.csvFileToEmpresas();
		
	}


	public Indicador getIndicador() {
		return indicador;
	}


	public void setIndicador(Indicador indicador) {
		CargarCondicionViewModel.indicador = indicador;
	}


	public String getnombreCondicion() {
		return nombreCondicion;
	}
	public int getPesoCondicion() {
		return pesoCondicion;
	}


	public void setPesoCondicion(int pesoCondicion) {
		CargarCondicionViewModel.pesoCondicion = pesoCondicion;
	}

	public void setnombreCondicion(String nombreCondicion) {
		CargarCondicionViewModel.nombreCondicion = nombreCondicion;
	}


	public List<Condicion> getCondiciones() {
		return condiciones;
	}


	public void setCondiciones(List<Condicion> condiciones) {
		CargarCondicionViewModel.condiciones = condiciones;
	}


	public Condicion getCondicion() {
		return condicion;
	}


	public void setCondicion(Condicion condicion) {
		CargarCondicionViewModel.condicion = condicion;
	}


	public List<String> getTipoCondiciones() {
		return tipoCondiciones;
	}


	public void setTipoCondiciones(List<String> tipoCondiciones) {
		CargarCondicionViewModel.tipoCondiciones = tipoCondiciones;
	}


	public String getTipoCondicion() {
		return tipoCondicion;
	}


	public void setTipoCondicion(String tipoCondicion) {
		CargarCondicionViewModel.tipoCondicion = tipoCondicion;
	}


	public List<String> getComparadores() {
		return comparadores;
	}


	public void setComparadores(List<String> comparadores) {
		CargarCondicionViewModel.comparadores = comparadores;
	}


	public String getComparador() {
		return comparador;
	}


	public void setComparador(String comparador) {
		CargarCondicionViewModel.comparador = comparador;
		
	}
	
	public List<Indicador> getIndicadores(){
		Collections.sort(CargarCondicionViewModel.indicadores);
		return this.indicadores;
	}
	public static void generarCondicion() {
		
		
		
		Condicion condicionDefinidaPorUsuario = crearObjetoCondicion();
		
		
		String jsonElement = new Gson().toJson(condicionDefinidaPorUsuario); 
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("condiciones",jsonElement );
	}
	
	private static Condicion crearObjetoCondicion() {
			
			if(tipoCondicion.equals("Comparativa")){
			
			if(comparador.equals(">")){
					TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
					Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,comparativa,indicador,pesoCondicion);
					return condicionDefinidaPorUsuario;
			}else if(comparador.equals(">=")){
					TipoCondicion comparativa = new Comparativa(new ComparadorMayorIgual());
					Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,comparativa,indicador,pesoCondicion);
					return condicionDefinidaPorUsuario;
			}else if(comparador.equals("<=")){
					TipoCondicion comparativa = new Comparativa(new ComparadorMenorIgual());
					Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,comparativa,indicador,pesoCondicion);
					return condicionDefinidaPorUsuario;
			}	
			else if(comparador.equals("<")){
					TipoCondicion comparativa = new Comparativa(new ComparadorMenor());
					Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,comparativa,indicador,pesoCondicion);
					return condicionDefinidaPorUsuario;
				}	
		
			
		}else if (tipoCondicion.equals("Taxativa")){
			
		if(comparador.equals(">")){
				Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,new Taxativa(new ComparadorMayor(), pesoCondicion),indicador,pesoCondicion);
				return condicionDefinidaPorUsuario;
		}else
		if(comparador.equals(">=")){
				Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,new Taxativa(new ComparadorMayorIgual(), pesoCondicion),indicador,pesoCondicion);
				return condicionDefinidaPorUsuario;
		}else
		if(comparador.equals("<=")){
				Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,new Taxativa(new ComparadorMenorIgual(), pesoCondicion),indicador,pesoCondicion);
				return condicionDefinidaPorUsuario;
		}else
		if(comparador.equals("<")){
				Condicion condicionDefinidaPorUsuario = new Condicion(nombreCondicion,new Taxativa(new ComparadorMenor(), pesoCondicion),indicador,pesoCondicion);
				return condicionDefinidaPorUsuario;
			}	
		}
			
		return null;
	}





	public void CargarComparadoresParaElUsuario() {
		if(comparadores.isEmpty()){
		CargarCondicionViewModel.comparadores.add(">");
		CargarCondicionViewModel.comparadores.add("<");
		CargarCondicionViewModel.comparadores.add("<=");
		CargarCondicionViewModel.comparadores.add(">=");
		}
	
	}
	public void CargarTipoCondicionesParaElUsuario() {
		if(tipoCondiciones.isEmpty()){
		CargarCondicionViewModel.tipoCondiciones.add("Comparativa");
		CargarCondicionViewModel.tipoCondiciones.add("Taxativa");
		}
	}
}
