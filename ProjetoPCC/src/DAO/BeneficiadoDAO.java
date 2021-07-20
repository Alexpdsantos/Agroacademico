package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BeneficiadoModel;
import Model.ProdutoModel;

public class BeneficiadoDAO extends ConnectionDAO {
	
	public ConnectionDAO conexão = new ConnectionDAO() {
		public Connection obterConexao() {
			return null;
		}
	};
		
	public boolean insert(Object o) throws SQLException {
		String comandoSQL = ("insert into estoqueBeneficiado "
				+"(dataCriacao, codigo, descricao, lote, quantidade, talhao, qualidade, tipo, umidade, rendimento) values (?,?,?,?,?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
		Date dataSql = new Date(((BeneficiadoModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setLong(2, ((BeneficiadoModel) o).getProduto());
		comandojava.setString(3, ((BeneficiadoModel) o).getDescricao());
		comandojava.setString(4, ((BeneficiadoModel) o).getLote());
		comandojava.setFloat(5, ((BeneficiadoModel) o).getQuantidade());
		comandojava.setString(6, ((BeneficiadoModel) o).getTalhao());
		comandojava.setString(7, ((BeneficiadoModel) o).getQualidade());
		comandojava.setString(8, ((BeneficiadoModel) o).getTipo());
		comandojava.setFloat(9, ((BeneficiadoModel) o).getUmidade());
		comandojava.setFloat(10, ((BeneficiadoModel) o).getRendimento());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}
	
		public boolean update(Object o) throws SQLException {
			String comandoSQL = ("UPDATE EstoqueBeneficiado SET dataCriacao = ?, codigo = ?, descricao = ?, lote = ?, quantidade = ?, qualidade = ?, tipo =?, umidade = ?, rendimento = ? WHERE idBeneficiado =?");
			Connection conexao = obterConexao();
			PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
			Date dataSql = new Date(((BeneficiadoModel) o).getData().getTime());
			comandojava.setDate(1, dataSql);
			comandojava.setLong(2, ((BeneficiadoModel) o).getProduto());
			comandojava.setString(3, ((BeneficiadoModel) o).getDescricao());
			comandojava.setString(4, ((BeneficiadoModel) o).getLote());
			comandojava.setFloat(5, ((BeneficiadoModel) o).getQuantidade());
			comandojava.setString(6, ((BeneficiadoModel) o).getQualidade());
			comandojava.setString(7, ((BeneficiadoModel) o).getTipo());
			comandojava.setFloat(8, ((BeneficiadoModel) o).getUmidade());
			comandojava.setFloat(9, ((BeneficiadoModel) o).getRendimento());
			comandojava.setInt(10, ((BeneficiadoModel) o).getId());

			int resultado = comandojava.executeUpdate();
			comandojava.close();
			if (resultado != 0) {
				return true;
			}
			return false;
		}
		
		public boolean delete(int idBeneficiado) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			comandojava = conexao.prepareStatement("DELETE FROM estoqueBeneficiado WHERE idBeneficiado = '" + idBeneficiado + "'");
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
			comandojava = conexao.prepareStatement("DELETE FROM estoqueBeneficiado WHERE quantidade <= " +0 );
			comandojava.executeUpdate();
			int resultado = comandojava.executeUpdate();
			comandojava.close();
			if (resultado != 0) {
				return true;
			}
			return false;
		}
		
		public List<BeneficiadoModel> consultaProdutos() throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;

			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement("SELECT * FROM estoqueBeneficiado ORDER BY dataCriacao asc");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));

				produtos.add(produto);
			}

