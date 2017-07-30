package Condiciones;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import usuario.Empresa;

public class TipoCondicion {

	private Comparador comparador;

	public List<Empresa> evaluar(List<Empresa> empresa,List<String> periodos, Condicion condicion){
		List<Empresa> resultado = new LinkedList<>();
		return resultado;
	};

	void setComparador(Comparador comparador){
		this.comparador = comparador;
	};
	
}
