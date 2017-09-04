package ui.vm;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;

import comparadores.Comparador;
import condiciones.Condicion;
import condiciones.Taxativa;
import condiciones.TipoCondicion;
import excepciones.IndicadorNotFound;
import excepciones.NombreCondicionNotFound;
import excepciones.PesoCondicionNotFound;
import excepciones.TipoCondicionNotFound;
import indicadoresPredefinidos.PatrimonioNeto;
import repositorios.CondicionesRepository;
import repositorios.DBRelacionalRepository;
import repositorios.IndicadoresRepository;
import usuario.Indicador;
import utilities.JPAUtility;

@Observable
public class CargarCondicionViewModel {
	
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
		return CondicionesRepository.getCondiciones();
	}

	public List<TipoCondicion> getTipoCondiciones() {
		return CondicionesRepository.getTipoCondiciones();
	}
	
	public List<Comparador> getComparadores() {
		return CondicionesRepository.getComparadores();
	}
	
	public List<Indicador> getIndicadores(){
		return IndicadoresRepository.getIndicadores();
	}
	
	public void generarCondicion() throws NombreCondicionNotFound, TipoCondicionNotFound, PesoCondicionNotFound, IndicadorNotFound {
		if(this.nombreCondicion == null) throw new NombreCondicionNotFound();
		if(this.tipoCondicion == null) throw new TipoCondicionNotFound();
		if(this.indicador == null) throw new IndicadorNotFound();
		if(this.pesoCondicion == -1) throw new PesoCondicionNotFound();
		
		JPAUtility jpa=JPAUtility.getInstance();
		EntityManager entityManager = jpa.getEntityManager();
		DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);
		this.tipoCondicion.setComparador(this.comparador);

		
		//CondicionesRepository.addCondicion(condicionDefinidaPorUsuario);
		//repo.agregar(condicionDefinidaPorUsuario);
		
		
		entityManager.getTransaction().begin();
		
		Condicion condicionDefinidaPorUsuario = new Condicion(this.nombreCondicion,this.tipoCondicion,this.indicador,this.pesoCondicion);
		repo.agregar(condicionDefinidaPorUsuario);

		entityManager.getTransaction().commit();
		
	}
	
}
