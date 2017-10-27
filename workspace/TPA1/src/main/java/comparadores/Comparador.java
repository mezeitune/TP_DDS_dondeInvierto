package comparadores;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="Comparadores")
public class Comparador { //Si senor, otra clase abstracta para la persistencia en Json
	@Id @GeneratedValue
	private Long id;
	
	public String nombre;
	
	public boolean comparar(int valor1, int valor2){return true;}
	
	public String getNombre(){
		return this.nombre;
	}
	
	
	
	
	
}














