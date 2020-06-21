package model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

	private String nomeCompleto;
	private String CPF;
	private String email;
	private String telefone;
	private String dataNascimento;
	private double salarioLiquido;
	public static double salario;

	public Cliente() {

	}

	public Cliente(String email, String telefone, double salarioLiquido) {
		super();
		this.email = email;
		this.telefone = telefone;
		this.salarioLiquido = salarioLiquido;

	}

	public Cliente(String nomeCompleto, String cPF, String email, String telefone, String dataNascimento,
			double salarioLiquido) {
		super();
		this.nomeCompleto = nomeCompleto;
		CPF = cPF;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.salarioLiquido = salarioLiquido;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

}
