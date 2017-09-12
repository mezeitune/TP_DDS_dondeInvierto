package ui.vm;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import excepciones.CondicionRepetidaException;
import repositorios.CondicionesRepository;
import repositorios.CondicionesSeleccionadasRepository;
import repositorios.MetodologiasRepository;
import utilities.JPAUtility;
@Observable
public class AgregarCondicionSeleccionadaViewModel {

	private JPAUtility jpa=JPAUtility.getInstance();
	private EntityManager entityManager = this.jpa.getEntityManager();
	private CondicionesRepository repo=new CondicionesRepository(this.entityManager);
	
	private  Condicion condicionSeleccionada;
	
	public List<Condicion> getCondiciones() {
		return repo.getCondiciones();
	}
	
	public Condicion getCondicionSeleccionada() {
		return this.condicionSeleccionada;
	}
	
	public void setCondicionSeleccionada(Condicion condicion) {
		this.condicionSeleccionada = condicion;
	}
	
	public void agregarCondicionALaLista() throws CondicionRepetidaException {
		if(CondicionesSeleccionadasRepository.esCondicionYaAgregada(condicionSeleccionada)) {
			throw new CondicionRepetidaException();
		}
		else{
			CondicionesSeleccionadasRepository.agregarCondicionSeleccionada(this.condicionSeleccionada);
		}
	}
	
}
