package ui.vm;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;

import comparadores.Comparador;
import condiciones.Condicion;
import condiciones.Taxativa;
import condiciones.TipoCondicion;
import excepciones.IndicadorNotFound;
import excepciones.DatoRepetidoException;
import excepciones.NombreCondicionNotFound;
import excepciones.PesoCondicionNotFound;
import excepciones.TipoCondicionNotFound;
import indicadoresPredefinidos.PatrimonioNeto;
import repositorios.CondicionesRepository;
import repositorios.DBRelacionalRepository;
import repositorios.IndicadoresRepository;
import repositorios.MetodologiasRepository;
import usuario.Indicador;
import utilities.JPAUtility;

@Observable
public class CargarCondicionViewModel {
	
	private JPAUtility jpa=JPAUtility.getInstance();
	private EntityManager entityManager = this.jpa.getEntityManager();
	private IndicadoresRepository repoIndicadores=new IndicadoresRepository();
	

	private CondicionesRepository repoCondiciones=new CondicionesRepository();
	
	private  String nombreCondicion;
	private  TipoCondicion tipoCondicion;
	private  int pesoCondicion = -1;
	private  Indicador indicador;
	private Comparador comparador;
	
	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public String getnombreCondicion() {
		return nombreCondicion;
	}
	
	public void setnombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}
	
	public int getPesoCondicion() {
		return pesoCondicion;
	}

	public void setPesoCondicion(int pesoCondicion) {
		this.pesoCondicion = pesoCondicion;
	}

	public TipoCondicion getTipoCondicion() {
		return this.tipoCondicion;
	}

	public void setTipoCondicion(TipoCondicion tipoCondicion) {
		this.tipoCondicion = tipoCondicion;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;;
	}
	
	public Comparador getComparador() {
		return this.comparador;
	}
	
	public List<Condicion> getCondiciones() {
		return repoCondiciones.getCondiciones();
	}

	public List<TipoCondicion> getTipoCondiciones() {
		return repoCondiciones.getTipoCondiciones();
	}
	
	public List<Comparador> getComparadores() {
		return repoCondiciones.getComparadores();
	}
	
	public List<Indicador> getIndicadores(){
		return repoIndicadores.getIndicadores();
	}
	
	public void generarCondicion() throws NombreCondicionNotFound, TipoCondicionNotFound, PesoCondicionNotFound, IndicadorNotFound, DatoRepetidoException {
		if(this.nombreCondicion == null) throw new NombreCondicionNotFound();
		if(this.tipoCondicion == null) throw new TipoCondicionNotFound();
		if(this.indicador == null) throw new IndicadorNotFound();
		if(this.pesoCondicion == -1) throw new PesoCondicionNotFound();
		

		this.tipoCondicion.setComparador(this.comparador);
		Condicion condicionDefinidaPorUsuario = new Condicion(this.nombreCondicion,this.tipoCondicion,this.indicador,this.pesoCondicion);
		
		if(this.esUnaCondicionRepetida(condicionDefinidaPorUsuario.getNombre())) throw new DatoRepetidoException();
		
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		} 
		
		
		repoCondiciones.agregar(condicionDefinidaPorUsuario);

		entityManager.getTransaction().commit();
		
	}

	private boolean esUnaCondicionRepetida(String nombre) {
		CondicionesRepository repoDeCondiciones = new CondicionesRepository();
		return repoDeCondiciones.validarCondicionRepetidoAntesCargar(nombre);
		
	}
	
}
