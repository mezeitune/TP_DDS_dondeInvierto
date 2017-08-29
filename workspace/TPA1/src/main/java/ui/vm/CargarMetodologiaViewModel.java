package ui.vm;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import excepciones.CondicionesNotFoundException;
import excepciones.MetodologiaRepetidaException;
import excepciones.NombreMetodologiaNotFoundException;
import repositorios.CondicionesSeleccionadasRepository;
import repositorios.DBRelacionalRepository;
import repositorios.MetodologiasRepository;
import usuario.Metodologia;
import utilities.JPAUtility;


@Observable
public class CargarMetodologiaViewModel {
	private static String nombreMetodologia ;
	
	JPAUtility jpa=JPAUtility.getInstance();
	EntityManager entityManager = jpa.getEntityManager();
	DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);
	
	private Metodologia MetodologiaSeleccionada = new Metodologia();
	public List<Condicion> getCondiciones(){
		return CondicionesSeleccionadasRepository.getCondicionesSeleccionados();
	}
	
	public List<Metodologia> getMetodologias() {
		return MetodologiasRepository.getMetodologias();
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombre) {
		CargarMetodologiaViewModel.nombreMetodologia = nombre;
	}
	
	public  void generarMetodologia() throws NombreMetodologiaNotFoundException, CondicionesNotFoundException, MetodologiaRepetidaException {
		
		if(nombreMetodologia == null) throw new NombreMetodologiaNotFoundException();
		if(MetodologiasRepository.esMetodologiaRepetida(nombreMetodologia)) throw new MetodologiaRepetidaException();
		if(this.getCondiciones().isEmpty()) throw new CondicionesNotFoundException();
		
		Metodologia nuevaMetodologia = new Metodologia();
		nuevaMetodologia.setNombre(nombreMetodologia);
		nuevaMetodologia.setCondiciones(this.getCondiciones());
	
		MetodologiasRepository.addMetodologia(nuevaMetodologia);
		
		ObservableUtils.firePropertyChanged(this, "metodologias");
	}

	public void eliminarMetodologiaDeBDD(){
		entityManager.getTransaction().begin();
		System.out.println(MetodologiaSeleccionada);
		repo.eliminar(MetodologiaSeleccionada);
		
		entityManager.getTransaction().commit();
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
