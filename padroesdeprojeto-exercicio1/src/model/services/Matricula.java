package model.services;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Matricula {

	public static String anoParaMatricula() {

		Calendar calendario = GregorianCalendar.getInstance();
		int anoAtual = calendario.get(Calendar.YEAR);

		String converterParaString = Integer.toString(anoAtual);

		String doisDigitosAno = converterParaString.substring(2,3);

		return doisDigitosAno;

	}

	public static String mesParaMatricula() {

		Calendar calendario = GregorianCalendar.getInstance();
		int mesAtual = calendario.get(Calendar.MONTH) + 1;

		String mesEmString = Integer.toString(mesAtual);

		return mesEmString;

	}

	public static String gerarParteInicialMatricula() {

		String matriculaInicial = mesParaMatricula() + anoParaMatricula();

		return matriculaInicial;

	}

}
