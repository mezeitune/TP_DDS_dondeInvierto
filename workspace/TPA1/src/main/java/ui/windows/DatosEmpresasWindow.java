package ui.windows;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import ui.vm.DatosEmpresasViewModel;
import usuario.*;

@SuppressWarnings("serial")
public class DatosEmpresasWindow extends Dialog<DatosEmpresasViewModel> {
	
	
	
	public DatosEmpresasWindow(WindowOwner parent) {
		super(parent, new DatosEmpresasViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(1));
		
		new Label(form).setText("Empresas");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresa");
		
		
		
		new Label(form).setText("Empresa Seleccionada");
		
		new Label(form).bindValueToProperty("empresa.nombre");//Bindea el nombre de la cuenta, sooo cambiar variable para mostrar los otros, si ahce falta
		
		//onSelection(Action);
		
		Table<Cuenta> table = new Table<Cuenta>(mainPanel, Cuenta.class);
		table.setHeight(0);
		table.setWidth(450);
		table.bindItemsToProperty("cuentas");
		table.bindValueToProperty("cuenta");
		
		new Column<Cuenta>(table).setTitle("Nombre").setFixedSize(250).bindContentsToProperty("nombre");/*Falta setear en el view model la cuenta*/
		new Column<Cuenta>(table).setTitle("Valor").setFixedSize(250).bindContentsToProperty("valor");
		new Column<Cuenta>(table).setTitle("Periodo").setFixedSize(250).bindContentsToProperty("periodo");
		
		
	}
	
	
	
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}
	

}		