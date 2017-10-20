package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorVacioError;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorVacioError;
import indicadoresPredefinidos.Antiguedad;
import indicadoresPredefinidos.PatrimonioNeto;
import model.Indicador;
import model.Usuario;
import parserIndicadores.ParserFormulaIndicador;

public class IndicadoresRepository extends DBRelacionalRepository<Indicador> {
	
	public List<Indicador> getIndicadores(){
		List<Indicador> indicadores = new LinkedList<Indicador>();
		this.cargarIndicadores(indicadores);
		return indicadores;
	}
	

	public List<String> getNombreIndicadores(){
		return this.getIndicadores().stream().map(indicador -> indicador.getNombre())
													 .collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	public List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		Query queryIndicadores = entityManager().createQuery("from Indicador"); 
		return queryIndicadores.getResultList(); 
	}
	
	public  List<Indicador>  getIndicadoresPredefinidos() {
		List<Indicador> indicadoresPredefinidos = new LinkedList<Indicador>();
		indicadoresPredefinidos.add(PatrimonioNeto.getInstance());
		indicadoresPredefinidos.add(Antiguedad.getInstance());
		return indicadoresPredefinidos;
	}
	
	public void cargarIndicadores(List<Indicador> indicadores) {
		this.getIndicadoresPredefinidos().stream().forEach(indicadorPredefinido -> indicadores.add(indicadorPredefinido));
		this.getIndicadoresDefinidosPorElUsuario().stream().forEach(indicadorDefinidoPorUsuario -> indicadores.add(indicadorDefinidoPorUsuario));
	}
	public boolean validarIndicadorRepetidoAntesCargar(String nombre , String formula) {
		List<Indicador> indicadoresRepetidos = this.getIndicadores().stream().filter(line -> line.getNombre().equals(nombre)).collect(Collectors.toList());
		return indicadoresRepetidos.size() >= 1;
	}


	public void generarIndicador(String nombreIndicador, String formulaIndicador,Usuario user) throws NombreIndicadorVacioError, DatoRepetidoException, FormulaIndicadorNotValidException, FormulaIndicadorVacioError {
		
		if(nombreIndicador == null) throw new NombreIndicadorVacioError();
		if(formulaIndicador == null) throw new FormulaIndicadorVacioError();
		
		Indicador nuevoIndicador = new Indicador(nombreIndicador,formulaIndicador,user);
		
		if(this.esIndicadorRepetido(nuevoIndicador)) throw new DatoRepetidoException();
		
		if(!ParserFormulaIndicador.esFormulaIndicadorValida(formulaIndicador)) throw new FormulaIndicadorNotValidException();

		
		if (!entityManager().getTransaction().isActive()) {
			entityManager().getTransaction().begin();
		} 
		
		this.agregar(nuevoIndicador);
		entityManager().getTransaction().commit();

	}
	
	public boolean esIndicadorRepetido (Indicador nuevoIndicador) {
		IndicadoresRepository repositorioDeIndicadores = new IndicadoresRepository();

		return repositorioDeIndicadores.validarIndicadorRepetidoAntesCargar(nuevoIndicador.getNombre(),nuevoIndicador.getFormula());
	}

	public void eliminarIndicador(String nombreIndicador){
		
		List<Indicador> indicadorAEliminar =this.getIndicadores().stream().filter(unInd -> unInd.getNombre().equals(nombreIndicador)).collect(Collectors.toList());
		
				
		if (!entityManager().getTransaction().isActive()) {
			entityManager().getTransaction().begin();
		} 
		this.eliminar(indicadorAEliminar.get(0));
		entityManager().getTransaction().commit();
		//this.setResultadoIndicador("Se ha eliminado correctamente el indicador :"+ nombreIndicador);

	}
	
	
	public Indicador getIndicador(String nombreIndicador){
		
		List<Indicador> indicador = this.getIndicadores().stream().filter(unInd -> unInd.getNombre().equals(nombreIndicador)).collect(Collectors.toList());
		return indicador.get(0);
		
		
	}


	@SuppressWarnings("unchecked")
	public List<Indicador> getIndicadoresPorUsuario(String username) {
		Query queryUsuarios = entityManager().createQuery("from Indicador where usuario_username like :username");
		queryUsuarios.setParameter("username",username);
		return queryUsuarios.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
