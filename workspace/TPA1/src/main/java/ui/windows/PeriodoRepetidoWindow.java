package ui.windows;


import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.PeriodoRepetidoViewModel;

public class PeriodoRepetidoWindow extends Dialog<PeriodoRepetidoViewModel> {

	public PeriodoRepetidoWindow  (WindowOwner owner) {
		super(owner, new PeriodoRepetidoViewModel());
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("Ingreso un periodo repetido, vuelva a ingresarlo");
		
	}
	protected void addActions(Panel actionsPanel){
	new Button(actionsPanel).setCaption("Volver")
	.onClick(() -> {
					
						this.getDelegate().close();
						AgregarPeriodoWindow();
	}).setWidth(200);
	}

	public void AgregarPeriodoWindow()  {
		Dialog<?> dialog = new AgregarPeriodoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
}
	