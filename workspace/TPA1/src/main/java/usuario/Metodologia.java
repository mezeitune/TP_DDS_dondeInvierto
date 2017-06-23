package usuario;

import java.util.LinkedList;
import java.util.List;

import metodologias.Condicion;

public class Metodologia {

	private List<Condicion> condiciones = new LinkedList<>();
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	} 
	
	
	
}
