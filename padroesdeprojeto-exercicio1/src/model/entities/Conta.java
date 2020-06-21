package model.entities;

import java.io.Serializable;
import java.util.Random;

public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numeroConta;
	private Integer digitoConta;
	private Double limiteCheque;
	private Integer idStatusConta;
	private Integer idTipoConta;
	private Integer idCliente;

	public Conta() {

	}

	public Conta(Integer numeroConta, Integer digitoConta, Double limiteCheque, Integer idStatusConta,
			Integer idTipoConta, Integer idCliente) {
		this.numeroConta = numeroConta;
		this.digitoConta = digitoConta;
		this.limiteCheque = limiteCheque;
		this.idStatusConta = idStatusConta;
		this.idTipoConta = idTipoConta;
		this.idCliente = idCliente;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Integer getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(Integer digitoConta) {
		this.digitoConta = digitoConta;
	}

	public Double getLimiteCheque() {
		return limiteCheque;
	}

	public void setLimiteCheque(Double limiteCheque) {
		this.limiteCheque = limiteCheque;
	}

	public Integer getIdStatusConta() {
		return idStatusConta;
	}

	public void setIdStatusConta(Integer idStatusConta) {
		this.idStatusConta = idStatusConta;
	}

	public Integer getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(Integer idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public void geradorNumeroConta() {

		Random random = new Random();

		String retorno = "";

		for (int i = 0; i < 6; i++) {

			Integer conta = random.nextInt(9);
			retorno = retorno + conta;

		}

		Integer resultado = Integer.parseInt(retorno);

		setNumeroConta(resultado);

	}

	public void digitoConta() {

		Integer numeroConta = getNumeroConta();

		String converterParaString = numeroConta.toString();

		String ultimoNumero = converterParaString.substring(5, 6);

		Integer digitoEmInt = Integer.parseInt(ultimoNumero);

		if (digitoEmInt == 1) {

			setDigitoConta(9);

		} else if (digitoEmInt == 0) {
			
			setDigitoConta(8);
			
		} else {

			Integer digitoConta = digitoEmInt - 2;

			setDigitoConta(digitoConta);

		}

	}

}
