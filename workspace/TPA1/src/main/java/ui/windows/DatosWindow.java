package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.DatosViewModel;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.IndicadorCustom;

public class DatosWindow extends Dialog<DatosViewModel>{
	public DatosWindow(WindowOwner parent) throws IOException {
		super(parent, new DatosViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consulta de Indicadores");
		
		Panel Panel = new Panel(mainPanel);
		mainPanel.setLayout(new HorizontalLayout());
		
		
		new Label(Panel).setText("Seleccione una empresa ").setBackground(Color.ORANGE);
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(Panel).allowNull(true);
		selectorEmpresa.setWidth(100);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresa");
		

		new Label(Panel).setText("Seleccione un Periodo ").setBackground(Color.ORANGE);
		Selector<String> selectorPeriodo = new Selector<String>(Panel).allowNull(true);
		selectorPeriodo.setWidth(100);
		selectorPeriodo.bindItemsToProperty("periodos");
		selectorPeriodo.bindValueToProperty("periodo");
		
		new Label(Panel).setText("Cuentas").setBackground(Color.ORANGE);
		
		Table<Cuenta> tableCuentas = new Table<Cuenta>(Panel, Cuenta.class);
		
		
		tableCuentas.bindItemsToProperty("cuentasFiltradas");
		new Column<Cuenta>(tableCuentas).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<Cuenta>(tableCuentas).setTitle("Valor").bindContentsToProperty("valor");
		new Column<Cuenta>(tableCuentas).setTitle("Periodo").bindContentsToProperty("periodo");
		
}

	
	
	protected void addActions(Panel actionsPanel){
		Panel Panel2 = new Panel(actionsPanel);
		Panel2.setLayout(new VerticalLayout());
		
		new Label(Panel2).setText("Indicadores Disponibles").setBackground(Color.ORANGE);
		
		Table<IndicadorCustom> tableIndicadores = new Table<IndicadorCustom>(Panel2, IndicadorCustom.class);
		tableIndicadores.bindItemsToProperty("indicadores"); 
		
		new Column<IndicadorCustom>(tableIndicadores).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<IndicadorCustom>( tableIndicadores).setTitle("Formula").bindContentsToProperty("formula");
		
		new Label(Panel2).setText("Seleccione el indicador a evaluar ").setBackground(Color.ORANGE);
		Selector<Cuenta> selectorIndicadorAEvaluar = new Selector<Cuenta>(Panel2).allowNull(true);
		selectorIndicadorAEvaluar.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Cuenta.class, "nombre"));
		selectorIndicadorAEvaluar.setWidth(100);
		selectorIndicadorAEvaluar.bindValueToProperty("indicadorSeleccionado");
		new Label(Panel2).setText("El resultado es").setBackground(Color.GREEN);
		new Label(Panel2).setBackground(Color.GREEN).bindValueToProperty("indicadorSeleccionado.calcular");
		
		new Button(Panel2).setCaption("Volver al Menu Principal")
		.onClick(() -> {
			try{
				this.getDelegate().close();
				MenuWindow();
			}catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargaIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
}
