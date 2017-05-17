package Mocks;

import java.util.LinkedList;
import java.util.List;

import usuario.Indicador;

public class ListaIndicadoresMock {
	private List<Indicador> indicadores = new LinkedList<Indicador>();
	
	public void setIndicadoresMockeados(){
		this.indicadores.add(new Indicador("IndicadorConSumas","1+1"));
		this.indicadores.add(new Indicador("IndicadorConRestas","3-1"));
		this.indicadores.add(new Indicador("IndicadorConMultiplicacion","2*1"));
		this.indicadores.add(new Indicador("IndicadorConDivison","8/4"));
	}
	
	
	public List<Indicador> getIndicadoresMockeados() {
		return this.indicadores;
	}
	
}
