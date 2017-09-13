package ui.vm;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import excepciones.CondicionesNotFoundException;
import excepciones.MetodologiaRepetidaException;
import excepciones.NombreMetodologiaNotFoundException;
import metodologiasPredefinidas.WarrenBuffet;
import repositorios.CondicionesSeleccionadasRepository;
import repositorios.DBRelacionalRepository;
import repositorios.MetodologiasRepository;
import usuario.Indicador;
import usuario.Metodologia;
import utilities.JPAUtility;


@Observable
public class CargarMetodologiaViewModel {
	private static String nombreMetodologia ;
	
	private JPAUtility jpa=JPAUtility.getInstance();
	private EntityManager entityManager = this.jpa.getEntityManager();
	private MetodologiasRepository repo=new MetodologiasRepository(this.entityManager);
	
	
	private String resultadoIndicador;
	private Metodologia metodologiaSeleccionada;
	private List <Condicion> condiciones;
	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}
	public CargarMetodologiaViewModel(){
		this.nombreMetodologia=null;
		this.condiciones=null;
	}
	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionadaa) {
		metodologiaSeleccionada = metodologiaSeleccionadaa;
	}

	public List<Condicion> getCondiciones(){
		return CondicionesSeleccionadasRepository.getCondicionesSeleccionados();
	}
	
	public List<Metodologia> getMetodologias() {
		return repo.getMetodologiasDefinidasPorElUsuario();
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombre) {
		CargarMetodologiaViewModel.nombreMetodologia = nombre;
	}
	
	public String getResultadoIndicador() {
		
		return resultadoIndicador;
	}

	public void setResultadoIndicador(String resultadoIndicador) {
		this.resultadoIndicador = resultadoIndicador;
	}
	
	public  void generarMetodologia() throws NombreMetodologiaNotFoundException, CondicionesNotFoundException, MetodologiaRepetidaException {
		
		if(nombreMetodologia == null) throw new NombreMetodologiaNotFoundException();
		if(repo.esMetodologiaRepetida(nombreMetodologia)) throw new MetodologiaRepetidaException();
		if(this.getCondiciones().isEmpty()) throw new CondicionesNotFoundException();
		
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		} 
		
	
		
		Metodologia nuevaMetodologia = new Metodologia();
		nuevaMetodologia.setNombre(nombreMetodologia);
		nuevaMetodologia.setCondiciones(this.getCondiciones());
		repo.agregar(nuevaMetodologia);

		this.entityManager.getTransaction().commit();
		this.nombreMetodologia=null;
		this.condiciones=null;
		
		ObservableUtils.firePropertyChanged(this, "metodologias");
	}

	public void eliminarMetodologiaDeBDD(){
		
		
		List <Metodologia> metodologiaAEliminar = this.getMetodologias().stream().filter(unInd -> unInd.getNombre()==metodologiaSeleccionada.getNombre()).collect(Collectors.toList());
		
		
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		} 
		
		repo.eliminar(metodologiaAEliminar.get(0));
		entityManager.getTransaction().commit();
		this.setResultadoIndicador("Se ha eliminado correctamente la metodologia :"+metodologiaAEliminar.get(0).getNombre());
		ObservableUtils.firePropertyChanged(this, "resultadoIndicador");
		ObservableUtils.firePropertyChanged(this, "metodologias");
	}
	public void reset(){
		nombreMetodologia = null;
		CondicionesSeleccionadasRepository.vaciarListaDeCondicionesSeleccionadas();
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}

	public void refresh() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
		
	}
	
	
}
