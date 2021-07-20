package Model;

import java.util.Date;

public class ProdutoModel {
	private Date data;
	private int id;
	private int produto;
	private String lote;
	private float quantidade;
	private String talhao;
	private String descricao;

	public ProdutoModel() {
		super();
		this.data = data;
		this.id = id;
		this.produto = produto;
		this.lote = lote;
		this.quantidade = quantidade;
		this.talhao = talhao;
		this.descricao = descricao;

	}

	public ProdutoModel(Date data, int produto, String descricao, String lote, String talhao, float quantidade) {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.quantidade = quantidade;

	}

	public ProdutoModel(Date data, int produto, String descricao, String lote, String talhao, float quantidade, int id) {
		super();
		this.data = data;
		this.produto = produto;
		this.descricao = descricao;
		this.lote = lote;
		this.talhao = talhao;
		this.quantidade = quantidade;
		this.id = id;

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

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "telaInicial [Id=" + id + "Data=" + data + ", Produto=" + produto + ", Descrição=" + descricao
				+ ", Lote=" + lote + ", Talhao=" + talhao + " ,Quantidade=" + quantidade + "]";
	}
	
	//Método importante para avaliar se dois objetos são iguais, 
	//utilizando automaticamente pelos métodos remove e contains
	/*@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//return super.equals(obj);  <<< Não é o que eu quero...retorna comaração por endereço
		
		//arg0 ou obj que foi instanciado em public boolean equals(Object obj) 
		//é o produto que será cadastrado/removido/avaliado
		//this é o produto já contido na coleção ou que chama o método
		
		if(!(obj instanceof ProdutoModel))
			return false;
		
		String loteExistente = this.lote;
		String loteASerValidado = ((ProdutoModel)obj).lote;//Downcasting entre obj e Produto
		return loteASerValidado.equals(loteExistente);
	}
	
	public int hashCode(){
		//inicialize uma variável auxiliar com um numero primo
		int aux = 11;
		
		//para cada atributo a ser analisado equals, adicione
		//o código hash multiplicando por outro primo
		
		aux = aux+this.lote.hashCode()*17;
		return aux;
	}
	*/

}