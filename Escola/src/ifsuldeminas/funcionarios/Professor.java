package ifsuldeminas.funcionarios;

public class Professor {
	private String nome;
	private int suap;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSuap() {
		return suap;
	}

	public void setSuap(int suap) {
		if (suap >= 1000 && suap <= 9999)
			this.suap = suap;
	}

	public Professor(String nome, int suap) {
		setNome(nome);
		setSuap(suap);
	}

	public void exibirProfessor() {
		System.out.println("Professor: " + getNome() + "\n" + "SUAP: " + getSuap());
	}
}
