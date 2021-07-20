package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CadastrarTalhaoModel;

public class CadastrarTalhaoDAO extends ConnectionDAO {

	public ConnectionDAO conexão = new ConnectionDAO() {
		public Connection obterConexao() {
			return null;
		}
	};

	public boolean insert(CadastrarTalhaoModel novo) throws SQLException {
		String comandoSQL = ("insert into cadastrarTalhao"
				+ "(talhao, variedade, area, quantPes, espacamento) values(?,?,?,?,?)");
		// obter a conexão
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		// setar os valores a serem inseridos
		comandojava.setString(1, novo.getTalhao());
		comandojava.setString(2, novo.getVariedade());
		comandojava.setString(3, novo.getArea());
		comandojava.setInt(4, novo.getQuantPes());
		comandojava.setString(5, novo.getEspacamento());

		// executar o comando SQL no banco
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}
	
	public boolean insertRelatorio(CadastrarTalhaoModel novo) throws SQLException {
		String comandoSQL = ("insert into relatorioTalhao"
				+ "(talhao, variedade, area, quantPes, espacamento) values(?,?,?,?,?)");
		// obter a conexão
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		// setar os valores a serem inseridos
		comandojava.setString(1, novo.getTalhao());
		comandojava.setString(2, novo.getVariedade());
		comandojava.setString(3, novo.getArea());
		comandojava.setInt(4, novo.getQuantPes());
		comandojava.setString(5, novo.getEspacamento());

		// executar o comando SQL no banco
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}
	
	public List<CadastrarTalhaoModel> listarTodos() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<CadastrarTalhaoModel> listaTalhoes = new ArrayList<CadastrarTalhaoModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM cadastrarTalhao");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			CadastrarTalhaoModel talhao = new CadastrarTalhaoModel();
			talhao.setTalhao(rs.getString("talhao"));
			talhao.setVariedade(rs.getString("variedade"));
			
			listaTalhoes.add(talhao);
		}
		return listaTalhoes;
	}


}
