package condiciones;

import java.util.LinkedList;
import java.util.List;

import usuario.Empresa;
import org.uqbar.commons.utils.Observable;

import comparadores.Comparador;

@Observable
public class TipoCondicion { //Si senor, clase abstracta para la persistencia en Json.

	public String nombre;
	
	public List<Empresa> evaluar(List<Empresa> empresa,List<String> periodos, Condicion condicion){
		return new LinkedList<Empresa>();
	};
	public void setComparador(Comparador comparador){};
	
	public String getNombre(){
		return this.nombre;
	}
	
}
