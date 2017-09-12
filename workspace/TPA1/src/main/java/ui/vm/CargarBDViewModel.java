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
import repositorios.MetodologiasRepository;
import usuario.Indicador;
import utilities.JPAUtility;
@Observable
public class CargarBDViewModel {

	private JPAUtility jpa=JPAUtility.getInstance();
	private EntityManager entityManager = this.jpa.getEntityManager();
	private IndicadoresRepository repo=new IndicadoresRepository(this.entityManager);
	
	private List<Indicador> indicadores = repo.getIndicadoresDefinidosPorElUsuario();
	
	
	private static long idIndicador;
	private static String formulaIndicador;
	private static String resultadoIndicador;
	private static String nombreIndicador;
	
	


	public CargarBDViewModel(){
		formulaIndicador=null;
	}
	
	public Long getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(Long idIndicador) {
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
	
	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		CargarBDViewModel.nombreIndicador = nombreIndicador;
	}


	public void traerResultadoPorID(){
		//Consulta por un id de cualquier tabla sea
		Indicador indicador=(Indicador) repo.findById(Indicador.class,this.idIndicador);
		CargarBDViewModel.resultadoIndicador = indicador.toString();

		ObservableUtils.firePropertyChanged(this, "resultadoIndicador");
	}
	
	public void traerResultadoPorFormula(){
		//Consulta por un id de cualquier tabla sea
		List<Indicador> indicadores=repo.filtrarPorCampoEspecifico(Indicador.class,"Indicador", "formula", this.formulaIndicador);
		CargarBDViewModel.resultadoIndicador = indicadores.toString().substring(0, 9);

		ObservableUtils.firePropertyChanged(this, "resultadoIndicador");
	}
	
	public void agregarABD(){
		//Consulta por un id de cualquier tabla sea
		Indicador indicador=new Indicador(this.nombreIndicador,"bla");
		CargarBDViewModel.resultadoIndicador = "registro ingresado en la BD";
		entityManager.getTransaction().begin();
		repo.agregar(indicador);
		entityManager.getTransaction().commit();

		ObservableUtils.firePropertyChanged(this, "resultadoIndicador");
	}
	
	
}
