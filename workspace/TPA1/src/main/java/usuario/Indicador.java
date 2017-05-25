package usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.omg.CORBA.UserException;
import org.uqbar.commons.utils.Observable;

import parser.ParserFormulaToIndicador;

@Observable
public class Indicador  {

	private String nombre;

	public Indicador(String nombre){
		this.nombre=nombre;

	}

	public int calcular() throws UserException {
		
		return 0;
	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreIndicador) {
		this.nombre = nombreIndicador;
	}
	
}
