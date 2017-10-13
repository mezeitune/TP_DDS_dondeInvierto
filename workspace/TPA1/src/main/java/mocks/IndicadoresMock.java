package mocks;

import java.util.LinkedList;
import java.util.List;

import usuario.Indicador;

public class IndicadoresMock {
	private List<Indicador> indicadores = new LinkedList<Indicador>();
	
	public IndicadoresMock() {
		this.setIndicadoresMockeados();
	}
	
	public void setIndicadoresMockeados(){
		this.indicadores.add(new Indicador("IndicadorConSumas","1+1"));
		this.indicadores.add(new Indicador("IndicadorConRestas","3-1"));
		this.indicadores.add(new Indicador("IndicadorConMultiplicacion","2*1"));
		this.indicadores.add(new Indicador("IndicadorConDivision","8/4"));
		this.indicadores.add(new Indicador("IndicadorOperandoConOtroIndicador","IndicadorConSumas-2"));
		this.indicadores.add(new Indicador("IndicadorConOperacionesDistintas","5-1+2/2-2*3+11"));
		this.indicadores.add(new Indicador("IndicadorCombinado","EBITDA-2+IndicadorConMultiplicacion-2*1"));
	}
	
	
	public List<Indicador> getIndicadoresMockeados() {
		return this.indicadores;
	}
	
}
