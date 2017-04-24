package usuario;

import org.uqbar.commons.utils.Observable;
@Observable
public class Cuenta {

	String nombre;
	int periodo;
	int valor;
	
	public String getNombre (){
		return this.nombre;
	}
	
	public int getPeriodo (){
		return this.periodo;
	}
	
	public int getValor (){
		return this.valor;
	}
}

	
	//List<Empresa> empresas = new ArrayList<Empresa>();
	
	
	
	/*
	/No se tendria que usar esto ahora con la implementacion del lector de json
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

	*/


