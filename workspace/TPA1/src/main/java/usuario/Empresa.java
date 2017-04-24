package usuario;
import java.util.*;
import org.uqbar.commons.utils.Observable;


@Observable
public class Empresa {

	String nombre;
	private List<Cuenta> cuenta = new LinkedList<>(); 
	
	public String getNombre() {
		return nombre;
	}
	public  List<Cuenta> getCuentas() {
		return cuenta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuenta=cuenta;
	}
	   
	
}
