package Controller;

import java.sql.SQLException;
import java.util.Date;

import DAO.BeneficiarDAO;
import Model.BeneficiarModel;

public class BeneficiarControl {
	
	private BeneficiarDAO dao = new BeneficiarDAO();
	
	public boolean insert(String idProduto, Date data, String produto, String descricao, String  lote,  String talhao, float entrada, float saida, float quebra) throws SQLException{
		BeneficiarModel novo = new BeneficiarModel(idProduto, data, produto, descricao, lote, talhao, entrada, saida, quebra);
		dao.insertBeneficiar(novo);
		return dao.insert(novo);
		
	}
	
	public boolean finaliza(Date data, String produto, String descricao, String  lote,  String talhao, float entrada, float saida, float rendimento, float valor, String idProduto, int idBeneficiar) throws SQLException{
		BeneficiarModel novo = new BeneficiarModel(data, produto, descricao, lote, talhao, entrada, saida, rendimento, valor, idProduto, idBeneficiar);
		dao.insereRelatorioBeneficiado(novo);
		return dao.finaliza(novo);
		
	}
	

}
