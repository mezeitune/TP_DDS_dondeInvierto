package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.IngresoIncorrectoViewModel;


public class IngresoIncorrectoWindow  extends Dialog<IngresoIncorrectoViewModel> {

	private String mensaje;
	
	public IngresoIncorrectoWindow  (WindowOwner owner,String mensaje) {
		super(owner, new IngresoIncorrectoViewModel());
		this.mensaje = mensaje;
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText(mensaje).setBackground(Color.RED);
		new Label(mainPanel).setText("Vuelva a intentarlo");
		
	}
	protected void addActions(Panel actionsPanel){
	new Button(actionsPanel).setCaption("Volver")
	.onClick(() -> {
						this.getDelegate().close();
						VolverVentanaPadre();
	}).setWidth(200);
	}

	/*TODO: Averiguar como volver a la ventana que invoco esta ventana*/
	public void VolverVentanaPadre()  {
		Dialog<?> dialog = this;
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
}