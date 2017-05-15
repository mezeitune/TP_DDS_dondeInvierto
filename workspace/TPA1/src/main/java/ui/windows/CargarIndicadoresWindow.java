package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargarIndicadoresViewModel;

@SuppressWarnings("serial")
public class CargarIndicadoresWindow extends Dialog<CargarIndicadoresViewModel> {

	public CargarIndicadoresWindow(WindowOwner owner) {
		super(owner, new CargarIndicadoresViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Carga de indicadores");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Escribir en el siguiente recuadro el nombre del Indicador").setBackground(Color.orange);
		
		new TextBox(form).bindValueToProperty("nombreIndicador");
		
		new Label(form).setText("Escribir en el siguiente recuadro la formula del Indicador").setBackground(Color.orange);
		
		new TextBox(form).bindValueToProperty("formulaIndicador");
		
		
	}
	
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Cargar Indicador")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													PreguntaDeCargaWindow();
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
	public void PreguntaDeCargaWindow() throws IOException {
		//Dialog<?> dialog = new PreguntaDeCargaWindow(this);
		//dialog.open();
		//dialog.onAccept(() -> {});
		
		CargarIndicadoresViewModel.generarIndicador();
	}
	
}
