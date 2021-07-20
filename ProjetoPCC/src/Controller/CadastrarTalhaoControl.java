package Controller;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.CadastrarTalhaoDAO;
import Model.CadastrarTalhaoModel;

public class CadastrarTalhaoControl {
	
        private CadastrarTalhaoDAO dao = new CadastrarTalhaoDAO();
		public boolean insert(String nome, int quantPes, String espacamento, String area, String variedade) throws SQLException{
			
			CadastrarTalhaoModel novo = new CadastrarTalhaoModel(nome, quantPes, espacamento, area, variedade);
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
