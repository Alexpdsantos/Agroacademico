package Model;

public class CadastrarProdutoModel {
	private int codigo;
	private String descricao;

	public CadastrarProdutoModel() {
		super();
		this.codigo = codigo;
		this.descricao = descricao;

	}

	public CadastrarProdutoModel(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return String.valueOf(codigo);
	}

}
