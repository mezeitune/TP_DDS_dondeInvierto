package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbar.commons.model.ObservableUtils;

import com.google.gson.Gson;

import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorNotFound;
import indicadoresPredefinidos.Antiguedad;
import indicadoresPredefinidos.PatrimonioNeto;
import parserArchivos.ParserJsonAObjetosJava;
import parserArchivos.ParserJsonString;
import parserIndicadores.ParserFormulaIndicador;
import usuario.Indicador;
import usuario.Metodologia;
import usuario.Usuarios;
import utilities.JPAUtility;

public class IndicadoresRepository extends DBRelacionalRepository<Indicador> {
	
	public IndicadoresRepository(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
	private ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");
	
	public static List<Indicador> getIndicadores(){
		List<Indicador> indicadores = new LinkedList<Indicador>();
		IndicadoresRepository.cargarIndicadores(indicadores);
		return indicadores;
	}
	

	
	public List<String> getNombreIndicadores(){
		return this.getIndicadores().stream().map(indicador -> indicador.getNombre())
													 .collect(Collectors.toList());
	}
	
	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		Query queryIndicadores = entityManager.createQuery("from Indicador"); 
		return queryIndicadores.getResultList(); 
	}
	
	public static List<Indicador>  getIndicadoresPredefinidos() {
		List<Indicador> indicadoresPredefinidos = new LinkedList<Indicador>();
		indicadoresPredefinidos.add(PatrimonioNeto.getInstance());
		indicadoresPredefinidos.add(Antiguedad.getInstance());
		return indicadoresPredefinidos;
	}
	
	public static void cargarIndicadores(List<Indicador> indicadores) {
		IndicadoresRepository.getIndicadoresPredefinidos().stream().forEach(indicadorPredefinido -> indicadores.add(indicadorPredefinido));
		IndicadoresRepository.getIndicadoresDefinidosPorElUsuario().stream().forEach(indicadorDefinidoPorUsuario -> indicadores.add(indicadorDefinidoPorUsuario));
	}
	public boolean validarIndicadorRepetidoAntesCargar(String nombre , String formula) {
		List<Indicador> indicadoresRepetidos = this.getIndicadores().stream().filter(line -> line.getNombre().equals(nombre)).collect(Collectors.toList());
		
		if (indicadoresRepetidos.size() >= 1){
			return true;
		} else {
			return false;
		}
	}


	public static void generarIndicador(String nombreIndicador, String formulaIndicador) throws NombreIndicadorNotFound, DatoRepetidoException, FormulaIndicadorNotValidException, FormulaIndicadorNotFound {
		
		if(nombreIndicador == null) throw new NombreIndicadorNotFound();
		if(formulaIndicador == null) throw new FormulaIndicadorNotFound();
		
		Indicador nuevoIndicador = new Indicador(nombreIndicador,formulaIndicador);
		
		if(IndicadoresRepository.esUnIndicadorYaIngresado(nuevoIndicador)) throw new DatoRepetidoException();
		
		if(!ParserFormulaIndicador.formulaIndicadorValida(formulaIndicador)) throw new FormulaIndicadorNotValidException();

		
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		} 
		
		IndicadoresRepository.agregar(nuevoIndicador);
		entityManager.getTransaction().commit();

	}
	public static boolean esUnIndicadorYaIngresado (Indicador nuevoIndicador) {
		IndicadoresRepository repositorioDeIndicadores = new IndicadoresRepository(entityManager);

		return repositorioDeIndicadores.validarIndicadorRepetidoAntesCargar(nuevoIndicador.getNombre(),nuevoIndicador.getFormula());
	}

	public static void eliminarIndicadorDeLaBDD(String nombreIndicador){
		
		List<Indicador> indicadorAEliminar =IndicadoresRepository.getIndicadores().stream().filter(unInd -> unInd.getNombre().equals(nombreIndicador)).collect(Collectors.toList());
		
				
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		} 
		IndicadoresRepository.eliminar(indicadorAEliminar.get(0));
		entityManager.getTransaction().commit();
		//this.setResultadoIndicador("Se ha eliminado correctamente el indicador :"+ nombreIndicador);

	}
	
	
	public static Indicador getIndicador(String nombreIndicador){
		
		List<Indicador> indicador = IndicadoresRepository.getIndicadores().stream().filter(unInd -> unInd.getNombre().equals(nombreIndicador)).collect(Collectors.toList());
		return indicador.get(0);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
