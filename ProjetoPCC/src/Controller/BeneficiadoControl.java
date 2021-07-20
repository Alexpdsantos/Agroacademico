package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import DAO.BeneficiadoDAO;
import Model.BeneficiadoModel;

public class BeneficiadoControl {

	private BeneficiadoDAO dao = new BeneficiadoDAO();
	
	public boolean insert(Date data, int produto, String descricao, String  lote,String talhao, float quantidade, String qualidade, String tipo, Float umidade,Float rendimento, int id) throws SQLException{
		BeneficiadoModel novo = new BeneficiadoModel(data, produto, descricao, lote,talhao, quantidade, qualidade, tipo, umidade, rendimento);
		return dao.insert(novo);
	}
	
	public boolean update(Date data, int produto, String descricao, String  lote,String talhao, float quantidade, String qualidade, String tipo, Float umidade,Float rendimento, int id) throws SQLException{
		BeneficiadoModel update = new BeneficiadoModel(data, produto, descricao, lote,talhao, quantidade, qualidade, tipo, umidade, rendimento, id);
		return dao.update(update);
	}
	
	public Connection obterConexao() throws SQLException{
		return dao.obterConexao();
	}
	public void desconecta(Connection conexao) throws SQLException{
		 dao.disconnect(conexao);
	}
}
