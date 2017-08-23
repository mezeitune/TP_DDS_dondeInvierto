package ui.vm;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.IndicadorRepetidoException;
import excepciones.NombreIndicadorNotFound;
import parserIndicadores.ParserFormulaIndicador;
import repositorios.DBRelacionalRepository;
import repositorios.IndicadoresRepository;
import usuario.Indicador;
import utilities.JPAUtility;
@Observable
public class CargarBDViewModel {

	private static List<Indicador> indicadores = IndicadoresRepository.getIndicadoresDefinidosPorElUsuario();
	
	private static String idIndicador;
	private static String formulaIndicador;
	private static String resultadoIndicador;
	
	JPAUtility jpa=JPAUtility.getInstance();
	EntityManager entityManager = jpa.getEntityManager();
	DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);
	


	public CargarBDViewModel(){
		idIndicador=null;
		formulaIndicador=null;
	}
	
	public String getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(String idIndicador) {
		CargarBDViewModel.idIndicador = idIndicador;
	}
	public String getFormulaIndicador() {
		return formulaIndicador;
	}
	public void setFormulaIndicador(String formulaIndicador) {

		CargarBDViewModel.formulaIndicador = formulaIndicador;
	}
	
	public String getResultadoIndicador() {
		return resultadoIndicador;
	}

	public void setResultadoIndicador(String resultadoIndicador) {
		CargarBDViewModel.resultadoIndicador = resultadoIndicador;
	}

	public void traerResultadoPorID(){
		//Consulta por un id de cualquier tabla sea
		Indicador indicador=(Indicador) repo.findById(Indicador.class,1);
		CargarBDViewModel.resultadoIndicador = indicador.toString();

		ObservableUtils.firePropertyChanged(this, "resultadoIndicador");
	}
	
	public void traerResultadoPorFormula(){
		
	}

	
	
	
	
}
