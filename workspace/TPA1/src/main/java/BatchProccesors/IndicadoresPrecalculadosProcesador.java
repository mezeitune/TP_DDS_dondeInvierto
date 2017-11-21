package BatchProccesors;

import java.util.LinkedList;
import java.util.List;

import model.Empresa;
import model.Indicador;
import model.IndicadorPrecalculado;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;

public class IndicadoresPrecalculadosProcesador {

	private static List<Empresa> empresas;
	private static List<Indicador> indicadores;
	private static RepositorioEmpresas repositorio_empresas = new RepositorioEmpresas();
	private static RepositorioIndicadores repositorio_indicadores = new RepositorioIndicadores();
	
	public static void main(String[] args) {
		new IndicadoresPrecalculadosProcesador().init();
		System.out.println("Indicadores precalculados");
		System.exit(0);
	}
	
	private void init() {
		indicadores = repositorio_indicadores.getIndicadores();
		empresas = repositorio_empresas.getEmpresas();
		
		repositorio_indicadores.begin();
		indicadores.stream().forEach(indicador-> precalcularIndicador(indicador));
		repositorio_indicadores.commit();
	}
	
	private void precalcularIndicador(Indicador indicador) {
		empresas.stream().forEach(empresa -> precalcularIndicadorEnEmpresa(empresa,indicador));
	}
	
	private void precalcularIndicadorEnEmpresa(Empresa empresa,Indicador indicador) {
		List<IndicadorPrecalculado> indicadoresPrecalculados = new LinkedList<IndicadorPrecalculado>();
		
		empresa.getPeriodosSinRepetidos().stream().forEach(periodo->indicadoresPrecalculados.add(indicador.precalcular(empresa,periodo)));

	
		for (int i = 0; i < indicadoresPrecalculados.size(); i++) {
			IndicadorPrecalculado indicadorPrecalculado = indicadoresPrecalculados.get(i);
			String nomIndic = indicadorPrecalculado.getIndicador().getNombre();
			String periodo = indicadorPrecalculado.getPeriodo();
			
			if(indicadorPrecalculado.getFlag()){
				if(!repositorio_indicadores.validarExistenciaIndicadorPrecalculado(nomIndic, empresa.getNombre(), periodo)){
					repositorio_indicadores.agregar(indicadorPrecalculado);
				}else repositorio_indicadores.updateResultadoIndicador(indicadorPrecalculado);
			}
			
		}
	}
}
