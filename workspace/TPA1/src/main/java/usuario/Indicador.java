package usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Indicador {

	private String nombre;
	private List<Indicador> listaDeIndicadoresParaCalculo = new LinkedList<>(); //puede que tenga muchos indicadores a los cuales haga referencia
	private List<List<Integer>> listaDeEnterosParaCalculo = new ArrayList<>();// se que suena raro , pero puede ser que el calculo tenga 
							//muchos numeros constantes , por eso puse una lista
							//todo esto en realidad lo hago para que sea mas facil pasar a json , y leerlo despues
							//hay que buscar una buena forma de definir como hacer para introducir el calculo de cada indicador

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public List<Indicador> getListaDeIndicadoresParaCalculo() {
		return listaDeIndicadoresParaCalculo;
	}

	public void setListaDeIndicadoresParaCalculo(List<Indicador> listaDeIndicadoresParaCalculo) {
		this.listaDeIndicadoresParaCalculo = listaDeIndicadoresParaCalculo;
	}

	public List<List<Integer>> getListaDeEnterosParaCalculo() {
		return listaDeEnterosParaCalculo;
	}

	public void setListaDeEnterosParaCalculo(List<List<Integer>> listaDeEnterosParaCalculo) {
		this.listaDeEnterosParaCalculo = listaDeEnterosParaCalculo;
	}

	public int calcular() {
		// TODO Auto-generated method stub
		return 0;
	}
}
