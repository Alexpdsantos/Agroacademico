package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CadastrarProdutoModel;

public class CadastrarProdutoDAO extends ConnectionDAO {

	public ConnectionDAO conexão = new ConnectionDAO() {
		public Connection obterConexao() {
			return null;
		}
	};
	
	public boolean insert(CadastrarProdutoModel novo) throws SQLException {
		String comandoSQL = ("insert into cadastrarProduto" + "(codigo, descricao) values(?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		comandojava.setInt(1, novo.getCodigo());
		comandojava.setString(2, novo.getDescricao());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}
	
	public boolean insertRelatorio(CadastrarProdutoModel novo) throws SQLException {
		String comandoSQL = ("insert into relatorioProduto" + "(codigo, descricao) values(?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		comandojava.setLong(1, novo.getCodigo());
		comandojava.setString(2, novo.getDescricao());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public List<CadastrarProdutoModel> listarTodos() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<CadastrarProdutoModel> listaProdutos = new ArrayList<CadastrarProdutoModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM cadastrarProduto");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			CadastrarProdutoModel produto = new CadastrarProdutoModel();
			produto.setCodigo(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			
			listaProdutos.add(produto);
		}
		return listaProdutos;
	}

}
