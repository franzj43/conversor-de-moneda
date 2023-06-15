package com.myproject.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.myproject.controller.ConversorDeMonedaWindowController;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class ConversorDeMonedaWindow {

	protected Shell shellPantallaConversion;
	private ConversorDeMonedaWindowController conversorDeMonedaWindowController;
	private Shell mainWindow;
	private Text textMoneda1;
	private Text textMoneda2;
	
	private String[] opciones = {"GTQ", "USD", "EUR", "GBP", "JPY", "KRW"};
	private String[] simbolos = {"Q.", "$.", "€.", "£.", "¥.", "₩."};

	public ConversorDeMonedaWindow(Shell shlPantallaprincipal) {
		this.mainWindow = shlPantallaprincipal;
		this.conversorDeMonedaWindowController = new ConversorDeMonedaWindowController();
		
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellPantallaConversion.open();
		shellPantallaConversion.layout();
		while (!shellPantallaConversion.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellPantallaConversion = new Shell();
		shellPantallaConversion.setSize(290, 328);
		shellPantallaConversion.setText("SWT Application");
		
		Label lblMoneda1 = new Label(shellPantallaConversion, SWT.NONE);
		lblMoneda1.setBounds(10, 10, 55, 15);
		lblMoneda1.setText("Moneda 1");
		
		List listaMoneda1 = new List(shellPantallaConversion, SWT.BORDER);
		listaMoneda1.setItems(opciones);
		listaMoneda1.setBounds(10, 31, 108, 139);
		listaMoneda1.setSelection(0);
		
		Label lblMoneda2 = new Label(shellPantallaConversion, SWT.NONE);
		lblMoneda2.setBounds(148, 10, 55, 15);
		lblMoneda2.setText("Moneda 2");
		
		List listaMoneda2 = new List(shellPantallaConversion, SWT.BORDER);
		listaMoneda2.setItems(opciones);
		listaMoneda2.setBounds(148, 31, 108, 139);
		listaMoneda2.setSelection(0);
		
		textMoneda1 = new Text(shellPantallaConversion, SWT.BORDER);
		textMoneda1.setBounds(34, 193, 84, 21);
		
		Label lblSimboloMoneda1 = new Label(shellPantallaConversion, SWT.NONE);
		lblSimboloMoneda1.setBounds(10, 196, 18, 15);
		
		Label lblSimboloMoneda2 = new Label(shellPantallaConversion, SWT.NONE);
		lblSimboloMoneda2.setBounds(148, 196, 18, 15);
		
		textMoneda2 = new Text(shellPantallaConversion, SWT.BORDER);
		textMoneda2.setEnabled(false);
		textMoneda2.setEditable(false);
		textMoneda2.setBounds(172, 193, 84, 21);
		
		Label lblTotal = new Label(shellPantallaConversion, SWT.NONE);
		lblTotal.setAlignment(SWT.CENTER);
		lblTotal.setBounds(55, 232, 170, 15);
		lblTotal.setText("Moneda 1 equivale a Moneda 2");
		lblTotal.setVisible(false);
		
		Button btnRegresar = new Button(shellPantallaConversion, SWT.NONE);
		btnRegresar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				mainWindow.setVisible(true);
				shellPantallaConversion.dispose();
				
			}
		});
		btnRegresar.setBounds(99, 253, 75, 25);
		btnRegresar.setText("Regresar");
		
		textMoneda1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				if(textMoneda1.getText() != "") {
				
					String moneda1 = listaMoneda1.getItem(listaMoneda1.getSelectionIndex());
					String moneda2 = listaMoneda2.getItem(listaMoneda2.getSelectionIndex());
					
					Double conversion1  = conversorDeMonedaWindowController.getCambioMoneda(moneda1);
					Double conversion2  = conversorDeMonedaWindowController.getCambioMoneda(moneda2);
					
					Double total = Double.parseDouble(textMoneda1.getText()) * (conversion2/conversion1);
					
					textMoneda2.setText(String.format("%.2f", total));
					
					lblTotal.setText(textMoneda1.getText() + " " + moneda1 + " equivale a: " + textMoneda2.getText() + " " + moneda2);
					
					if(!lblTotal.isVisible()) {
						lblTotal.setVisible(true);
					}
				}
			}
		});
		
		textMoneda1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				
				String currentText = ((Text) arg0.getSource()).getText();
                String newText = currentText.substring(0, arg0.start) + arg0.text + currentText.substring(arg0.end);
                boolean isValid = newText.matches("^\\d*\\.?\\d{0,2}$");
                arg0.doit = isValid;
				
			}
		});
		
		listaMoneda1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Integer monedaSeleccionada1 = listaMoneda1.getSelectionIndex();
				lblSimboloMoneda1.setText(simbolos[monedaSeleccionada1]);
				
			}
		});
		
		listaMoneda2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Integer monedaSeleccionada2 = listaMoneda2.getSelectionIndex();
				lblSimboloMoneda2.setText(simbolos[monedaSeleccionada2]);
				
			}
		});

	}
}
