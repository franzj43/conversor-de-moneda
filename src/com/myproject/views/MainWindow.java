package com.myproject.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWindow {

	protected Shell shlPantallaprincipal;

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPantallaprincipal.open();
		shlPantallaprincipal.layout();
		while (!shlPantallaprincipal.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPantallaprincipal = new Shell();
		shlPantallaprincipal.setSize(300, 150);
		shlPantallaprincipal.setText("PantallaPrincipal");
		
		Combo comboBox1 = new Combo(shlPantallaprincipal, SWT.READ_ONLY);
		comboBox1.setItems(new String[] {"Conversor de moneda", "Conversor de temperatura"});
		comboBox1.setBounds(16, 43, 251, 23);
		comboBox1.select(0);
		
		Label lblSeleccioneUnaOpcin = new Label(shlPantallaprincipal, SWT.NONE);
		lblSeleccioneUnaOpcin.setBounds(79, 22, 126, 15);
		lblSeleccioneUnaOpcin.setText("Seleccione una opción");
		
		Button btnAceptar = new Button(shlPantallaprincipal, SWT.NONE);
		btnAceptar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Integer itemSeleccionado = comboBox1.getSelectionIndex();
				
				if(itemSeleccionado.equals(0)) {
					
					shlPantallaprincipal.setVisible(false);
					ConversorDeMonedaWindow conversorDeMonedaWindow = new ConversorDeMonedaWindow(shlPantallaprincipal);
					conversorDeMonedaWindow.open();
					
				} else {
					
					shlPantallaprincipal.setVisible(false);
					ConversorDeTemperaturaWindow conversorDeTemperaturaWindow = new ConversorDeTemperaturaWindow(shlPantallaprincipal);
					conversorDeTemperaturaWindow.open();
					
				}
				
			}
		});
		btnAceptar.setBounds(66, 72, 75, 25);
		btnAceptar.setText("Aceptar");
		
		Button btnCancelar = new Button(shlPantallaprincipal, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlPantallaprincipal.close();
				
			}
		});
		btnCancelar.setBounds(147, 72, 75, 25);
		btnCancelar.setText("Cancelar");

	}
}
