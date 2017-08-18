package ui.vm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.uqbar.commons.utils.Observable;


import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMayorIgual;
import Comparadores.ComparadorMenor;
import Comparadores.ComparadorMenorIgual;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.Taxativa;
import Condiciones.TipoCondicion;
import parserArchivos.ParserJsonAObjetosJava;
import repository.IndicadoresRepository;
import repository.MetodologiasRepository;
import usuario.Indicador;

@Observable
public class CargarCondicionViewModel {
	private  String nombreCondicion;
	
	private  List<Condicion> condiciones;
	private  Condicion condicion;
	
	private  List<TipoCondicion> tipoCondiciones = new ArrayList<>();
	private  TipoCondicion tipoCondicion;
	
	private  int pesoCondicion;
	
	
	private  List<String> comparadores = new ArrayList<>();
	private  String comparador;
	
	private  List<Indicador> indicadores = IndicadoresRepository.getIndicadoresDefinidosPorElUsuario();
	private  Indicador indicador;

	
	public CargarCondicionViewModel(){
		ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");
		indicadores=parser.getIndicadoresDelArchivo();
		
		nombreCondicion=null;
		tipoCondicion= tipoCondiciones.get(0);
		comparador= comparadores.get(0);
	}
	
	
	public Indicador getIndicador() {
		return indicador;
	}


	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}


	public String getnombreCondicion() {
		return nombreCondicion;
	}
	public int getPesoCondicion() {
		return pesoCondicion;
	}


	public void setPesoCondicion(int pesoCondicion) {
		this.pesoCondicion = pesoCondicion;
	}

	public void setnombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}


	public List<Condicion> getCondiciones() {
		return condiciones;
	}


	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}


	public Condicion getCondicion() {
		return condicion;
	}


	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}


	public List<TipoCondicion> getTipoCondiciones() {
		return tipoCondiciones;
	}

	public TipoCondicion getTipoCondicion() {
		return this.tipoCondicion;
	}


	public void setTipoCondicion(TipoCondicion tipoCondicion) {
		this.tipoCondicion = tipoCondicion;
	}


	public List<String> getComparadores() {
		return comparadores;
	}


	public void setComparadores(List<String> comparadores) {
		this.comparadores = comparadores;
	}


	public String getComparador() {
		return comparador;
	}


	public void setComparador(String comparador) {
		this.comparador = comparador;
		
	}
	
	public List<Indicador> getIndicadores(){
		Collections.sort(this.indicadores);
		return this.indicadores;
	}
	
	
	public void generarCondicion() {
		Condicion condicionDefinidaPorUsuario = crearObjetoCondicion();
		MetodologiasRepository.addCondicion(condicionDefinidaPorUsuario);
	}
	
	private  Condicion crearObjetoCondicion() {
			
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
	
}
