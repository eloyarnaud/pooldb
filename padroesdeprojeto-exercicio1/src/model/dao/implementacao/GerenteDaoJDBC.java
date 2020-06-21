package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import banco_de_dados.BdExcecao;
import banco_de_dados.Conexao_banco_dados;
import model.dao.GerenteDao;
import model.entities.Gerente;
import model.services.Atualizacao;

public class GerenteDaoJDBC implements GerenteDao {

	Connection conexao = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	@Override
	public void inserir(Gerente gerenteObj) {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement(
					"INSERT into gerente (NOME_COMPLETO, MATRICULA, EMAIL, TELEFONE, SETOR_RESPONSAVEL) values (?,?,?,?,?)");

			st.setString(1, gerenteObj.getNome_completo());
			st.setString(2, gerenteObj.getMatricula());
			st.setString(3, gerenteObj.getEmail());
			st.setString(4, gerenteObj.getTelefone());
			st.setInt(5, gerenteObj.getSetorResponsavel());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "GERENTE CADASTRADO COM SUCESSO", "CADASTRO GERENTE",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR O GERENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	@Override
	public void atualizar(Gerente gerenteObj, String Matricula) {

		conexao = Conexao_banco_dados.abrirConexaoComOBanco();

		try {

			if (gerenteObj.getEmail() != null) {

				st = conexao.prepareStatement("update gerente set EMAIL = (?) where matricula = ?");
				st.setString(1, gerenteObj.getEmail());
				st.setString(2, Matricula);

			} else if (gerenteObj.getTelefone() != null) {

				st = conexao.prepareStatement("update gerente set TELEFONE = (?) where matricula = ?");
				st.setString(1, gerenteObj.getTelefone());
				st.setString(2, Matricula);

			} else if (gerenteObj.getSetorResponsavel() != null) {

				st = conexao.prepareStatement("update gerente set SETOR_RESPONSAVEL = (?) where matricula = ?");
				st.setInt(1, gerenteObj.getSetorResponsavel());
				st.setString(2, Matricula);

			}

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "CADASTRADO DO GERENTE ALTERADO COM SUCESSO", "CADASTRO GERENTE",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO AO ATUALIZAR CADASTRO DO GERENTE", "ERROR",
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
	public void deletarPelaMatricula(String Matricula) {

		conexao = Conexao_banco_dados.abrirConexaoComOBanco();

		try {

			st = conexao.prepareStatement("delete from GERENTE where matricula = ?");

			st.setString(1, Matricula);

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "GERENTE EXCLUÍDO COM SUCESSO", "CADASTRO GERENTE",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR O GERENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	@Override
	public Gerente procurarPelaMatricula(String Matricula) {

		conexao = Conexao_banco_dados.abrirConexaoComOBanco();

		try {

			st = conexao.prepareStatement(
					"select NOME_COMPLETO, MATRICULA, EMAIL, TELEFONE, SETOR_RESPONSAVEL from gerente where matricula = ?");

			st.setString(1, Matricula);

			rs = st.executeQuery();

			if (rs.next()) {

				Gerente gerente = new Gerente();

				gerente.setNome_completo(rs.getString("NOME_COMPLETO"));
				gerente.setMatricula(rs.getString("MATRICULA"));
				gerente.setEmail(rs.getString("EMAIL"));
				gerente.setTelefone(rs.getString("TELEFONE"));
				gerente.setSetorResponsavel(rs.getInt("SETOR_RESPONSAVEL"));

				int setorNome = gerente.getSetorResponsavel();

				SetorDaoJDBC setor = new SetorDaoJDBC();

				String setorEmString = setor.mostrarSetorDeAcordoComId(setorNome);

				Object[] valoresPossiveis = { "ATUALIZAR CADASTRO DO GERENTE", "DELETAR CADASTRO DO GERENTE" };

				Object selectedValue = JOptionPane.showInputDialog(null,
						"NOME COMPLETO: " + gerente.getNome_completo() + "\nMATRÍCULA: " + gerente.getMatricula()
								+ "\nEMAIL: " + gerente.getEmail() + "\nTELEFONE: " + gerente.getTelefone()
								+ "\nSETOR RESPONSÁVEL: " + setorEmString + "\n\n",
						"ATUALIZAR CADASTRO DO GERENTE", JOptionPane.INFORMATION_MESSAGE, null, valoresPossiveis,
						valoresPossiveis[0]);

				if (selectedValue == valoresPossiveis[0]) {
					Atualizacao atu = new Atualizacao();
					atu.atualizarGerente(Matricula);
				} else if (selectedValue == valoresPossiveis[1]) {

					int resposta = JOptionPane.showConfirmDialog(null,
							"TEM CERTEZA QUE DESEJA EXCLUIR ESSE GERENTE: '" + gerente.getNome_completo() + " '",
							"EXCLUIR GERENTE", JOptionPane.YES_NO_OPTION);

					if (resposta == 0) {
						deletarPelaMatricula(Matricula);
					} else {
						JOptionPane.showMessageDialog(null, "GERENTE NÃO EXCLUÍDO", "EXCLUIR GERENTE",
								JOptionPane.ERROR_MESSAGE);
					}

				}

				return gerente;

			}

			return null;

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	@Override
	public List<Gerente> procurarTodos() {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement(
					"select nome_completo, matricula, email, telefone, setor from gerente, setor where setor_responsavel = setor.id order by gerente.nome_completo");

			rs = st.executeQuery();

			String retorno = "";

			while (rs.next()) {

				String nomeCompleto = rs.getString("nome_completo");
				String matricula = rs.getString("matricula");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				String setor = rs.getString("setor");

				retorno = retorno + "NOME COMPLETO: " + nomeCompleto + "\nMATRICULA: " + matricula + "\nEMAIL: " + email
						+ "\nTELEFONE: " + telefone + "\nSETOR: " + setor + "\n\n";

			}

			JOptionPane.showMessageDialog(null, retorno);

			return null;

//			

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
		}

	}

	public List<Gerente> retornarGerenteNome_Matricula() {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement("select NOME_COMPLETO, MATRICULA from gerente");

			rs = st.executeQuery();

			String retorn = "";

			while (rs.next()) {

				String matriculaGerente = rs.getString("MATRICULA");
				String nomeGerente = rs.getString("NOME_COMPLETO");

				retorn = "MATRICULA: " + matriculaGerente + "\nNOME COMPLETO: " + nomeGerente;

				JOptionPane.showMessageDialog(null, retorn);

			}
			return null;

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
		}

	}

	public int idGerente() {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement("select ID, NOME_COMPLETO, MATRICULA from gerente");

			rs = st.executeQuery();

			List<String> listaGerente = new ArrayList<>();

			while (rs.next()) {

				int idGerente = rs.getInt("ID");
				String matriculaGerente = rs.getString("MATRICULA");
				String nomeGerente = rs.getString("NOME_COMPLETO");

				listaGerente
						.add("ID: " + idGerente + "\nMATRÍCULA: " + matriculaGerente + "\nNOME: " + nomeGerente + "\n");

			}

			String gerente = "";

			for (int i = 0; i < listaGerente.size(); i++) {

				gerente = gerente + listaGerente.get(i) + "\n";

			}

			int idGerenteEscolhido = Integer
					.parseInt(JOptionPane.showInputDialog(gerente + "\nINFORME O ID DO GERENTE"));

			return idGerenteEscolhido;

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
		}

	}

	public String nomeMatriculaConfirmacao(String Matricula) {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement("select nome_completo, matricula from gerente where matricula = ?");

			st.setString(1, Matricula);

			rs = st.executeQuery();

			String nomeCompleto = " ";

			while (rs.next()) {

				String matriculaGerente = rs.getString("MATRICULA");
				String nomeGerente = rs.getString("NOME_COMPLETO");

				nomeCompleto = nomeCompleto + nomeGerente + " | MATRICULA: " + matriculaGerente;

			}
			return nomeCompleto;

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
		}
	}

	public String emailTelefoneSetor(String Matricula) {

		conexao = Conexao_banco_dados.abrirConexaoComOBanco();

		try {

			st = conexao.prepareStatement(
					"select EMAIL, TELEFONE, setor.setor from GERENTE,SETOR where SETOR_RESPONSAVEL = setor.ID AND MATRICULA = ?");

			st.setString(1, Matricula);

			rs = st.executeQuery();

			String retorno = "";

			if (rs.next()) {

				System.out.println();

				String email = rs.getString("EMAIL");
				String telefone = rs.getString("TELEFONE");
				String setorRespon = rs.getString("SETOR");

				retorno = "EMAIL: " + email + "\nTELEFONE: " + telefone + "\nSETOR RESPONSÁVEL: " + setorRespon;

			}

			return retorno;

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	public String voltarMatriculaAtravesDoId(Integer id) {

		conexao = Conexao_banco_dados.abrirConexaoComOBanco();

		try {

			st = conexao.prepareStatement("select NOME_COMPLETO from gerente where id =  ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			String retorno = "";

			if (rs.next()) {

				String matricula = rs.getString("NOME_COMPLETO");

				retorno = retorno + matricula;

			}
			return retorno;

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	public String mostrarGerenteDeAcordoComId(int id) {

		conexao = Conexao_banco_dados.abrirConexaoComOBanco();

		try {

			st = conexao.prepareStatement("select nome_completo from gerente where id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			String finals = null;

			while (rs.next()) {
				String setor = rs.getString("NOME_COMPLETO");
				finals = setor;
			}
			return finals;

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharResultSet(rs);
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

}
