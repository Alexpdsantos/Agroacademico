package Controller;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.CadastrarProdutoDAO;
import Model.CadastrarProdutoModel;

public class CadastrarProdutoControl {
	
        private CadastrarProdutoDAO dao = new CadastrarProdutoDAO();
		public boolean insert(int codigo, String descricao) throws SQLException{
			
			CadastrarProdutoModel novo = new CadastrarProdutoModel(codigo, descricao);
			dao.insertRelatorio(novo);
			return dao.insert(novo);
			
		}



		public Connection obterConexao() throws SQLException{
			return dao.obterConexao();
		}
		public void desconecta(Connection conexao) throws SQLException{
			 dao.disconnect(conexao);
		}

}
