package BatchProccesors;

import java.util.LinkedList;
import java.util.List;

import excepciones.AccountNotFoundException;
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
		System.exit(0);
	}
	
	private void init() {
		indicadores = repositorio_indicadores.getIndicadores();
		empresas = repositorio_empresas.getEmpresas();
		
		repositorio_indicadores.begin();
		indicadores.stream().forEach(indicador-> precalcularIndicador(indicador));
		repositorio_indicadores.commit();
		System.out.println("Indicadores precalculados");
	}
	
	private void precalcularIndicador(Indicador indicador) {
		empresas.stream().forEach(empresa -> precalcularIndicadorEnEmpresa(empresa,indicador));
	}
	
	private void precalcularIndicadorEnEmpresa(Empresa empresa,Indicador indicador) {
		List<IndicadorPrecalculado> indicadoresPrecalculados = new LinkedList<IndicadorPrecalculado>();
		
		empresa.getPeriodosSinRepetidos().stream().forEach(periodo->precalcularEn(indicadoresPrecalculados,empresa,periodo,indicador));

		indicadoresPrecalculados.stream().forEach(indicadorPrecalculado -> persistir(indicadorPrecalculado,empresa));
	}
	
	//TODO: Hacer Log para esos indicadores que no pudieron calcularse en una empresa y periodo.
	private void precalcularEn(List<IndicadorPrecalculado> indicadoresPrecalculados,Empresa empresa, String periodo,Indicador indicador){
		IndicadorPrecalculado indicadorPrecalculado = new IndicadorPrecalculado();
		
		try {
			indicadorPrecalculado = indicador.precalcular(empresa, periodo);
			indicadoresPrecalculados.add(indicadorPrecalculado);
		}catch (AccountNotFoundException exception) {
			System.out.println("El indicador "+indicador.getNombre()+" no pudo ser calculado en la empresa "
								+exception.getEmpresa().getNombre()+" para el periodo "+exception.getPeriodo()
								+" porque no tiene la cuenta "+exception.getNombreCuenta());
		}
	}
	
	private void persistir(IndicadorPrecalculado indicadorPrecalculado,Empresa empresa) {
		String nomIndic = indicadorPrecalculado.getIndicador().getNombre();
		String periodo = indicadorPrecalculado.getPeriodo();
		
		if(!repositorio_indicadores.validarExistenciaIndicadorPrecalculado(nomIndic, empresa.getNombre(), periodo)){
			repositorio_indicadores.agregar(indicadorPrecalculado);
		}else repositorio_indicadores.updateResultadoIndicador(indicadorPrecalculado);
	}
}
