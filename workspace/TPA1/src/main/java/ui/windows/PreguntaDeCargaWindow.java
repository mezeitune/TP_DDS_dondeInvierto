package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.PreguntaDeCargaViewModel;

//por ahora no se usa porque al cargarlo, aunque pregunte o no lo carga igual, me parece mejor que directamente informa la carga exitosa del indicador
@SuppressWarnings("serial")
public class PreguntaDeCargaWindow extends Dialog<PreguntaDeCargaViewModel> {

	public PreguntaDeCargaWindow(WindowOwner owner) {
		super(owner, new PreguntaDeCargaViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("�Desea agregar un nuevo Indicador?");
				
	}
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Si")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													CargaExitosaWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								})
								.setWidth(130);
	
		new Button(actionsPanel).setCaption("No, volver al Menu Principal")
									.onClick(() -> {
													try{
														this.getDelegate().close();
														MenuWindow();
													}catch (IOException e) {
														e.printStackTrace();
													}
									});
		new Button(actionsPanel).setCaption("No, volver a Cargar Indicadores")
		.onClick(() -> {
						try{
							this.getDelegate().close();
							CargarIndicadoresWindow();
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
	public void CargarIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargaExitosaWindow() throws IOException {
		Dialog<?> dialog = new CargaExitosaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
}
