package Condiciones;

import java.util.LinkedList;
import java.util.List;

import Comparadores.Comparador;
import usuario.Empresa;

public class TipoCondicion { //Si senor, clase abstracta para la persistencia en Json.

	public List<Empresa> evaluar(List<Empresa> empresa,List<String> periodos, Condicion condicion){
		return new LinkedList<Empresa>();
	};
	void setComparador(Comparador comparador){};
	
}
