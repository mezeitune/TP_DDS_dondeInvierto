package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import Server.Controller.ControllerIndicadores;
import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorVacioError;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorVacioError;
import indicadoresPredefinidos.Antiguedad;
import indicadoresPredefinidos.PatrimonioNeto;
import model.Empresa;
import model.Indicador;
import model.IndicadorPrecalculado;
import model.Usuario;
import parserIndicadores.ParserFormulaIndicador;

public class RepositorioIndicadores extends RepositorioDBRelational<Indicador> {
	
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


	public void generarIndicador( Indicador indicador) throws NombreIndicadorVacioError, DatoRepetidoException, FormulaIndicadorNotValidException, FormulaIndicadorVacioError {
		
		String nombreIndicador = indicador.getNombre();
		String formulaIndicador = indicador.getFormula();
		Usuario user = indicador.getUsuario();
		
		
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
		RepositorioIndicadores repositorioDeIndicadores = new RepositorioIndicadores();

		return repositorioDeIndicadores.validarIndicadorRepetidoAntesCargar(nuevoIndicador.getNombre(),nuevoIndicador.getFormula());
	}

	public void eliminarIndicador(String nombreIndicador, Usuario user){
		List<Indicador> indicadorAEliminar = this.getIndicadoresPorUsuario(user.getUsername()).stream().filter(indic -> indic.getNombre().equals(nombreIndicador)).collect(Collectors.toList());

		eliminarInstanciasIndicador(indicadorAEliminar);

	}


	private void eliminarInstanciasIndicador(List<Indicador> indicadorAEliminar) {
		if (!entityManager().getTransaction().isActive()) {
			entityManager().getTransaction().begin();
		} 

		Query queryIndicador = entityManager().createQuery("delete from IndicadorPrecalculado where indicador_id = :indicador_id");
		queryIndicador.setParameter("indicador_id", getIndicadorId(indicadorAEliminar.get(0).getNombre()));
		queryIndicador.executeUpdate();
		this.eliminar(indicadorAEliminar.get(0));
		
		entityManager().getTransaction().commit();
	}
	
	
	public Indicador getIndicador(String nombreIndicador,String user){
		
		List<Indicador> indicador = this.getIndicadoresPorUsuario(user).stream().filter(unInd -> unInd.getNombre().equals(nombreIndicador)).collect(Collectors.toList());
		try{
			return indicador.get(0);
		}catch(IndexOutOfBoundsException e){
			ControllerIndicadores.setErrorMessage("Debe seleccionar un indicador y una empresa");
		}
		return null;

		
		
	}


	@SuppressWarnings("unchecked")
	public List<Indicador> getIndicadoresPorUsuario(String username) {

		Query queryIdUsuario = entityManager().createQuery("from Usuario where username= :username");
		queryIdUsuario.setParameter("username", username);
		Usuario usuario = (Usuario)queryIdUsuario.getSingleResult();
		
		Query queryIndicadores = entityManager().createQuery("from Indicador where usuario_id = :user_id");
		queryIndicadores.setParameter("user_id",usuario.getId());
		List<Indicador> indicadores = queryIndicadores.getResultList();

		return indicadores;
	}
	
	public void generarPrecalculado(Indicador indicador, Empresa empresa, String periodo){
		
		indicador.construirOperadorRaiz(empresa, periodo);
		
		IndicadorPrecalculado indicadorPrecalculado = new IndicadorPrecalculado(empresa,periodo,indicador.calcular(),indicador,true);
		
		
		if (!entityManager().getTransaction().isActive()) {
			entityManager().getTransaction().begin();
		} 
		
		this.agregar(indicadorPrecalculado);
		entityManager().getTransaction().commit();
		
		
	}
	
	
	public long getIndicadorId(String nombre){
		Query queryIdIndicador = entityManager().createQuery("select id from Indicador where nombre= :nombre and usuario_id != null");
		queryIdIndicador.setParameter("nombre", nombre);
		try{
			return (long) queryIdIndicador.getSingleResult();
		}catch (NoResultException e) {
		}
		return 0;
	}
	
	public IndicadorPrecalculado getIndicadorPrecalculado(String nombreIndicador,String nombreEmpresa,String periodo){
		IndicadorPrecalculado indicador;
		
		Query queryIndicadorPrecalculado = entityManager().createQuery("from IndicadorPrecalculado where indicador_id= :indicador_id and empresa_id= :empresa_id and periodo= :periodo");
		queryIndicadorPrecalculado.setParameter("indicador_id", getIndicadorId(nombreIndicador));
		queryIndicadorPrecalculado.setParameter("empresa_id", new RepositorioEmpresas().getIdEmpresa(nombreEmpresa));
		queryIndicadorPrecalculado.setParameter("periodo",periodo);

		try{
			indicador = (IndicadorPrecalculado) queryIndicadorPrecalculado.getSingleResult();
		}catch(NoResultException  | NonUniqueResultException e){
			
			return null;
		}

		return indicador;
		
	}

	public int getResultadoPrecalculado(String nombreIndicador,String nombreEmpresa,String periodo){
		return getIndicadorPrecalculado(nombreIndicador, nombreEmpresa, periodo).getResultado();
		
	}
	
	public boolean validarExistenciaIndicadorPrecalculado(String nombreIndicador,String nombreEmpresa,String periodo){
		
		Query queryIndicadorPrecalculado = entityManager().createQuery("from IndicadorPrecalculado where indicador_id= :indicador_id and empresa_id= :empresa_id and periodo= :periodo");
		queryIndicadorPrecalculado.setParameter("indicador_id", getIndicadorId(nombreIndicador));
		if(getIndicadorId(nombreIndicador) == 0) return true;
		queryIndicadorPrecalculado.setParameter("empresa_id", new RepositorioEmpresas().getIdEmpresa(nombreEmpresa));
		queryIndicadorPrecalculado.setParameter("periodo",periodo);
		
		return queryIndicadorPrecalculado.getResultList().size() > 0;
			
	}
	
	public void updateResultadoIndicador(IndicadorPrecalculado indicador){
		Query updateIndicadorPrecalculado = entityManager().createQuery("update IndicadorPrecalculado set resultado= :r where indicador_id= :indicador_id and empresa_id= :empresa_id and periodo= :periodo");
		updateIndicadorPrecalculado.setParameter("indicador_id", getIndicadorId(indicador.getIndicador().getNombre()));
		updateIndicadorPrecalculado.setParameter("empresa_id", new RepositorioEmpresas().getIdEmpresa(indicador.getEmpresa().getNombre()));
		updateIndicadorPrecalculado.setParameter("periodo",indicador.getPeriodo());
		updateIndicadorPrecalculado.setParameter("r",indicador.getResultado());
		updateIndicadorPrecalculado.executeUpdate();
		
	}
	
}
