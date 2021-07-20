package Model;

import java.util.Date;

public class BeneficiadoModel {

	private int id;
	private Date data;
	private int produto;
	private String descricao;
	private String lote;
	private String talhao;
	private float quantidade;
	private String qualidade;
	private String tipo;
	private float umidade;
	private float rendimento;

	public BeneficiadoModel() {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.quantidade = quantidade;
		this.qualidade = qualidade;
		this.tipo = tipo;
		this.umidade = umidade;
		this.rendimento = rendimento;
		this.id = id;
	}

	public BeneficiadoModel(Date data, int produto, String descricao,String lote,String talhao, float quantidade, String qualidade,
			String tipo, float umidade, float rendimento) {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.quantidade = quantidade;
		this.qualidade = qualidade;
		this.tipo = tipo;
		this.umidade = umidade;
		this.rendimento = rendimento;
	}

	public BeneficiadoModel(Date data, int produto, String descricao, String lote,String talhao, float quantidade, String qualidade,
			String tipo, float umidade, float rendimento, int id) {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.quantidade = quantidade;
		this.qualidade = qualidade;
		this.tipo = tipo;
		this.umidade = umidade;
		this.rendimento = rendimento;
		this.id = id;
	}
	

	public String getTalhao() {
		return talhao;
	}

	public void setTalhao(String talhao) {
		this.talhao = talhao;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getProduto() {
		return produto;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getQualidade() {
		return qualidade;
	}

	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getUmidade() {
		return umidade;
	}

	public void setUmidade(float umidade) {
		this.umidade = umidade;
	}

	public float getRendimento() {
		return rendimento;
	}

	public void setRendimento(float rendimento) {
		this.rendimento = rendimento;
	}
}
