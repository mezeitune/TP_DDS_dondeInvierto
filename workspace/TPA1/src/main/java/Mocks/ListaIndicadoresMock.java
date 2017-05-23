package Mocks;

import java.util.LinkedList;
import java.util.List;

import usuario.Indicador;
import usuario.IndicadorCustom;

public class ListaIndicadoresMock {
	private List<Indicador> indicadores = new LinkedList<Indicador>();
	
	public void setIndicadoresMockeados(){
		this.indicadores.add(new IndicadorCustom("IndicadorConSumas","1+1"));
		this.indicadores.add(new IndicadorCustom("IndicadorConRestas","3-1"));
		this.indicadores.add(new IndicadorCustom("IndicadorConMultiplicacion","2*1"));
		this.indicadores.add(new IndicadorCustom("IndicadorConDivison","8/4"));
	}
	
	
	public List<Indicador> getIndicadoresMockeados() {
		return this.indicadores;
	}
	
}
