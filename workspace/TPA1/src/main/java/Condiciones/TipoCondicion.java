package Condiciones;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import usuario.Empresa;

public abstract interface TipoCondicion {

	List<Empresa> evaluar(List<Empresa> empresa,List<String> periodos, Condicion condicion);

	void setComparador(Comparador comparador);
	
}
