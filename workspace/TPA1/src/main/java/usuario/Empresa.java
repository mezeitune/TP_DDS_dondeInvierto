package usuario;
import java.util.*;
import org.uqbar.commons.utils.Observable;


@Observable
public class Empresa {

	String nombre;
	static List<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	public String getNombre() {
		return nombre;
	}
	public static List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setValores(List<Cuenta> cuentas) {
		Empresa.cuentas = (ArrayList<Cuenta>) cuentas;
	}

	   
	
}
