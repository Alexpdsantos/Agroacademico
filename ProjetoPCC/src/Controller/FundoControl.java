package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import DAO.FundoDAO;
import Model.FundoModel;

public class FundoControl {

	private FundoDAO dao = new FundoDAO();

	public boolean insert(Date data, int produto, String descricao, int lote, float quantidade, String qualidade)
			throws SQLException {
		FundoModel novo = new FundoModel(data, produto, descricao, lote, quantidade, qualidade);

		return dao.insert(novo);

	}

	public boolean update(Date data, int produto, String descricao, int lote, float quantidade,String qualidade, int id)
			throws SQLException {
		FundoModel update = new FundoModel(data, produto, descricao, lote, quantidade, qualidade, id);
		dao.persist2(update);
		return dao.update(update);

	}

	public Connection obterConexao() throws SQLException {
		return dao.obterConexao();
	}

	public void desconecta(Connection conexao) throws SQLException {
		dao.disconnect(conexao);
	}

}
