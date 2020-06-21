package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import banco_de_dados.BdExcecao;
import banco_de_dados.Conexao_banco_dados;
import model.dao.ContaDao;
import model.entities.Conta;

public class ContaDaoJDBC implements ContaDao {

	Connection conexao = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	public static double salarioDigitado;

	@Override
	public void inserir(Conta contaObj) {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement(
					"insert conta (NUMERO_CONTA, DIGITO_CONTA, LIMITE_CHEQUE_ESPECIAL, ID_TIPO_CONTA,  ID_STATUS_CONTA, ID_CLIENTE)"
							+ "values (?,?,?,?,?,?)");

			st.setInt(1, contaObj.getNumeroConta());
			st.setInt(2, contaObj.getDigitoConta());
			st.setDouble(3, contaObj.getLimiteCheque());
			st.setInt(4, contaObj.getIdTipoConta());
			st.setInt(5, contaObj.getIdStatusConta());
			st.setInt(6, contaObj.getIdCliente());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "CONTA CADASTRADA COM SUCESSO", "CADASTRO CONTA",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR CONTA", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	@Override
	public void atualizar(Conta contaSimplesObj, int id) {

		try {
			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao
					.prepareStatement("update conta set LIMITE_CHEQUE_ESPECIAL = ?, ID_TIPO_CONTA = ? where id= (?)");

			st.setDouble(1, contaSimplesObj.getLimiteCheque());
			st.setInt(2, contaSimplesObj.getIdTipoConta());
			st.setInt(3, contaSimplesObj.getIdCliente());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	@Override
	public void deletarContaPeloId(int id) {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement("delete from conta where id = ?");

			st.setInt(1, id);

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "CONTA EXCLUÍDA COM SUCESSO", "CONTA BANCÁRIA CLIENTE",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR A CONTA DO CLIENTE", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	@Override
	public Conta procurarContaPeloCPF(String CPF) {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement(
					"select conta.id, numero_conta, digito_conta, LIMITE_CHEQUE_ESPECIAL, TIPO_CONTA, SITUACAO"
							+ " from conta, cliente, status_conta, tipo_conta" + " where id_cliente = cliente.id"
							+ " and tipo_conta.id = id_tipo_conta" + " and status_conta.id = ID_STATUS_CONTA"
							+ " and cliente.CPF = ?");

			st.setString(1, CPF);

			rs = st.executeQuery();

			String contaString = "";

			Conta conta = new Conta();

			while (rs.next()) {

				int id = rs.getInt("conta.id");
				conta.setNumeroConta(rs.getInt("NUMERO_CONTA"));
				conta.setDigitoConta(rs.getInt("DIGITO_CONTA"));
				double limiteCheque = rs.getDouble("LIMITE_CHEQUE_ESPECIAL");
				String tipoConta = rs.getString("TIPO_CONTA");
				String situacao = rs.getString("SITUACAO");

				contaString = contaString + "ID: " + id + "\nNÚMERO DA CONTA: " + conta.getDigitoConta()
						+ "\nDIGITO DA CONTA: " + conta.getDigitoConta() + "\nLIMITE DO CHEQUE ESPECIAL: "
						+ limiteCheque + "\nTIPO DE CONTA: " + tipoConta + "\nSITUAÇÃO DA CONTA: " + situacao + "\n\n";

			}

			Object[] valoresPossiveis = { "EXCLUIR CONTA" };

			Object selectedValue = JOptionPane.showInputDialog(null, contaString, "ATUALIZAR DADOS DA CONTA",
					JOptionPane.INFORMATION_MESSAGE, null, valoresPossiveis, valoresPossiveis[0]);

			if (selectedValue == valoresPossiveis[0]) {

				Integer idDaResposta = Integer.parseInt(JOptionPane.showInputDialog(null,
						contaString + "\n\nDIGITE O 'ID' DA CONTA QUE DESEJA EXCLUIR"));

				int resposta = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA EXCLUIR ESSA CONTA: "
						+ conta.getNumeroConta() + "-" + conta.getDigitoConta(), "EXCLUIR CONTA",
						JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					deletarContaPeloId(idDaResposta);
				} else {
					JOptionPane.showMessageDialog(null, "CONTA NÃO EXCLUÍDA", "EXCLUIR CONTA",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());

		}
		return null;

	}

	@Override
	public List<Conta> procurarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double pegarSalario(String CPF) {

		double resultado = 0;

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement("select salario from cliente where cpf = ?");

			st.setString(1, CPF);

			rs = st.executeQuery();

			while (rs.next()) {

				resultado = rs.getDouble("salario");

			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		}

		return resultado;

	}

	public int pegarIdContaDeAcordoComCpfCliente(String CPF) {

		int idDele = 0;

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao
					.prepareStatement("select conta.id from conta, cliente where id_cliente = cliente.id and cpf = ?");

			st.setString(1, CPF);

			rs = st.executeQuery();

			while (rs.next()) {

				idDele = rs.getInt("conta.id");

			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		}

		return idDele;

	}

}
