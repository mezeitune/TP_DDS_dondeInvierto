package usuario;
import java.util.*;
import org.uqbar.commons.utils.Observable;


@Observable
public class Empresa {

	String nombre;
	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	public String getNombre() {
		return nombre;
	}
	public  List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas= (ArrayList<Cuenta>) cuentas;
	}

	   
	
}
