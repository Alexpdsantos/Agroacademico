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

import Model.ProdutoModel;

public class ProdutoDAO extends ConnectionDAO {

	public ConnectionDAO conexão = new ConnectionDAO() {
		public Connection obterConexao() {
			return null;
		}
	};
	Set<ProdutoModel> cadastraProduto = new HashSet<ProdutoModel>();

	public boolean persist(ProdutoModel novo) {
		return cadastraProduto.add(novo);
	}

	public Set<ProdutoModel> printAll() {
		return cadastraProduto;
	}

	Set<ProdutoModel> alteraProduto = new HashSet<ProdutoModel>();

	public boolean persist2(ProdutoModel update) {
		return alteraProduto.add(update);
	}

	public boolean insert(Object o) throws SQLException {
		String comandoSQL = ("insert into Produto"
				+ "(dataCriacao, codigo, descricao, talhao, lote, quantidade) values(?,?,?,?,?,?)");

		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		Date dataSql = new Date(((ProdutoModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setLong(2, ((ProdutoModel) o).getProduto());
		comandojava.setString(3, ((ProdutoModel) o).getDescricao());
		comandojava.setString(4, ((ProdutoModel) o).getTalhao());
		comandojava.setString(5, ((ProdutoModel) o).getLote());
		comandojava.setFloat(6, ((ProdutoModel) o).getQuantidade());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean insertRelatorio(Object o) throws SQLException {
		String comandoSQL = ("insert into RelatorioProdutos"
				+ "(dataCriacao, codigo, descricao, talhao, lote, quantidade) values(?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
		Date dataSql = new Date(((ProdutoModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setLong(2, ((ProdutoModel) o).getProduto());
		comandojava.setString(3, ((ProdutoModel) o).getDescricao());
		comandojava.setString(4, ((ProdutoModel) o).getLote());
		comandojava.setString(5, ((ProdutoModel) o).getTalhao());
		comandojava.setFloat(6, ((ProdutoModel) o).getQuantidade());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean update(Object o) throws SQLException {
		String comandoSQL = ("UPDATE Produto SET dataCriacao = ?, codigo = ?, descricao = ?, talhao = ?, lote = ?, quantidade = ? WHERE idProduto =?");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
		Date dataSql = new Date(((ProdutoModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setLong(2, ((ProdutoModel) o).getProduto());
		comandojava.setString(3, ((ProdutoModel) o).getDescricao());
		comandojava.setString(4, ((ProdutoModel) o).getTalhao());
		comandojava.setString(5, ((ProdutoModel) o).getLote());
		comandojava.setFloat(6, ((ProdutoModel) o).getQuantidade());
		comandojava.setInt(7, ((ProdutoModel) o).getId());

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
		comandojava = conexao.prepareStatement("DELETE FROM Produto WHERE idProduto = " + id);
		comandojava.executeUpdate();
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteZero() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		comandojava = conexao.prepareStatement("DELETE FROM Produto WHERE quantidade <= " +0);
		comandojava.executeUpdate();
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}


	public List<ProdutoModel> consultaProdutos() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM Produto ORDER BY dataCriacao ASC");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getFloat("quantidade"));

			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorDataProdLoteTalhao(java.util.Date dataInicial, java.util.Date dataFim,
			int codigo, String lote, String talhao) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement(
				"SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? AND lote like ? AND talhao like ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		comandojava.setInt(3, codigo);
		comandojava.setString(4, "%"+lote+"%");
		comandojava.setString(5, "%" + talhao + "%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorDataProdTalhao(java.util.Date dataInicial, java.util.Date dataFim, int codigo,
			String talhao) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement(
				"SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? AND talhao like ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		comandojava.setInt(3, codigo);
		comandojava.setString(4, "%" + talhao + "%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorDataProdLote(java.util.Date dataInicial, java.util.Date dataFim, int codigo, String lote)
			throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement(
				"SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? AND lote like ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		comandojava.setInt(3, codigo);
		comandojava.setString(4, "%"+lote+"%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}
	
	public List<ProdutoModel> consultaPorDataTalhaoLote(java.util.Date dataInicial, java.util.Date dataFim, String talhao, String lote) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement(
				"SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? AND talhao like ? AND lote like ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		comandojava.setString(3, "%" + talhao + "%");
		comandojava.setString(4, "%"+lote+"%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorDataProd(java.util.Date dataInicial, java.util.Date dataFim, int codigo)
			throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement(
				"SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		comandojava.setInt(3, codigo);
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorDataLote(java.util.Date dataInicial, java.util.Date dataFim, String lote)
			throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement(
				"SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? AND lote like ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		comandojava.setString(3, "%"+lote+"%");
		rs = comandojava.executeQuery();
		while (rs.next()) {

			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));

			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorDataTalhao(java.util.Date dataInicial, java.util.Date dataFim, String talhao)
			throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement(
				"SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? AND talhao like ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		comandojava.setString(3, "%" + talhao + "%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorProdTalhao(int codigo, String talhao) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao
				.prepareStatement("SELECT * FROM Produto WHERE codigo = ? AND talhao like ? ORDER BY dataCriacao ASC");
		comandojava.setInt(1, codigo);
		comandojava.setString(2, "%" + talhao + "%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorProdLote(int codigo, String lote) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao
				.prepareStatement("SELECT * FROM Produto WHERE codigo = ? AND lote like ? ORDER BY dataCriacao ASC");
		comandojava.setInt(1, codigo);
		comandojava.setString(2, "%"+lote+"%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorLoteTalhao(String lote, String talhao) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao
				.prepareStatement("SELECT * FROM Produto WHERE lote like ? AND talhao like ? ORDER BY dataCriacao ASC");
		comandojava.setString(1, "%"+lote+"%");
		comandojava.setString(2, "%" + talhao + "%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorData(java.util.Date dataInicial, java.util.Date dataFim) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao
				.prepareStatement("SELECT * FROM Produto WHERE dataCriacao BETWEEN ? AND ? ORDER BY dataCriacao ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorProd(int codigo) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM Produto WHERE codigo = ? ORDER BY dataCriacao ASC");
		comandojava.setInt(1, codigo);
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorLote(String lote) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM Produto WHERE lote like ? ORDER BY dataCriacao ASC");
		comandojava.setString(1, "%"+lote+"%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}

	public List<ProdutoModel> consultaPorTalhao(String talhao) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM Produto WHERE talhao like ? ORDER BY dataCriacao ASC");
		comandojava.setString(1, "%" + talhao + "%");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			ProdutoModel produto = new ProdutoModel();
			produto.setId(rs.getInt("idProduto"));
			produto.setData(rs.getDate("dataCriacao"));
			produto.setProduto(rs.getInt("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setLote(rs.getString("lote"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produtos.add(produto);
		}
		return produtos;
	}
}