package condiciones;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

import usuario.Empresa;
import org.uqbar.commons.utils.Observable;

import comparadores.Comparador;

@Observable
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TipoCondicion { //Si senor, clase abstracta para la persistencia en Json.

	@Id @GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST) 
	@OrderColumn
	public Comparador comparador;
	public String nombre;
	
	public List<Empresa> evaluar(List<Empresa> empresa,List<String> periodos, Condicion condicion){
		return new LinkedList<Empresa>();
	};
	public void setComparador(Comparador comparador){};
	
	public String getNombre(){
		return this.nombre;
	}
	
}
