package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.IndicadorRepetidoException;
import excepciones.NombreIndicadorNotFound;
import ui.vm.CargarBDViewModel;
import ui.vm.CargarIndicadoresViewModel;
import usuario.Indicador;

@SuppressWarnings("serial")
public class CargarBDWindow extends Dialog<CargarBDViewModel> {

	public CargarBDWindow(WindowOwner owner) {
		super(owner, new CargarBDViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Carga y consultar BD");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Escriba el nombre del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("nombreIndicador");
		
		new Label(form).setText("Escriba la formula del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("formulaIndicador");		
		
		new Label(form).setText("Puede agregar indicadores pre-existentes en su formula").setBackground(Color.green);
		
		new Label(mainPanel).setText("Indicadores Disponibles").setBackground(Color.ORANGE);
		
		Table<Indicador> tableIndicadores = new Table<Indicador>(mainPanel, Indicador.class);
		
		tableIndicadores.setNumberVisibleRows(6).setWidth(200);
		
		tableIndicadores.bindItemsToProperty("indicadores"); 
		
		new Column<Indicador>(tableIndicadores).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<Indicador>( tableIndicadores).setTitle("Formula").bindContentsToProperty("formula");
		
	}
	
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Cargar Indicador")
								.onClick(() -> {

										try {
											this.getModelObject().generarIndicador();
											this.getDelegate().close();
											PreguntaNuevoIndicadorWindow();
										} catch (NombreIndicadorNotFound e) {
											this.showError("No se ha ingresado un nombre para el indicador");
										} catch (FormulaIndicadorNotFound e) {
											this.showError("No se ha ingresado la formula del indicador");
										} catch (IndicadorRepetidoException e) {
											this.showError("El nombre ingresado ya existe");
										} catch (FormulaIndicadorNotValidException e) {
											this.showError("El indicador ingresado no contiene cuentas ni indicadores validos,revise la formula ingresada");
										}
								}).setWidth(200);
	
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
			this.getDelegate().close();
			MenuWindow();
		}).setWidth(200);
	}
	
	
	public void MenuWindow(){
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void PreguntaNuevoIndicadorWindow()  {
		Dialog<?> dialog = new PreguntaNuevoIndicadorWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	
	
}
