package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import parser.ParserFormulaToIndicador;
import ui.vm.CargarIndicadoresViewModel;
import usuario.Cuenta;
import usuario.Indicador;

@SuppressWarnings("serial")
public class CargarIndicadoresWindow extends Dialog<CargarIndicadoresViewModel> {

	public CargarIndicadoresWindow(WindowOwner owner) {
		super(owner, new CargarIndicadoresViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Carga de indicadores");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Escriba el nombre del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(200).bindValueToProperty("nombreIndicador");
		
		new Label(form).setText("Escriba la formula del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(200).bindValueToProperty("formulaIndicador");		
		
	}
	
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Cargar Indicador")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													CargarIndicadoresViewModel.generarIndicador();//Actualiza archivo sin cerrar programa
													new ParserFormulaToIndicador();//Muestra Indicadores en tabla
													CargaExitosaWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								});
	
		new Button(actionsPanel).setCaption("Cancelar")
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
	public void CargaExitosaWindow() throws IOException {
		Dialog<?> dialog = new CargaExitosaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	
}
