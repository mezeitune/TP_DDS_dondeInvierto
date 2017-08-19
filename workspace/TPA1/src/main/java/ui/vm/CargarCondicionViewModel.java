package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import Condiciones.Condicion;
import Condiciones.TipoCondicion;
import excepciones.IndicadorNotFound;
import excepciones.NombreCondicionNotFound;
import excepciones.PesoCondicionNotFound;
import excepciones.TipoCondicionNotFound;
import repository.CondicionesRepository;
import repository.IndicadoresRepository;
import usuario.Indicador;

@Observable
public class CargarCondicionViewModel {
	
	private  String nombreCondicion;
	private  TipoCondicion tipoCondicion;
	private  int pesoCondicion = -1;
	private  Comparador comparador;
	private  Indicador indicador;
	
	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public String getnombreCondicion() {
		return nombreCondicion;
	}
	
	public void setnombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}
	
	public int getPesoCondicion() {
		return pesoCondicion;
	}

	public void setPesoCondicion(int pesoCondicion) {
		this.pesoCondicion = pesoCondicion;
	}

	public TipoCondicion getTipoCondicion() {
		return this.tipoCondicion;
	}

	public void setTipoCondicion(TipoCondicion tipoCondicion) {
		this.tipoCondicion = tipoCondicion;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}
	
	public List<Condicion> getCondiciones() {
		return CondicionesRepository.getCondiciones();
	}

	public List<TipoCondicion> getTipoCondiciones() {
		return CondicionesRepository.getTipoCondiciones();
	}
	
	public List<Comparador> getComparadores() {
		return CondicionesRepository.getComparadores();
	}
	
	public List<Indicador> getIndicadores(){
		return IndicadoresRepository.getIndicadores();
	}
	
	public void generarCondicion() throws NombreCondicionNotFound, TipoCondicionNotFound, PesoCondicionNotFound, IndicadorNotFound {
		if(this.nombreCondicion == null) throw new NombreCondicionNotFound();
		if(this.tipoCondicion == null) throw new TipoCondicionNotFound();
		if(this.indicador == null) throw new IndicadorNotFound();
		if(this.pesoCondicion == -1) throw new PesoCondicionNotFound();
			
		Condicion condicionDefinidaPorUsuario = new Condicion(this.nombreCondicion,this.tipoCondicion,this.indicador,this.pesoCondicion);
		CondicionesRepository.addCondicion(condicionDefinidaPorUsuario);
	}
	
}
