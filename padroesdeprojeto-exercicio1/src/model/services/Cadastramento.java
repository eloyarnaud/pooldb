package model.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.dao.implementacao.AtendenteDaoJDBC;
import model.dao.implementacao.ClienteDaoJDBC;
import model.dao.implementacao.ContaDaoJDBC;
import model.dao.implementacao.GerenteDaoJDBC;
import model.dao.implementacao.SetorDaoJDBC;
import model.entities.Atendente;
import model.entities.Cliente;
import model.entities.Conta;
import model.entities.Gerente;

public class Cadastramento {

	Scanner sc = new Scanner(System.in);

	Cliente cliente = new Cliente();
	Atendente atendente = new Atendente();
	Gerente gerente = new Gerente();
	Conta conta = new Conta();

	SetorDaoJDBC setorDaoJDBC = new SetorDaoJDBC();
	ClienteDaoJDBC clienteDaoJDBC = new ClienteDaoJDBC();
	GerenteDaoJDBC gerenteDaoJDBC = new GerenteDaoJDBC();
	AtendenteDaoJDBC atendenteDaoJDBC = new AtendenteDaoJDBC();
	ContaDaoJDBC contaDaoJDBC = new ContaDaoJDBC();

	ValidarEmail validarEmail = new ValidarEmail();

	private int result;

	public Cadastramento() {

	}

