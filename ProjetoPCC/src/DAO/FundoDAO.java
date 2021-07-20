package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Model.FundoModel;

public class FundoDAO extends ConnectionDAO {

	public ConnectionDAO conexão = new ConnectionDAO() {
		public Connection obterConexao() {
			return null;
		}
	};

	Set<FundoModel> alteraFundo = new HashSet<FundoModel>();

	public boolean persist2(FundoModel update) {
		return alteraFundo.add(update);
	}

	public boolean update(Object o) throws SQLException {
		String comandoSQL = ("UPDATE fundo SET dataCriacao = ?, codigoP = ?, descricao = ?, lote = ?, quantidade = ?, qualidade = ? WHERE idFundo =?");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		Date dataSql = new Date(((FundoModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setLong(2, ((FundoModel) o).getProduto());
		comandojava.setString(3, ((FundoModel) o).getDescricao());
		comandojava.setLong(4, ((FundoModel) o).getLote());
		comandojava.setFloat(5, ((FundoModel) o).getQuantidade());
		comandojava.setString(6, ((FundoModel) o).getQualidade());
		comandojava.setInt(7, ((FundoModel) o).getId());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean insert(Object o) throws SQLException {
		String comandoSQL = ("insert into fundo"
				+ "(dataCriacao, codigoP, descricao, lote, quantidade, qualidade) values(?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		Date dataSql = new Date(((FundoModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setLong(2, ((FundoModel) o).getProduto());
		comandojava.setString(3, ((FundoModel) o).getDescricao());
		comandojava.setLong(4, ((FundoModel) o).getLote());
		comandojava.setFloat(5, ((FundoModel) o).getQuantidade());
		comandojava.setString(6, ((FundoModel) o).getQualidade());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean delete(int id) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		comandojava = conexao.prepareStatement("DELETE FROM fundo WHERE idFundo = '" + id + "'");
		comandojava.executeUpdate();
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public List<FundoModel> consultaProdutos() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<FundoModel> produtos = new ArrayList<FundoModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM fundo ORDER BY dataCriacao ASC");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			FundoModel produto = new FundoModel();

			produto.setId(rs.getInt("idFundo"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigoP"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setLote(rs.getInt("lote"));
			produto.setQuantidade(rs.getFloat("quantidade"));
			produto.setQualidade(rs.getString("qualidade"));

			produtos.add(produto);
		}

		return produtos;
	}

}
