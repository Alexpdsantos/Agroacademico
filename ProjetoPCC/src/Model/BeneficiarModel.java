package Model;

import java.util.Date;

public class BeneficiarModel {
	private int idBeneficiar;
	private String idProduto;
	private Date data;
	private String produto;
	private String descricao;
	private String lote;
	private String talhao;
	private float entrada;
	private float saida;
	private float rendimento;
	private float valor;

	public BeneficiarModel() {
		super();
		this.idBeneficiar = idBeneficiar;
		this.idProduto = idProduto;
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.entrada = entrada;
		this.saida = saida;
		this.rendimento = rendimento;
		this.valor = valor;

	}
	
	

	public BeneficiarModel(Date data, String produto, String descricao, String lote, String talhao, float entrada,
			float saida, float rendimento, float valor, String idProduto, int idBeneficiar) {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.entrada = entrada;
		this.saida = saida;
		this.rendimento = rendimento;
		this.valor = valor;
		this.idProduto = idProduto;
		this.idBeneficiar = idBeneficiar;
	}



	public BeneficiarModel(String idProduto, Date data, String produto, String descricao, String lote, String talhao,
			float entrada, float saida, float rendimento, int idBeneficiar) {
		super();
		this.idBeneficiar = idBeneficiar;
		this.idProduto = idProduto;
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.entrada = entrada;
		this.saida = saida;
		this.rendimento = rendimento;
	}

	public BeneficiarModel(String idProduto, Date data, String produto, String descricao, String lote, String talhao,
			float entrada, float saida, float rendimento) {
		super();
		this.idProduto = idProduto;
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.entrada = entrada;
		this.saida = saida;
		this.rendimento = rendimento;
	}

	public int getIdBeneficiar() {
		return idBeneficiar;
	}

	public void setIdBeneficiar(int idBeneficiar) {
		this.idBeneficiar = idBeneficiar;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getTalhao() {
		return talhao;
	}

	public void setTalhao(String talhao) {
		this.talhao = talhao;
	}

	public float getEntrada() {
		return entrada;
	}

	public void setEntrada(float entrada) {
		this.entrada = entrada;
	}

	public float getSaida() {
		return saida;
	}

	public void setSaida(float saida) {
		this.saida = saida;
	}

	public float getRendimento() {
		return rendimento;
	}

	public void setRendimento(float rendimento) {
		this.rendimento = rendimento;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
