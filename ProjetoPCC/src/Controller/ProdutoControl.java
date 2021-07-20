package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import DAO.ProdutoDAO;
import Model.ProdutoModel;

public class ProdutoControl {
	
	private ProdutoDAO dao = new ProdutoDAO();
	
	public boolean insert(Date data, int produto, String descricao, String  lote, String talhao, float quantidade) throws SQLException{
		ProdutoModel novo = new ProdutoModel(data, produto, descricao, lote, talhao, quantidade);
		dao.insertRelatorio(novo);
		return dao.insert(novo);
		
	}
	
	public boolean update(Date data, int produto, String descricao, String  lote, String talhao, float quantidade, int id) throws SQLException{
		ProdutoModel update = new ProdutoModel(data, produto, descricao, lote, talhao, quantidade, id);
		dao.persist2(update);
		return dao.update(update);
		
	}


	public Connection obterConexao() throws SQLException{
		return dao.obterConexao();
	}
	public void desconecta(Connection conexao) throws SQLException{
		 dao.disconnect(conexao);
	}
	
}