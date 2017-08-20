package mocks;

import java.util.LinkedList;
import java.util.List;

import indicadoresPredefinidos.IndicadorCustom;
import usuario.Indicador;

public class ListaIndicadoresMock {
	private List<Indicador> indicadores = new LinkedList<Indicador>();
	
	public void setIndicadoresMockeados(){
		this.indicadores.add(new IndicadorCustom("IndicadorConSumas","1+1"));
		this.indicadores.add(new IndicadorCustom("IndicadorConRestas","3-1"));
		this.indicadores.add(new IndicadorCustom("IndicadorConMultiplicacion","2*1"));
		this.indicadores.add(new IndicadorCustom("IndicadorConDivision","8/4"));
		this.indicadores.add(new IndicadorCustom("IndicadorOperandoConOtroIndicador","IndicadorConSumas-2"));
		this.indicadores.add(new IndicadorCustom("IndicadorConOperacionesDistintas","5-1+2/2-2*3+11"));
		this.indicadores.add(new IndicadorCustom("IndicadorCombinado","EBITDA-2+IndicadorConMultiplicacion-2*1"));
	}
	
	
	public List<Indicador> getIndicadoresMockeados() {
		return this.indicadores;
	}
	
}
