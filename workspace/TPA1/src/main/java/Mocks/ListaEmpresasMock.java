package Mocks;

import java.util.ArrayList;
import java.util.List;

import usuario.Cuenta;
import usuario.Empresa;

public class ListaEmpresasMock {
	
	List<Empresa> empresas = new ArrayList<>();
	List<Cuenta> cuentas = new ArrayList<>();
	
	public List<Empresa> mockearListaEmpresas(){
		List<Cuenta> cuentasEmpresa1 = new ArrayList<>();
		Cuenta cuenta1 = new Cuenta("EBITDA","2016",200);
		Cuenta cuenta2 = new Cuenta("EBITDA","2015",300);
		Cuenta cuenta3 = new Cuenta("Free CashFlow","2015",1100);
		Cuenta cuenta4 = new Cuenta("Free CashFlow","2016",900);
		cuentasEmpresa1.add(cuenta1);
		cuentasEmpresa1.add(cuenta2);
		cuentasEmpresa1.add(cuenta3);
		cuentasEmpresa1.add(cuenta4);
		Empresa empresa1 = new Empresa("Facebook", cuentasEmpresa1);
		
		
		List<Cuenta> cuentasEmpresa2 = new ArrayList<>();
		Cuenta cuenta5 = new Cuenta("Free CashFlow","2016",50);
		Cuenta cuenta6 = new Cuenta("Revenue","2010",1000);
		Cuenta cuenta7 = new Cuenta("EBITDA","2016",400);
		Cuenta cuenta8 = new Cuenta("Revenue","2016",1000);
		cuentasEmpresa2.add(cuenta5);
		cuentasEmpresa2.add(cuenta6);
		cuentasEmpresa2.add(cuenta7);
		cuentasEmpresa2.add(cuenta8);
		Empresa empresa2 = new Empresa("Apple", cuentasEmpresa2);
		
		
		List<Cuenta> cuentasEmpresa3 = new ArrayList<>();
		Cuenta cuenta9 = new Cuenta("Free CashFlow","2016",100);
		Cuenta cuenta10 = new Cuenta("Revenue","2010",100);
		Cuenta cuenta11= new Cuenta("EBITDA","2016",300);
		Cuenta cuenta12= new Cuenta("Revenue","2016",500);
		cuentasEmpresa3.add(cuenta9);
		cuentasEmpresa3.add(cuenta10);
		cuentasEmpresa3.add(cuenta11);
		cuentasEmpresa3.add(cuenta12);
		Empresa empresa3 = new Empresa("IBM", cuentasEmpresa3);

		
		List<Cuenta> cuentasEmpresa4 = new ArrayList<>();
		Cuenta cuenta13 = new Cuenta("Free CashFlow","2016",35);
		Cuenta cuenta14 = new Cuenta("Revenue","2010",850);
		Cuenta cuenta15 = new Cuenta("EBITDA","2016",324);
		Cuenta cuenta16 = new Cuenta("Revenue","2016",900);
		cuentasEmpresa4.add(cuenta13);
		cuentasEmpresa4.add(cuenta14);
		cuentasEmpresa4.add(cuenta15);
		cuentasEmpresa4.add(cuenta16);
		Empresa empresa4 = new Empresa("Oracle", cuentasEmpresa4);
		
		
		
		this.empresas.add(empresa1);
		this.empresas.add(empresa2);
		this.empresas.add(empresa3);
		this.empresas.add(empresa4);
		return this.empresas;
	}
	
	public List<Cuenta> mockearListaCuentas(){
		Cuenta cuenta1 = new Cuenta("EBITDA","2016",200);
		Cuenta cuenta2 = new Cuenta("EBITDA","2015",300);
		Cuenta cuenta3 = new Cuenta("Free CashFlow","2015",1100);
		Cuenta cuenta4 = new Cuenta("Free CashFlow","2016",50);
		Cuenta cuenta5 = new Cuenta("Revenue","2014",1000);
		Cuenta cuenta6 = new Cuenta("Free CashFlow","2016",900);
		this.cuentas.add(cuenta1);
		this.cuentas.add(cuenta2);
		this.cuentas.add(cuenta3);
		this.cuentas.add(cuenta4);
		this.cuentas.add(cuenta5);
		this.cuentas.add(cuenta6);
		return this.cuentas;
	}
}