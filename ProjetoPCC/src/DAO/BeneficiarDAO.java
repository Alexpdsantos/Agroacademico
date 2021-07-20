package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BeneficiarModel;

public class BeneficiarDAO extends ConnectionDAO {

	public ConnectionDAO conexão = new ConnectionDAO() {
		public Connection obterConexao() {
			return null;
		}
	};

	public boolean insert(Object o) throws SQLException {
		String comandoSQL = ("insert into Beneficiar"
				+ "(idProduto, dataBeneficiamento, codigo, descricao, lote, talhao, entrada, saida, rendimento) values(?,?,?,?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
		Date dataSql = new Date(((BeneficiarModel) o).getData().getTime());

		comandojava.setString(1, ((BeneficiarModel) o).getIdProduto());
		comandojava.setDate(2, dataSql);
		comandojava.setString(3, ((BeneficiarModel) o).getProduto());
		comandojava.setString(4, ((BeneficiarModel) o).getDescricao());
		comandojava.setString(5, ((BeneficiarModel) o).getLote());
		comandojava.setString(6, ((BeneficiarModel) o).getTalhao());
		comandojava.setFloat(7, ((BeneficiarModel) o).getEntrada());
		comandojava.setFloat(8, ((BeneficiarModel) o).getSaida());
		comandojava.setFloat(9, ((BeneficiarModel) o).getRendimento());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean insertBeneficiar(Object o) throws SQLException {
		String comandoSQL = ("insert into relatorioBeneficiar"
				+ "(idProduto, dataBeneficiamento, codigo, descricao, lote, talhao, entrada, saida, rendimento) values(?,?,?,?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
		Date dataSql = new Date(((BeneficiarModel) o).getData().getTime());

		comandojava.setString(1, ((BeneficiarModel) o).getIdProduto());
		comandojava.setDate(2, dataSql);
		comandojava.setString(3, ((BeneficiarModel) o).getProduto());
		comandojava.setString(4, ((BeneficiarModel) o).getDescricao());
		comandojava.setString(5, ((BeneficiarModel) o).getLote());
		comandojava.setString(6, ((BeneficiarModel) o).getTalhao());
		comandojava.setFloat(7, ((BeneficiarModel) o).getEntrada());
		comandojava.setFloat(8, ((BeneficiarModel) o).getSaida());
		comandojava.setFloat(9, ((BeneficiarModel) o).getRendimento());

		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean finaliza(Object o) throws SQLException {
		String comandoSQL = ("insert into estoqueBeneficiado"
				+ "(dataCriacao, codigo, descricao, lote, quantidade, talhao, qualidade, tipo, umidade, rendimento, valor, idBeneficiar, idProduto) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
		Date dataSql = new Date(((BeneficiarModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setString(2, ((BeneficiarModel) o).getProduto());
		comandojava.setString(3, ((BeneficiarModel) o).getDescricao());
		comandojava.setString(4, ((BeneficiarModel) o).getLote());
		comandojava.setFloat(5, ((BeneficiarModel) o).getSaida());
		comandojava.setString(6, ((BeneficiarModel) o).getTalhao());
		comandojava.setString(7, "");
		comandojava.setString(8, "Normal");
		comandojava.setString(9, "0.0");
		comandojava.setFloat(10, ((BeneficiarModel) o).getRendimento());
		comandojava.setFloat(11, ((BeneficiarModel) o).getValor());
		comandojava.setInt(12, ((BeneficiarModel) o).getIdBeneficiar());
		comandojava.setString(13, ((BeneficiarModel) o).getIdProduto());
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public boolean insereRelatorioBeneficiado(Object o) throws SQLException {
		String comandoSQL = ("insert into relatorioBeneficiado"
				+ "(dataCriacao, codigo, descricao, lote, quantidade, talhao, qualidade, tipo, umidade, rendimento) values(?,?,?,?,?,?,?,?,?,?)");
		Connection conexao = obterConexao();
		PreparedStatement comandojava = conexao.prepareStatement(comandoSQL);
		Date dataSql = new Date(((BeneficiarModel) o).getData().getTime());
		comandojava.setDate(1, dataSql);
		comandojava.setString(2, ((BeneficiarModel) o).getProduto());
		comandojava.setString(3, ((BeneficiarModel) o).getDescricao());
		comandojava.setString(4, ((BeneficiarModel) o).getLote());
		comandojava.setFloat(5, ((BeneficiarModel) o).getSaida());
		comandojava.setString(6, ((BeneficiarModel) o).getTalhao());
		comandojava.setString(7, "");
		comandojava.setString(8, "Normal");
		comandojava.setString(9, "0.0");
		comandojava.setFloat(10, ((BeneficiarModel) o).getRendimento());

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
		comandojava = conexao.prepareStatement("DELETE FROM Beneficiar WHERE idBeneficiar =" + id);
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
		comandojava = conexao.prepareStatement("DELETE FROM Beneficiar WHERE entrada <="+0);
		comandojava.executeUpdate();
		int resultado = comandojava.executeUpdate();
		comandojava.close();
		if (resultado != 0) {
			return true;
		}
		return false;
	}

	public List<BeneficiarModel> consultaBeneficiamento() throws SQLException {
		Connection conexao = obterConexao();
		PreparedStatement comandojava = null;
		ResultSet rs = null;

		List<BeneficiarModel> produtos = new ArrayList<BeneficiarModel>();
		comandojava = conexao.prepareStatement("SELECT * FROM Beneficiar ORDER BY dataBeneficiamento ASC");
		rs = comandojava.executeQuery();
		while (rs.next()) {
			BeneficiarModel produto = new BeneficiarModel();
			produto.setIdBeneficiar(rs.getInt("idBeneficiar"));
			produto.setIdProduto(rs.getString("idProduto"));
			produto.setData(rs.getDate("dataBeneficiamento"));
			produto.setProduto(rs.getString("codigo"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setLote(rs.getString("lote"));
			produto.setTalhao(rs.getString("talhao"));
			produto.setEntrada(rs.getFloat("entrada"));
			produto.setSaida(rs.getFloat("saida"));
			produto.setRendimento(rs.getFloat("rendimento"));

			produtos.add(produto);
		}
		return produtos;
	}
}