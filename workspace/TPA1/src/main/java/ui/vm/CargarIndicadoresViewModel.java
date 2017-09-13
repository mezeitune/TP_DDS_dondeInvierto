package ui.vm;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.IndicadorRepetidoException;
import excepciones.NombreIndicadorNotFound;
import parserArchivos.ParserJsonAObjetosJava;
import parserIndicadores.ParserFormulaIndicador;
import repositorios.DBRelacionalRepository;
import repositorios.IndicadoresRepository;
import repositorios.MetodologiasRepository;
import usuario.Indicador;
import usuario.Metodologia;
import utilities.JPAUtility;
@Observable
public class CargarIndicadoresViewModel {
	
	private JPAUtility jpa=JPAUtility.getInstance();
	private EntityManager entityManager = this.jpa.getEntityManager();
	private IndicadoresRepository repo=new IndicadoresRepository(this.entityManager);
	
	
	private List<Indicador> indicadores = repo.getIndicadoresDefinidosPorElUsuario();
	
	private Indicador indicadorSeleccionado;
	private static String nombreIndicador;
	private static String formulaIndicador;
	private static String resultadoIndicador;
	public CargarIndicadoresViewModel(){
		nombreIndicador=null;
		formulaIndicador=null;
		
	}
	private static int codigoDeError;
	
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	public void setNombreIndicador(String nombreIndicador) {
		CargarIndicadoresViewModel.nombreIndicador = nombreIndicador;
	}
	public String getFormulaIndicador() {
		return formulaIndicador;
	}
	public void setFormulaIndicador(String formulaIndicador) {

		CargarIndicadoresViewModel.formulaIndicador = formulaIndicador;
	}
	
	public static int getCodigoDeError() {
		return codigoDeError;
	}
	
	public static void setCodigoDeError(int codigoDeError) {
		CargarIndicadoresViewModel.codigoDeError = codigoDeError;
	}
	public String getResultadoIndicador() {
		return resultadoIndicador;
	}

	public void setResultadoIndicador(String resultadoIndicador) {
		this.resultadoIndicador = resultadoIndicador;
	}
	
	//@SuppressWarnings("unchecked")
	public void generarIndicador() throws NombreIndicadorNotFound, IndicadorRepetidoException, FormulaIndicadorNotValidException, FormulaIndicadorNotFound {
		
		if(nombreIndicador == null) throw new NombreIndicadorNotFound();
		if(formulaIndicador == null) throw new FormulaIndicadorNotFound();
		
		Indicador nuevoIndicador = new Indicador(nombreIndicador,formulaIndicador);
		
		if(this.esUnIndicadorYaIngresado(nuevoIndicador)) throw new IndicadorRepetidoException();
		
		System.out.println("Formula " + formulaIndicador);
		
		if(!ParserFormulaIndicador.formulaIndicadorValida(formulaIndicador)) throw new FormulaIndicadorNotValidException();

		
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		} 
		
		repo.agregar(nuevoIndicador);
		entityManager.getTransaction().commit();
		
		ObservableUtils.firePropertyChanged(this, "resultadoIndicador");
		
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}
	public boolean esUnIndicadorYaIngresado (Indicador nuevoIndicador) {
		IndicadoresRepository repositorioDeIndicadores = new IndicadoresRepository(entityManager);

		return repositorioDeIndicadores.validarIndicadorRepetidoAntesCargar(nuevoIndicador.getNombre(),nuevoIndicador.getFormula());
	}
	public List<Indicador> getIndicadores(){
		Collections.sort(indicadores);
		return indicadores;
	}
	
	public void eliminarIndicadorDeLaBDD(){
		
		List <Indicador> indicadorAEliminar = indicadores.stream().filter(unInd -> unInd.getNombre()==indicadorSeleccionado.getNombre()).collect(Collectors.toList());
		
		
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		} 
		
		
		repo.eliminar(indicadorAEliminar.get(0));
		entityManager.getTransaction().commit();
		this.setResultadoIndicador("Se ha eliminado correctamente el indicador :"+indicadorAEliminar.get(0).getNombre());

	}
	
}
