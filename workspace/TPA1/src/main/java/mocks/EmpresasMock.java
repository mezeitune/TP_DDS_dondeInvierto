package mocks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import model.Cuenta;
import model.Empresa;
public class EmpresasMock {
	
	List<Empresa> empresas = new LinkedList<Empresa>();
	
	public void mockearListaEmpresas(){
		List<Cuenta> cuentasEmpresa1 = new ArrayList<>();
		Cuenta cuenta1 = new Cuenta("EBITDA","2016",200);
		Cuenta cuenta2 = new Cuenta("EBITDA","2015",300);
		Cuenta cuenta3 = new Cuenta("Free CashFlow","2015",1100);
		Cuenta cuenta4 = new Cuenta("Free CashFlow","2016",900);
		Cuenta cuenta5 = new Cuenta("Ingreso Neto","2016",1500);
		Cuenta cuenta6 = new Cuenta("Dividendos","2016",500);
		Cuenta cuenta7 = new Cuenta("Capital Total","2016",1500);
		Cuenta cuenta8 = new Cuenta("Activo","2016",4000);
		Cuenta cuenta9 = new Cuenta("Pasivo","2016",2000);
		cuentasEmpresa1.add(cuenta1);
		cuentasEmpresa1.add(cuenta2);
		cuentasEmpresa1.add(cuenta3);
		cuentasEmpresa1.add(cuenta4);
		cuentasEmpresa1.add(cuenta5);
		cuentasEmpresa1.add(cuenta6);
		cuentasEmpresa1.add(cuenta7);
		cuentasEmpresa1.add(cuenta8);
		cuentasEmpresa1.add(cuenta9);
		Empresa empresa1 = new Empresa("Facebook", cuentasEmpresa1);
		
		
		List<Cuenta> cuentasEmpresa2 = new ArrayList<>();
		Cuenta cuenta11 = new Cuenta("Free CashFlow","2016",50);
		Cuenta cuenta12 = new Cuenta("Revenue","2010",1000);
		Cuenta cuenta13 = new Cuenta("EBITDA","2016",400);
		Cuenta cuenta14 = new Cuenta("Revenue","2016",1000);
		Cuenta cuenta15 = new Cuenta("Ingreso Neto","2016",600);
		Cuenta cuenta16 = new Cuenta("Dividendos","2016",400);
		Cuenta cuenta17 = new Cuenta("Capital Total","2016",1000);
		Cuenta cuenta18 = new Cuenta("Activo","2016",6000);
		Cuenta cuenta19 = new Cuenta("Pasivo","2016",2000);
		cuentasEmpresa2.add(cuenta11);
		cuentasEmpresa2.add(cuenta12);
		cuentasEmpresa2.add(cuenta13);
		cuentasEmpresa2.add(cuenta14);
		cuentasEmpresa2.add(cuenta15);
		cuentasEmpresa2.add(cuenta16);
		cuentasEmpresa2.add(cuenta17);
		cuentasEmpresa2.add(cuenta18);
		cuentasEmpresa2.add(cuenta19);
		Empresa empresa2 = new Empresa("Apple", cuentasEmpresa2);
		
		
		List<Cuenta> cuentasEmpresa3 = new ArrayList<>();
		Cuenta cuenta21 = new Cuenta("Free CashFlow","2016",100);
		Cuenta cuenta22 = new Cuenta("Revenue","2011",100);
		Cuenta cuenta23= new Cuenta("EBITDA","2016",300);
		Cuenta cuenta24= new Cuenta("Revenue","2016",500);
		Cuenta cuenta25 = new Cuenta("Ingreso Neto","2016",2000);
		Cuenta cuenta26 = new Cuenta("Dividendos","2016",400);
		Cuenta cuenta27 = new Cuenta("Capital Total","2016",1000);
		Cuenta cuenta28 = new Cuenta("Activo","2016",1000);
		Cuenta cuenta29 = new Cuenta("Pasivo","2016",800);
		cuentasEmpresa3.add(cuenta21);
		cuentasEmpresa3.add(cuenta22);
		cuentasEmpresa3.add(cuenta23);
		cuentasEmpresa3.add(cuenta24);
		cuentasEmpresa3.add(cuenta25);
		cuentasEmpresa3.add(cuenta26);
		cuentasEmpresa3.add(cuenta27);
		cuentasEmpresa3.add(cuenta28);
		cuentasEmpresa3.add(cuenta29);
		Empresa empresa3 = new Empresa("IBM", cuentasEmpresa3);

		
		List<Cuenta> cuentasEmpresa4 = new ArrayList<>();
		Cuenta cuenta31 = new Cuenta("Free CashFlow","2016",35);
		Cuenta cuenta32 = new Cuenta("Revenue","2008",850);
		Cuenta cuenta33 = new Cuenta("EBITDA","2016",324);
		Cuenta cuenta34 = new Cuenta("Revenue","2016",900);
		Cuenta cuenta35 = new Cuenta("Ingreso Neto","2016",900);
		Cuenta cuenta36 = new Cuenta("Dividendos","2016",400);
		Cuenta cuenta37 = new Cuenta("Capital Total","2016",1500);
		Cuenta cuenta38 = new Cuenta("Activo","2016",3000);
		Cuenta cuenta39 = new Cuenta("Pasivo","2016",800);
		
		cuentasEmpresa4.add(cuenta31);
		cuentasEmpresa4.add(cuenta32);
		cuentasEmpresa4.add(cuenta33);
		cuentasEmpresa4.add(cuenta34);
		cuentasEmpresa4.add(cuenta35);
		cuentasEmpresa4.add(cuenta36);
		cuentasEmpresa4.add(cuenta37);
		cuentasEmpresa4.add(cuenta38);
		cuentasEmpresa4.add(cuenta39);
		Empresa empresa4 = new Empresa("Oracle", cuentasEmpresa4);
		
		this.empresas.add(empresa1);
		this.empresas.add(empresa2);
		this.empresas.add(empresa3);
		this.empresas.add(empresa4);
	}
	
	public List<Empresa> getEmpresasMockeadas(){
		if(empresas.isEmpty()) {
			mockearListaEmpresas();
		}
		return this.empresas;
	}
	
	public List<Cuenta> getCuentasMockeadas(){
		List<Cuenta> cuentas = new ArrayList<>();
		
		this.empresas.stream().forEach(empresa -> cuentas.addAll(empresa.getCuentas()));
		
		return cuentas;
	}
}