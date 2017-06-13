package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.omg.CORBA.UserException;
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

import parserFormulaInidicador.ParserFormulaToIndicador;
import ui.vm.CargarIndicadoresViewModel;
import usuario.Cuenta;
import usuario.Indicador;
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
													CargarIndicadoresViewModel.generarIndicador();
												} catch (IOException e2) {
													// TODO Auto-generated catch block
													e2.printStackTrace();
												}//Actualiza archivo sin cerrar programa
												try {
													new ParserFormulaToIndicador();
												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}//Muestra Indicadores en tabla											
												try{
												
													if(CargarIndicadoresViewModel.getCodigoDeError() == 1){
														this.getDelegate().close();
														IndicadorVacioWindow();
													}else if(CargarIndicadoresViewModel.getCodigoDeError() == 2){
														this.getDelegate().close();
														IndicadorErroneoWindow();
													}else if (CargarIndicadoresViewModel.getCodigoDeError() == 3){
														
														this.getDelegate().close();
														IndicadorRepetidoWindow();
													} else {
														this.getDelegate().close();
														PreguntaNuevoIndicadorWindow();
													}
												}catch (IOException e) {
													e.printStackTrace();
												}
								}).setWidth(200);
	
		new Button(actionsPanel).setCaption("Cancelar")
									.onClick(() -> {
													try{
														this.getDelegate().close();
														MenuWindow();
													}catch (IOException e) {
														e.printStackTrace();
													}
									}).setWidth(200);
	}
	
	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void PreguntaNuevoIndicadorWindow() throws IOException {
		Dialog<?> dialog = new PreguntaNuevoIndicadorWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void IndicadorRepetidoWindow() throws IOException {
		Dialog<?> dialog = new IndicadorRepetidoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void IndicadorVacioWindow() throws IOException {
		Dialog<?> dialog = new IndicadorVacioWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	
	public void IndicadorErroneoWindow() throws IOException {
		Dialog<?> dialog = new IndicadorErroneoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	
}
