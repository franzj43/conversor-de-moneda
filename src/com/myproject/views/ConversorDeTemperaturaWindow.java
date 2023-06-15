package com.myproject.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.myproject.controller.ConversorDeTemperaturaWindowController;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class ConversorDeTemperaturaWindow {

	protected Shell shellConversorDeTemperatura;
	private Shell shlPantallaprincipal;
	private ConversorDeTemperaturaWindowController conversorDeTemperaturaWindowController;
	private String[] opciones = {"°C", "°F", "K", "R"};
	private Text textTemperatura1;
	private Text textTemperatura2;

	public ConversorDeTemperaturaWindow(Shell shlPantallaprincipal) {
		
		this.shlPantallaprincipal = shlPantallaprincipal;
		this.conversorDeTemperaturaWindowController = new ConversorDeTemperaturaWindowController();
		
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellConversorDeTemperatura.open();
		shellConversorDeTemperatura.layout();
		while (!shellConversorDeTemperatura.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellConversorDeTemperatura = new Shell();
		shellConversorDeTemperatura.setSize(290, 328);
		shellConversorDeTemperatura.setText("SWT Application");
		
		Label lblTemperatura1 = new Label(shellConversorDeTemperatura, SWT.NONE);
		lblTemperatura1.setBounds(10, 10, 82, 15);
		lblTemperatura1.setText("Temperatura 1");
		
		Label lblTemperatura2 = new Label(shellConversorDeTemperatura, SWT.NONE);
		lblTemperatura2.setText("Temperatura 2");
		lblTemperatura2.setBounds(145, 10, 82, 15);
		
		List listaTemperatura1 = new List(shellConversorDeTemperatura, SWT.BORDER);
		listaTemperatura1.setItems(opciones);
		listaTemperatura1.setBounds(10, 31, 109, 139);
		listaTemperatura1.select(0);
		
		List listaTemperatura2 = new List(shellConversorDeTemperatura, SWT.BORDER);
		listaTemperatura2.setItems(opciones);
		listaTemperatura2.setBounds(145, 31, 109, 139);
		listaTemperatura2.select(0);
		
		Label labelSimbolo1 = new Label(shellConversorDeTemperatura, SWT.NONE);
		labelSimbolo1.setBounds(10, 195, 18, 15);
		
		textTemperatura1 = new Text(shellConversorDeTemperatura, SWT.BORDER);
		textTemperatura1.setBounds(35, 192, 84, 21);
		
		Label labelSimbolo2 = new Label(shellConversorDeTemperatura, SWT.NONE);
		labelSimbolo2.setBounds(145, 195, 18, 15);
		
		textTemperatura2 = new Text(shellConversorDeTemperatura, SWT.BORDER);
		textTemperatura2.setEnabled(false);
		textTemperatura2.setEditable(false);
		textTemperatura2.setBounds(169, 192, 84, 21);
		
		Button btnRegresar = new Button(shellConversorDeTemperatura, SWT.NONE);
		btnRegresar.setBounds(99, 253, 75, 25);
		btnRegresar.setText("Regresar");
		
		Label labelTemperaturaTotal = new Label(shellConversorDeTemperatura, SWT.NONE);
		labelTemperaturaTotal.setAlignment(SWT.CENTER);
		labelTemperaturaTotal.setBounds(15, 229, 244, 15);
		labelTemperaturaTotal.setText("Temperatura 1 y temperatura 2");
		labelTemperaturaTotal.setVisible(false);
		
		textTemperatura1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				if(textTemperatura1.getText() != "") {
				
					Integer index1 = listaTemperatura1.getSelectionIndex();
					Integer index2 = listaTemperatura2.getSelectionIndex();
									
					Double valor1 = Double.parseDouble(textTemperatura1.getText());
					
					Double valor2 = conversorDeTemperaturaWindowController.convertirTemperatura(opciones[index1], opciones[index2], valor1);
					
					textTemperatura2.setText(String.format("%.2f", valor2));
					
					labelTemperaturaTotal.setText(textTemperatura1.getText() + " " + opciones[index1] + " equivale a: " + 
					textTemperatura2.getText() + " " + opciones[index2]);
					
					if(!labelTemperaturaTotal.isVisible()) {
						labelTemperaturaTotal.setVisible(true);
					}
				
				}
				
			}
		});
		
		textTemperatura1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				
				String currentText = ((Text) arg0.getSource()).getText();
                String newText = currentText.substring(0, arg0.start) + arg0.text + currentText.substring(arg0.end);
                boolean isValid = newText.matches("^\\d*\\.?\\d{0,2}$");
                arg0.doit = isValid;
				
			}
		});
		
		btnRegresar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlPantallaprincipal.setVisible(true);
				shellConversorDeTemperatura.dispose();
				
			}
		});
		
		listaTemperatura1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Integer index = listaTemperatura1.getSelectionIndex();
				labelSimbolo1.setText(opciones[index]);
				
			}
		});
		
		listaTemperatura2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Integer index = listaTemperatura2.getSelectionIndex();
				labelSimbolo2.setText(opciones[index]);
				
			}
		});

	}
}