			return produtos;
		}
		
		
		
		public List<BeneficiadoModel> consultaPorDataProdLoteUmidade(java.util.Date dataInicial, java.util.Date dataFim,
				int codigo, String lote, float umidade) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;
			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement(
					"SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? AND lote like ? AND umidade like ? ORDER BY dataCriacao ASC");
			comandojava.setDate(1, (Date) dataInicial);
			comandojava.setDate(2, (Date) dataFim);
			comandojava.setInt(3, codigo);
			comandojava.setString(4, "%"+lote+"%");
			comandojava.setString(5, "%" + umidade + "%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorDataProdUmidade(java.util.Date dataInicial, java.util.Date dataFim, int codigo,
				float umidade) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;
			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement(
					"SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? AND umidade like ? ORDER BY dataCriacao ASC");
			comandojava.setDate(1, (Date) dataInicial);
			comandojava.setDate(2, (Date) dataFim);
			comandojava.setInt(3, codigo);
			comandojava.setString(4, "%" + umidade + "%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorDataProdLote(java.util.Date dataInicial, java.util.Date dataFim, int codigo, String lote)
				throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;

			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement(
					"SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? AND lote like ? ORDER BY dataCriacao ASC");
			comandojava.setDate(1, (Date) dataInicial);
			comandojava.setDate(2, (Date) dataFim);
			comandojava.setInt(3, codigo);
			comandojava.setString(4, "%"+lote+"%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}
		
		public List<BeneficiadoModel> consultaPorDataLoteUmidade(java.util.Date dataInicial, java.util.Date dataFim, float umidade, String lote) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;
			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement(
					"SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? AND umidade like ? AND lote like ? ORDER BY dataCriacao ASC");
			comandojava.setDate(1, (Date) dataInicial);
			comandojava.setDate(2, (Date) dataFim);
			comandojava.setString(3, "%" + umidade + "%");
			comandojava.setString(4, "%"+lote+"%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorDataProd(java.util.Date dataInicial, java.util.Date dataFim, int codigo)
				throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;

			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement(
					"SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? AND codigo = ? ORDER BY dataCriacao ASC");
			comandojava.setDate(1, (Date) dataInicial);
			comandojava.setDate(2, (Date) dataFim);
			comandojava.setInt(3, codigo);
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorDataLote(java.util.Date dataInicial, java.util.Date dataFim, String lote)
				throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;

			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement(
					"SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? AND lote like ? ORDER BY dataCriacao ASC");
			comandojava.setDate(1, (Date) dataInicial);
			comandojava.setDate(2, (Date) dataFim);
			comandojava.setString(3, "%"+lote+"%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorDataUmidade(java.util.Date dataInicial, java.util.Date dataFim, float umidade)
				throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;
			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement(
					"SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? AND umidade like ? ORDER BY dataCriacao ASC");
			comandojava.setDate(1, (Date) dataInicial);
			comandojava.setDate(2, (Date) dataFim);
			comandojava.setString(3, "%" + umidade + "%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorProdUmidade(int codigo, float umidade) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;
			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao
					.prepareStatement("SELECT * FROM estoqueBeneficiado WHERE codigo = ? AND umidade like ? ORDER BY dataCriacao ASC");
			comandojava.setInt(1, codigo);
			comandojava.setString(2, "%" + umidade + "%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorProdLote(int codigo, String lote) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;
			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao
					.prepareStatement("SELECT * FROM estoqueBeneficiado WHERE codigo = ? AND lote like ? ORDER BY dataCriacao ASC");
			comandojava.setInt(1, codigo);
			comandojava.setString(2, "%"+lote+"%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}

		public List<BeneficiadoModel> consultaPorLoteUmidade(String lote, String umidade) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;
			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao
					.prepareStatement("SELECT * FROM estoqueBEneficiado WHERE lote like ? AND umidade like ? ORDER BY dataCriacao ASC");
			comandojava.setString(1, "%"+lote+"%");
			comandojava.setString(2, "%" + umidade + "%");
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();
				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getFloat("quantidade"));
				produtos.add(produto);
			}
			return produtos;
		}
		
		public List<BeneficiadoModel> consultaPorData(java.util.Date dataInicial, java.util.Date dataFim) throws SQLException {
			Connection conexao = obterConexao();
			PreparedStatement comandojava = null;
			ResultSet rs = null;

			List<BeneficiadoModel> produtos = new ArrayList<BeneficiadoModel>();
			comandojava = conexao.prepareStatement("SELECT * FROM estoqueBeneficiado WHERE dataCriacao BETWEEN ? AND ? ORDER BY dataCriacao");
			comandojava.setDate(1,(Date) dataInicial);
			comandojava.setDate(2,(Date) dataFim);
			rs = comandojava.executeQuery();
			while (rs.next()) {
				BeneficiadoModel produto = new BeneficiadoModel();

				produto.setId(rs.getInt("idBeneficiado"));
				produto.setData(rs.getDate("dataCriacao"));
				produto.setProduto(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setLote(rs.getString("lote"));
				produto.setTalhao(rs.getString("talhao"));
				produto.setUmidade(rs.getFloat("umidade"));
				produto.setRendimento(rs.getFloat("rendimento"));
				produto.setQualidade(rs.getString("qualidade"));
				produto.setTipo(rs.getString("tipo"));
				produto.setQuantidade(rs.getInt("quantidade"));

				produtos.add(produto);
			}
			return produtos;
		}

}
