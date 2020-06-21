
package banco_de_dados;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Conexao_banco_dados {

	private static Connection conexao = null;

	// CONECTAR COM O BANCO DE DADOS
	public static Connection abrirConexaoComOBanco() {

		conexao = null;

		if (conexao == null) {

			try {

				// PEGANDO AS PROPRIEDADES DO OUTRO METODO
				Properties propriedades = lerPropriedades();
				// DBURL SER� ATRIBUIDO A VARIAVEL URL
				String url = propriedades.getProperty("dburl");
				conexao = DriverManager.getConnection(url, propriedades);

			} catch (SQLException e) {
				throw new BdExcecao(e.getMessage());
			}
		}
		return conexao;
	}

	// FECHAR CONEX�O COM O BANCO DE DADOS
	public static void fecharConexaoComoBanco() {
		// CONFERINDO SE A CONEX�O � EXISTENTE E FECHANDO
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new BdExcecao(e.getMessage());
			}
		}
	}

	// LER ARQUIVO COM OS CAMINHOS/ITENS NECESS�RIOS PARA CONEX�O
	private static Properties lerPropriedades() {
		// PEGANDO O ARQUIVO
		try (FileInputStream entradaDeArquivo = new FileInputStream("db.properties")) {
			Properties propriedades = new Properties();
			// LENDO O ARQUIVO
			propriedades.load(entradaDeArquivo);
			return propriedades;

		} catch (IOException e) {
			throw new BdExcecao(e.getMessage());
		}

	}

	public static void fecharStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new BdExcecao(e.getMessage());
			}
		}
	}

	public static void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new BdExcecao(e.getMessage());
			}
		}
	}

}