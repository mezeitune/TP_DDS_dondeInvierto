package ui.windows;

import java.awt.Color;
import java.awt.Desktop.Action;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;
import org.uqbar.lacar.ui.model.builder.LinkBuilder;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.Selector;

import ui.vm.DatosEmpresasViewModel;
import usuario.*;

public class DatosEmpresasWindow extends Dialog<DatosEmpresasViewModel> {
	
	
	
	public DatosEmpresasWindow(WindowOwner parent) {
		super(parent, new DatosEmpresasViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(1));
		
		new Label(form).setText("Empresas");
		Selector<Empresa> selectorPrenda = new Selector<Empresa>(form).allowNull(true);
		selectorPrenda.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorPrenda.bindValueToProperty("empresaSeleccionada");
		
		new Label(form).setText("Empresa Seleccionada");
		new Label(form).bindValueToProperty("empresaSeleccionada.nombre");
		
		//onSelection(Action);
		new Label(form).setText("Cuentas");
		
		new Label(form).setText("Nombre: ");
		//new Label(form).bindValueToProperty("cuentaSeleccionada.nombre");
		
		new Label(form).setText("Periodo: ");
		//new Label(form).bindValueToProperty("cuentaSeleccionada.periodo");
		
		new Label(form).setText("Valor: ");
		//new Label(form).bindValueToProperty("cuentaSeleccionada.valor");
	
	}
	
	
	
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}
	

}		