	public Cadastramento(int result) {
		super();
		this.result = result;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void cadastrarAtendente() {

		try {

			JOptionPane.showMessageDialog(null, "FORNEÇA OS DADOS CORRETAMENTE ", "DADOS DO ATENDENTE",
					JOptionPane.WARNING_MESSAGE);

			String nomeCompleto = JOptionPane.showInputDialog("NOME COMPLETO").toUpperCase();

			String matricula = atendente.gerarMatriculaAtendente().toUpperCase();

			String email = JOptionPane.showInputDialog("EMAIL").toUpperCase();

			ValidarEmail validarEmail = new ValidarEmail(email);
			boolean validacao = validarEmail.validarEmail();

			if (validacao == false) {

				JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDADO", "ERROR", JOptionPane.ERROR_MESSAGE);

			} else {

				String telefone = JOptionPane.showInputDialog("TELEFONE COM (DD)").toUpperCase();
				String telefoneFomartado = FormatarStrings
						.formatString(telefone.replaceAll(" ", "").replaceAll("()", ""), "(##) #####-####")
						.replaceAll("-", "");

				int matriculaGerente = gerenteDaoJDBC.idGerente();

				String matriculaGerenteString = gerenteDaoJDBC.voltarMatriculaAtravesDoId(matriculaGerente);

				int resposta = JOptionPane.showConfirmDialog(null,
						"NOME COMPLETO: " + nomeCompleto + "\nMATRICULA: " + matricula + "\nEMAIL: " + email
								+ "\nTELEFONE: " + telefoneFomartado + "\nGERENTE RESPONSÁVEL: "
								+ matriculaGerenteString + "\n\nTEM CERTEZA QUE DESEJA CADASTRAR ESSE ATENDENTE? ",
						"CONFIRMAÇÃO DE CADASTRADO", JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					atendente = new Atendente(nomeCompleto, matricula, email, telefoneFomartado, matriculaGerente);
					atendenteDaoJDBC.inserir(atendente);
				} else {
					JOptionPane.showMessageDialog(null, "ATENDENTE NÃO CADASTRADO", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

		} catch (InputMismatchException e) {
			e.getMessage();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "DIGITE APENAS NÚMEROS", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "CPF INVÁLIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "CAMPO NÃO PODE SER NULO", "ERROR", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void cadastrarGerente() {

		try {

			JOptionPane.showMessageDialog(null, "FORNEÇA OS DADOS CORRETAMENTE ", "DADOS DO GERENTE",
					JOptionPane.WARNING_MESSAGE);

			String nomeCompleto = JOptionPane.showInputDialog("NOME COMPLETO").toUpperCase();

			String matricula = gerente.gerarMatriculaGerente().toUpperCase();

			String email = JOptionPane.showInputDialog("EMAIL").toUpperCase();

			ValidarEmail validarEmail = new ValidarEmail(email);
			boolean validacao = validarEmail.validarEmail();

			if (validacao == false) {

				JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDADO", "ERROR", JOptionPane.ERROR_MESSAGE);

			} else {

				String telefone = JOptionPane.showInputDialog("TELEFONE COM (DD)").toUpperCase();
				String telefoneFomartado = FormatarStrings
						.formatString(telefone.replaceAll(" ", "").replaceAll("() -", ""), "(##) #####-####");

				int setorResponsavel = setorDaoJDBC.idSetor();

				String setor = null;

				if (setorResponsavel == 1) {
					setor = "ADMINISTRATIVO";
				} else if (setorResponsavel == 2) {
					setor = "COMERCIAL";
				} else if (setorResponsavel == 3) {
					setor = "FINANCEIRO";
				} else if (setorResponsavel == 4) {
					setor = "OPERACIONAL";
				} else if (setorResponsavel == 5) {
					setor = "RECURSOS HUMANOS";
				}

				int resposta = JOptionPane.showConfirmDialog(null,
						"NOME COMPLETO: " + nomeCompleto + "\nMATRICULA: " + matricula + "\nEMAIL: " + email
								+ "\nTELEFONE: " + telefoneFomartado + "\nSETOR RESPONSÁVEL: " + setor
								+ "\n\nTEM CERTEZA QUE DESEJA CADASTRAR ESSE GERENTE? ",
						"CONFIRMAÇÃO DE CADASTRADO", JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					gerente = new Gerente(nomeCompleto, matricula, email, telefoneFomartado, setorResponsavel);
					gerenteDaoJDBC.inserir(gerente);
				} else {
					JOptionPane.showMessageDialog(null, "CLIENTE NÃO CADASTRADO", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		} catch (InputMismatchException e) {
			e.getMessage();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "DIGITE APENAS NÚMEROS", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "CPF INVÁLIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "CAMPO NÃO PODE SER NULO", "ERROR", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void cadastrarCliente() {

		try {

			JOptionPane.showMessageDialog(null, "FORNEÇA OS DADOS CORRETAMENTE ", "DADOS DO CLIENTE",
					JOptionPane.WARNING_MESSAGE);

			String nomeCompleto = JOptionPane.showInputDialog("NOME COMPLETO").toUpperCase();

			String CPF = JOptionPane.showInputDialog("CPF").replaceAll("-", "").replaceAll("\\.", "");
			CPF = FormatarStrings.formatarCPF(CPF);

			String email = JOptionPane.showInputDialog("EMAIL").toUpperCase();

			ValidarEmail validarEmail = new ValidarEmail(email);
			boolean validacao = validarEmail.validarEmail();

			if (validacao == false) {

				JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDADO", "ERROR", JOptionPane.ERROR_MESSAGE);

			} else {

				String telefone = JOptionPane.showInputDialog("TELEFONE COM (DD)").toUpperCase();
				String telefoneFomartado = FormatarStrings.formatString(telefone.replaceAll(" ", ""),
						"(##) #####-####");

				String dataNascimento = JOptionPane.showInputDialog("DATA DE NASCIMENTO (DD/MM/YYYY)").toUpperCase();
				String dataNascimentoFormatado = dataNascimento.replaceAll("/", "");
				dataNascimentoFormatado = FormatarStrings.formatarData(dataNascimentoFormatado);

				Double salarioLiquido = Double.parseDouble(JOptionPane.showInputDialog("SALÁRIO LÍQUIDO MENSAL"));

				int resposta = JOptionPane.showConfirmDialog(null,
						"NOME COMPLETO: " + nomeCompleto + "\n CPF: " + CPF + "\n EMAIL: " + email + "\n TELEFONE: "
								+ telefoneFomartado + "\n DATA DE NASCIMENTO: " + dataNascimentoFormatado
								+ "\n SALÁRIO LÍQUIDO: " + salarioLiquido
								+ "\n \n TEM CERTEZA QUE DESEJA CADASTRAR ESSE CLIENTE? ",
						"CONFIRMAÇÃO DE CADASTRADO", JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					cliente = new Cliente(nomeCompleto, CPF, email, telefoneFomartado, dataNascimentoFormatado,
							salarioLiquido);
					Cliente.salario = salarioLiquido;
					clienteDaoJDBC.inserir(cliente);
				} else {
					JOptionPane.showMessageDialog(null, "CLIENTE NÃO CADASTRADO", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

			setResult(clienteDaoJDBC.pegarId(CPF));
			cadastrarConta();

		} catch (InputMismatchException e) {
			e.getMessage();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "DIGITE APENAS NÚMEROS", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "CPF INVÁLIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "CAMPO NÃO PODE SER NULO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cadastrarConta() {

		conta.geradorNumeroConta();
		conta.digitoConta();

		int numeroConta = conta.getNumeroConta();
		int digitoConta = conta.getDigitoConta();

		Double valorLimiteCheque = 0.0;
		int tipo_conta = 1;

		if (Cliente.salario < 2000.0) {

			valorLimiteCheque = 0.0;
			tipo_conta = 1;

		} else if (Cliente.salario >= 2000 && Cliente.salario < 4000) {

			valorLimiteCheque = 2000.0;
			tipo_conta = 2;

		} else if (Cliente.salario >= 4000 & Cliente.salario < 7500) {

			valorLimiteCheque = 4200.0;
			tipo_conta = 2;

		} else {

			valorLimiteCheque = 6000.0;
			tipo_conta = 2;

		}

		int statusConta = 1;

		int id_cliente = getResult();

		Conta conta = new Conta(numeroConta, digitoConta, valorLimiteCheque, statusConta, tipo_conta, id_cliente);

		contaDaoJDBC.inserir(conta);

	}
}
