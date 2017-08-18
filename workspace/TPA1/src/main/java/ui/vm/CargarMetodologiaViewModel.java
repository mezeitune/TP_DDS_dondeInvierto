package ui.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import com.google.gson.Gson;

import Condiciones.Condicion;
import parser.ParserJsonString;
import repository.CondicionesSeleccionadasRepository;
import repository.MetodologiasRepository;
import usuario.Metodologia;


@Observable
public class CargarMetodologiaViewModel {
	private static String nombreMetodologia ;
	private static List<Condicion> condiciones = new ArrayList<>();
	private Condicion condicion ;

	
	public List<Condicion> getCondiciones(){
		return CondicionesSeleccionadasRepository.getCondicionesSeleccionados();
	}
	
	public List<Metodologia> getMetodologias() {
		return MetodologiasRepository.getMetodologias();
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

	public void refresh() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}
	
	
	
}
