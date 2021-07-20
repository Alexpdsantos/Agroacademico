package Controller;

import java.sql.SQLException;
import java.util.Date;

import DAO.VenderDAO;
import Model.VenderModel;

public class VenderControl {
	
	private VenderDAO dao = new VenderDAO();
	
	
	public boolean insert(String idBeneficiado, String produto, String descricao, String talhao, String lote, String tipo, String qualidade, float quantidade,
			double precoKg, int desconto, double valorTotal, String cliente, Date data) throws SQLException {
		VenderModel novo = new VenderModel(idBeneficiado, produto, descricao, talhao, lote, tipo, qualidade, quantidade, precoKg, desconto, valorTotal, cliente, data);
		dao.insertRelatorio(novo);
		return dao.insert(novo);
	}
	
	public boolean update(String produto, String descricao, String talhao, String lote, String tipo, String qualidade, float quantidade,
			double precoKg, int desconto, double valorTotal, String cliente, Date data, int id) throws SQLException {
		VenderModel update = new VenderModel(produto, descricao, talhao, lote, tipo, qualidade,	quantidade, precoKg, desconto, valorTotal, cliente, data, id);
		return dao.update(update);
	}

}
