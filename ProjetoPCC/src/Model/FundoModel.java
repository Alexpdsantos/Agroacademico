package Model;

import java.util.Date;

public class FundoModel {
	private int id;
	private Date data;
	private int produto;
	private String descricao;
	private int lote;
	private float quantidade;
	private String qualidade;


	
	
	public FundoModel() {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.quantidade = quantidade;
		this.qualidade = qualidade;
		this.id = id;
	}

	public FundoModel(Date data, int produto, String descricao, int lote, float quantidade, String qualidade) {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.quantidade = quantidade;
		this.qualidade = qualidade;
	}

	public FundoModel(Date data, int produto, String descricao, int lote, float quantidade, String qualidade, int id) {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.quantidade = quantidade;
		this.qualidade = qualidade;
		this.id = id;
	}
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getProduto() {
		return produto;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}
	public String getQualidade() {
		return qualidade;
	}
	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	

	@Override
	public String toString() {
		return "FundoModel [data=" + data + ", produto=" + produto + ", descricao=" + descricao + ", lote=" + lote
				+ ", fundo=" + quantidade + "]";
	}
	
	
	
	
	
}
