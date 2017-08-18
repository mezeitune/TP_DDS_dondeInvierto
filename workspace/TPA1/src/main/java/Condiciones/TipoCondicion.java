package Condiciones;

import java.util.List;

import Comparadores.Comparador;
import usuario.Empresa;

public interface TipoCondicion {

	public List<Empresa> evaluar(List<Empresa> empresa,List<String> periodos, Condicion condicion);
	void setComparador(Comparador comparador);
	
}
