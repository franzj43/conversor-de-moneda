package com.myproject.controller;

public class ConversorDeTemperaturaWindowController {
	
	public Double convertirTemperatura(String temp1, String temp2, Double valor) {
		
		Double valor2 = convertirACelsius(temp1, valor);
		
		switch(temp2) {
		
		case "°C":
			return valor2 * 1.00;
		case "°F":
			return (valor2 * 9/5) + 32;
		case "K":
			return (valor2 + 273.15);
		case "R":
			return (valor2 * 9/5 + 491.67);
		default:
			return 1.00;
		
		}
		
	}
	
	public Double convertirACelsius(String temp1, Double valor) {
		
		switch(temp1) {
		
		case "°C":
			return valor * 1.00;
		case "°F":
			return (valor - 32) * 5/9;
		case "K":
			return valor - 273.15;
		case "R":
			return (valor - 491.67) * 5/9;
		default:
			return 1.00;
		}
		
	}

}
