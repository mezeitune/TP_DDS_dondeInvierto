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

import ui.vm.DatosCuentasViewModel;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;

public class DatosCuentasWindow extends Dialog<DatosCuentasViewModel>{
	public DatosCuentasWindow(WindowOwner parent)  {
		super(parent, new DatosCuentasViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consulta de Indicadores");
		
		
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		
		
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
		
		
		/*****************************************************textbox*************************/
		new Label(Panel).setHeight(20);
		new Label(Panel).setText("En el cuadro de la derecha puede escribir una formula a evaluar").setBackground(Color.ORANGE);
		new Label(Panel).setText("Es posible la utilizaci�n de indicadores y cuentas").setBackground(Color.ORANGE);
		/*****************************************************textbox*************************/
		
		
		new Button(Panel).setCaption("Volver al Menu Principal")
		.onClick(() -> {
			try{
				this.getDelegate().close();
				MenuWindow();
			}catch (IOException e) {
				e.printStackTrace();
			}
		});
}
	
	protected void addActions(Panel actionsPanel){
		
		
		Panel Panel2 = new Panel(actionsPanel);
		Panel2.setLayout(new VerticalLayout());
		
		new Label(Panel2).setText("Indicadores Disponibles").setBackground(Color.ORANGE);
		
		Table<Indicador> tableIndicadores = new Table<Indicador>(Panel2, Indicador.class);
		tableIndicadores.bindItemsToProperty("indicadores"); 
		
		new Column<Indicador>(tableIndicadores).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<Indicador>( tableIndicadores).setTitle("Formula").bindContentsToProperty("formula");
		
		
		Selector<Cuenta> selectorIndicadorAEvaluar = new Selector<Cuenta>(Panel2).allowNull(true);
		selectorIndicadorAEvaluar.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Cuenta.class, "nombre"));
		selectorIndicadorAEvaluar.setWidth(100);
		selectorIndicadorAEvaluar.bindValueToProperty("indicadorSeleccionado");
		
		new Label(Panel2).setText("Seleccionar un indicador para calcular").setBackground(Color.GREEN);
		
		new Label(Panel2).setText("Resultado de indicador Indicado").setBackground(Color.GREEN);
		new Label(Panel2).setBackground(Color.GREEN).bindValueToProperty("indicadorSeleccionado.calcular");
		
		/*****************************************************textbox*************************/
		new Label(Panel2).setHeight(25);
		new TextBox(Panel2).bindValueToProperty("indicadorAEvaluar");
		new Label(Panel2).setText("El resultado es").setBackground(Color.GREEN);
		new Label(Panel2).setBackground(Color.GREEN).bindValueToProperty("calculo");
		/*****************************************************textbox*************************/
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
