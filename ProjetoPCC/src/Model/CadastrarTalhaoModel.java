package Model;

public class CadastrarTalhaoModel {
	
	private String talhao;
	private int quantPes;
	private String espacamento;
	private String area;
	private String variedade;
	
	public CadastrarTalhaoModel() {

	}

	public CadastrarTalhaoModel(String talhao, int quantPes, String espacamento, String area, String variedade) {
		super();
		this.talhao = talhao;
		this.quantPes = quantPes;
		this.espacamento = espacamento;
		this.area = area;
		this.variedade = variedade;
	}

	public String getTalhao() {
		return talhao;
	}

	public void setTalhao(String talhao) {
		this.talhao = talhao;
	}

	public int getQuantPes() {
		return quantPes;
	}


	public void setQuantPes(int quantPes) {
		this.quantPes = quantPes;
	}


	public String getEspacamento() {
		return espacamento;
	}


	public void setEspacamento(String espacamento) {
		this.espacamento = espacamento;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getVariedade() {
		return variedade;
	}


	public void setVariedade(String variedade) {
		this.variedade = variedade;
	}


	@Override
	public String toString() {
		return String.valueOf(talhao);
	}
	
	


	
	
}
