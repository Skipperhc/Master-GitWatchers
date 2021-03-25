package com.pp5.apiWeb.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Numbers
{
	public static double ArredondarCasasDecimais(double valor, int casas) {
	    if (casas < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(valor);
	    bd = bd.setScale(casas, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static boolean ehNumeroLong(String numeros) {
		try {
			Long.parseLong(numeros);
	            return true;
		} catch (NumberFormatException e) {	  
	            return false;
		}
	}
	
	public static boolean ehNumeroInt(String numeros) {
		try {
			Integer.parseInt(numeros);
	            return true;
		} catch (NumberFormatException e) {	  
	            return false;
		}
	}
	
	public static boolean ehNumeroFloat(String numeros) {
		try {
			Float.parseFloat(numeros);
	            return true;
		} catch (NumberFormatException e) {	  
	            return false;
		}
	}
	
	public static boolean ehNumeroDouble(String numeros) {
		try 
		{
			Double.parseDouble(numeros);
			return true;			
		} 
		catch (NumberFormatException e)
		{
			return false;
		}
	}
}
