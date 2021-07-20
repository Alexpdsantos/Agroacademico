package Model;

import java.util.Date;

public class VenderModel {
	private String estoque;
	private int id;
	private String idBeneficiado;
	private String produto;
	private String descricao;
	private String talhao;
	private String lote;
	private String tipo;
	private String qualidade;
	private float quantidade;
	private double precoKg;
	private int desconto;
	private double valorTotal;
	private String cliente;
	private Date data;
	
	public VenderModel() {
		super();
		this.id = id;

		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.tipo = tipo;
		this.talhao = talhao;
		this.qualidade = qualidade;
		this.quantidade = quantidade;
		this.precoKg = precoKg;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.data = data;
	}
	
	public VenderModel(String produto, String descricao, String talhao, String lote, String tipo, String qualidade, float quantidade,
			double precoKg, int desconto, double valorTotal, String cliente, Date data, int id) {
		super();

		this.produto = produto;
		this.descricao = descricao;
		this.talhao = talhao;
		this.lote = lote;
		this.tipo = tipo;
		this.qualidade = qualidade;
		this.quantidade = quantidade;
		this.precoKg = precoKg;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.data = data;
		this.id = id;
	}

	public VenderModel(String idBeneficiado, String produto, String descricao, String talhao, String lote, String tipo, String qualidade, float quantidade,
			double precoKg, int desconto, double valorTotal, String cliente, Date data) {
		super();
		this.idBeneficiado = idBeneficiado;
		this.produto = produto;
		this.descricao = descricao;
		this.talhao = talhao;
		this.lote = lote;
		this.tipo = tipo;
		this.qualidade = qualidade;
		this.quantidade = quantidade;
		this.precoKg = precoKg;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.data = data;
	}
	

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public String getIdBeneficiado() {
		return idBeneficiado;
	}

	public void setIdBeneficiado(String idBeneficiado) {
		this.idBeneficiado = idBeneficiado;
	}

	public String getTalhao() {
		return talhao;
	}

	public void setTalhao(String talhao) {
		this.talhao = talhao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getQualidade() {
		return qualidade;
	}

	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoKg() {
		return precoKg;
	}

	public void setPrecoKg(double precoKg) {
		this.precoKg = precoKg;
	}

	public int getDesconto() {
		return desconto;
	}

	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
