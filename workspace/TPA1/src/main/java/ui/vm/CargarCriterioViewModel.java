package ui.vm;

import java.util.Collections;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Indicador;

@Observable
public class CargarCriterioViewModel {
	private static String nombreCondicion;
	private static List<Condicion> condiciones;
	private static Condicion condicion;
	private static List<String> tipoCondiciones;
	private static String tipoCondicion;
	private static List<String> comparadores;
	private static String comparador;
	private static List<Indicador> indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();;
	private static Indicador indicador;
	
	public Indicador getIndicador() {
		return indicador;
	}


	public void setIndicador(Indicador indicador) {
		CargarCriterioViewModel.indicador = indicador;
	}


	public String getnombreCondicion() {
		return nombreCondicion;
	}


	public void setnombreCondicion(String nombreCondicion) {
		CargarCriterioViewModel.nombreCondicion = nombreCondicion;
	}


	public List<Condicion> getCondiciones() {
		return condiciones;
	}


	public void setCondiciones(List<Condicion> condiciones) {
		CargarCriterioViewModel.condiciones = condiciones;
	}


	public Condicion getCondicion() {
		return condicion;
	}


	public void setCondicion(Condicion condicion) {
		CargarCriterioViewModel.condicion = condicion;
	}


	public List<String> getTipoCondiciones() {
		return tipoCondiciones;
	}


	public void setTipoCondiciones(List<String> tipoCondiciones) {
		CargarCriterioViewModel.tipoCondiciones = tipoCondiciones;
	}


	public String getTipoCondicion() {
		return tipoCondicion;
	}


	public void setTipoCondicion(String tipoCondicion) {
		CargarCriterioViewModel.tipoCondicion = tipoCondicion;
	}


	public List<String> getComparadores() {
		return comparadores;
	}


	public void setComparadores(List<String> comparadores) {
		CargarCriterioViewModel.comparadores = comparadores;
	}


	public String getComparador() {
		return comparador;
	}


	public void setComparador(String comparador) {
		CargarCriterioViewModel.comparador = comparador;
	}


	public void setIndicadores(List<Indicador> indicadores) {
		CargarCriterioViewModel.indicadores = indicadores;
	}


	
	public List<Indicador> getIndicadores(){
		Collections.sort(this.indicadores);
		return this.indicadores;
	}
	
	
}
