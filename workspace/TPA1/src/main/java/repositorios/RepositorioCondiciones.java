package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import comparadores.Comparador;
import comparadores.ComparadorMayor;
import comparadores.ComparadorMayorIgual;
import comparadores.ComparadorMenor;
import comparadores.ComparadorMenorIgual;
import condiciones.Comparativa;
import condiciones.Condicion;
import condiciones.Mixta;
import condiciones.Taxativa;
import condiciones.TipoCondicion;
import condicionesPredefinidas.MargenesCrecientes;

public class RepositorioCondiciones extends RepositorioDBRelational<Condicion>{


	public List<Condicion> getCondiciones() {
		List<Condicion> condiciones = new LinkedList<Condicion>();
		this.cargarCondiciones(condiciones);
		return condiciones;
	}
	
	private List<Condicion> getCondicionesPredefinidas() {
		List<Condicion> condicionesPredefinidas = new LinkedList<Condicion>();
		condicionesPredefinidas.add(MargenesCrecientes.getInstance());
		return condicionesPredefinidas;
	}

	@SuppressWarnings("unchecked")
	private List<Condicion> getCondicionesDefinidasPorElUsuario() {
		Query queryIndicadores = entityManager().createQuery("from Condicion"); 
		return queryIndicadores.getResultList(); 
	}

	public void cargarCondiciones(List<Condicion> condiciones){
		this.getCondicionesDefinidasPorElUsuario().stream().forEach(condicionDefinidaPorUsuario -> condiciones.add(condicionDefinidaPorUsuario));
		this.getCondicionesPredefinidas().stream().forEach(condicionPredefinida -> condiciones.add(condicionPredefinida));
	}
	
	public List<TipoCondicion> getTipoCondiciones() {
		List<TipoCondicion> tipoCondiciones = new LinkedList<TipoCondicion>();
		tipoCondiciones.add(new Comparativa());
		tipoCondiciones.add(new Taxativa());
		tipoCondiciones.add(new Mixta());
		return tipoCondiciones;
	}
	public boolean validarCondicionRepetidoAntesCargar(String nombre) {
		return this.getCondiciones().stream().filter(line -> line.getNombre().equals(nombre)).collect(Collectors.toList()).size() >=1;
	}
	
	public List<Comparador> getComparadores() {
		List<Comparador> comparadores = new LinkedList<Comparador>();
		comparadores.add(new ComparadorMayor());
		comparadores.add(new ComparadorMayorIgual());
		comparadores.add(new ComparadorMenor());
		comparadores.add(new ComparadorMenorIgual());
		return comparadores;
	}
}
