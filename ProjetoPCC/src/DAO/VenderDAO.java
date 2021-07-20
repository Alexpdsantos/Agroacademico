package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.VenderModel;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;

public class VenderDAO extends ConnectionDAO {

	public ConnectionDAO conexão = new ConnectionDAO() {
		public Connection obterConexao() {
			return null;
		}
	};

	public boolean insert(Object o) throws SQLException {
		String comandoSQL = ("insert into venderProduto"
				+ "(idBeneficiado, codigo, descricao, talhao, lote, tipo, qualidade, quantidade, precokg, desconto, valorTotal, cliente, dataVenda) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		Date dataSql = new Date(((VenderModel) o).getData().getTime());

		comandojava.setString(1, ((VenderModel) o).getIdBeneficiado());
		comandojava.setString(2, ((VenderModel) o).getProduto());
		comandojava.setString(3, ((VenderModel) o).getDescricao());
		comandojava.setString(4, ((VenderModel) o).getTalhao());
		comandojava.setString(5, ((VenderModel) o).getLote());
		comandojava.setString(6, ((VenderModel) o).getTipo());
		comandojava.setString(7, ((VenderModel) o).getQualidade());
		comandojava.setFloat(8, ((VenderModel) o).getQuantidade());
		comandojava.setDouble(9, ((VenderModel) o).getPrecoKg());
		comandojava.setInt(10, ((VenderModel) o).getDesconto());
		comandojava.setDouble(11, ((VenderModel) o).getValorTotal());
		comandojava.setString(12, ((VenderModel) o).getCliente());
		comandojava.setDate(13, dataSql);

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean insertRelatorio(Object o) throws SQLException {
		String comandoSQL = ("insert into relatorioVendas"
				+ "(idBeneficiado, codigo, descricao, talhao, lote, tipo, qualidade, quantidade, precokg, desconto, valorTotal, cliente, dataVenda) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		Date dataSql = new Date(((VenderModel) o).getData().getTime());
		comandojava.setString(1, ((VenderModel) o).getIdBeneficiado());
		comandojava.setString(2, ((VenderModel) o).getProduto());
		comandojava.setString(3, ((VenderModel) o).getDescricao());
		comandojava.setString(4, ((VenderModel) o).getTalhao());
		comandojava.setString(5, ((VenderModel) o).getLote());
		comandojava.setString(6, ((VenderModel) o).getTipo());
		comandojava.setString(7, ((VenderModel) o).getQualidade());
		comandojava.setFloat(8, ((VenderModel) o).getQuantidade());
		comandojava.setDouble(9, ((VenderModel) o).getPrecoKg());
		comandojava.setInt(10, ((VenderModel) o).getDesconto());
		comandojava.setDouble(11, ((VenderModel) o).getValorTotal());
		comandojava.setString(12, ((VenderModel) o).getCliente());
		comandojava.setDate(13, dataSql);

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean update(Object o) throws SQLException {
		String comandoSQL = ("UPDATE venderProduto SET codigo=?, descricao=?,talhao=?, lote=?, tipo=?,"
				+ " qualidade=?, quantidade=?, precoKg=?, desconto=?, valorTotal=?, cliente=?, dataVenda=? WHERE idVenda=?");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);

		Date dataSql = new Date(((VenderModel) o).getData().getTime());

		comandojava.setString(1, ((VenderModel) o).getProduto());
		comandojava.setString(2, ((VenderModel) o).getDescricao());
		comandojava.setString(3, ((VenderModel) o).getTalhao());
		comandojava.setString(4, ((VenderModel) o).getLote());
		comandojava.setString(5, ((VenderModel) o).getTipo());
		comandojava.setString(6, ((VenderModel) o).getQualidade());
		comandojava.setFloat(7, ((VenderModel) o).getQuantidade());
		comandojava.setDouble(8, ((VenderModel) o).getPrecoKg());
		comandojava.setInt(9, ((VenderModel) o).getDesconto());
		comandojava.setDouble(10, ((VenderModel) o).getValorTotal());
		comandojava.setString(11, ((VenderModel) o).getCliente());
		comandojava.setDate(12, dataSql);
		comandojava.setInt(13, ((VenderModel) o).getId());

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
		comandojava = conexao.prepareStatement("DELETE FROM venderProduto WHERE idVenda = " + id);
		comandojava.executeUpdate();
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public List<VenderModel> consultaProdutos() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<VenderModel> produtos = new ArrayList<VenderModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM venderProduto ORDER BY idVenda desc");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			VenderModel produto = new VenderModel();

			produto.setId(rs.getInt("idVenda"));
			produto.setData(rs.getDate("dataVenda"));
			produto.setProduto(rs.getString("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setLote(rs.getString("lote"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setQualidade(rs.getString("qualidade"));
			produto.setTipo(rs.getString("tipo"));
			produto.setPrecoKg(rs.getDouble("precokg"));
			produto.setDesconto(rs.getInt("desconto"));
			produto.setValorTotal(rs.getDouble("valorTotal"));
			produto.setCliente(rs.getString("cliente"));
			produto.setQuantidade(rs.getFloat("quantidade"));

			produtos.add(produto);
		}

		return produtos;
	}
	
	public List<VenderModel> consultaPorData(java.util.Date dataInicial, java.util.Date dataFim) throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<VenderModel> venda = new ArrayList<VenderModel>();
		comandojava = conexao
				.prepareStatement("SELECT * FROM venderProduto WHERE dataVenda BETWEEN ? AND ? ORDER BY dataVenda ASC");
		comandojava.setDate(1, (Date) dataInicial);
		comandojava.setDate(2, (Date) dataFim);
		rs = comandojava.executeQuery();
		while (rs.next()) {
			VenderModel produto = new VenderModel();
			produto.setId(rs.getInt("idVenda"));
			produto.setData(rs.getDate("dataVenda"));
			produto.setProduto(rs.getString("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setLote(rs.getString("lote"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setQualidade(rs.getString("qualidade"));
			produto.setTipo(rs.getString("tipo"));
			produto.setPrecoKg(rs.getDouble("precokg"));
			produto.setDesconto(rs.getInt("desconto"));
			produto.setValorTotal(rs.getDouble("valorTotal"));
			produto.setCliente(rs.getString("cliente"));
			produto.setQuantidade(rs.getFloat("quantidade"));
			venda.add(produto);
		}
		return venda;
	}
	
	/*public List<VenderModel> geraRelat() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<VenderModel> produtos = new ArrayList<VenderModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM venderProduto ORDER BY dataVenda ASC");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			
			String src = "C:\\Users\\lequi\\OneDrive\\Área de Trabalho\\ProjetoPCC\\src\\MyReports\\relatorio.jasper";
			
			JasperPrint	jp = null;
			
			try {
				jp = JasperFillManager.fillReport(src, null, conexao);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return produtos;
	}*/

}