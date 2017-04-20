package Class;
import usuario.*;
import java.util.Scanner;
import org.uqbar.commons.utils.Observable;


@Observable
public class Main {

	
	public static void main(String[] args) {
		//Segun lo que entendi habria que leer el txt y que se vaya insertando todo en las clases de Empresa y las demas
		//Pero la clase que lee el txt no deberia ser Cuenta , habria que crear un modulo aparte para que sea mas facil de testear
		
		String path = "";
		Scanner pathScanner = new Scanner (System.in);
		Cuenta archivo = new Cuenta();
		
		System.out.println("Ingrese la ruta del archivo");
        
        path = pathScanner.nextLine ();
        
		String datosCuenta = archivo.leerInformacionCuenta(path);
		
		
		System.out.println(datosCuenta); //en windows va siempre la \\ en vez de \
		pathScanner.close();
		
		
	}
	
	
	
}

//"C:\\arch.txt"