package usuario;
import java.util.*;
import org.uqbar.commons.utils.Observable;


@Observable
public class Empresa {

	String nombre;
	static List<Valor> valores = new ArrayList<Valor>();
	
	public String getNombre() {
		return nombre;
	}
	public static List<Valor> getValores() {
		return valores;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setValores(List<Valor> valores) {
		Empresa.valores = (ArrayList<Valor>) valores;
	}

	   
	
}
