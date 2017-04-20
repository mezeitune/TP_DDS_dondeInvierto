package usuario;
import java.io.*;
import java.util.*;

import org.uqbar.commons.utils.Observable;
@Observable
public class Cuenta {

	List<Empresa> empresas = new ArrayList<Empresa>();
	
	
	
	
	public String leerInformacionCuenta  (String path){
		String texto = "";
		
			try{
				BufferedReader bf = new BufferedReader (new FileReader(path)); //crea un buffer y fileReader para leer el archivo a traves del path
				String temp = "";
				String bfRead;
					while( (bfRead = bf.readLine()) != null  ){ //cuando no recibe mas datos corta
							temp = temp + bfRead;
					}
				texto = temp;
				bf.close();
			}catch (Exception e){System.err.println("No se encontro el archivo");}
			
			return texto;
	}

	
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}	
	public void setValores(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
