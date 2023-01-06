package attornatus.model;

public enum StatusEndereco {
	
	PRINCIPAL("principal"),
	SECUNDARIO("secundario"),
	INATIVO("inativo");
	
private  String descricao;
	
	StatusEndereco(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

		
}
