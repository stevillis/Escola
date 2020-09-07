package ifsuldeminas.alunos;

public class Aluno {
	private String nome;
	private int ra;
	private int periodo;
	private String curso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getRa() {
		return ra;
	}

	public boolean setRa(int ra) {
		if (ra >= 1000 && ra <= 9999) {
			this.ra = ra;
			return true;
		} else
			return false;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Aluno(String nome, int ra, String curso) {
		setNomeRACurso(nome, ra, curso);
		this.periodo = 1;
	}

	public Aluno(String nome, int ra, int periodo, String curso) {
		setNomeRACurso(nome, ra, curso);
		this.periodo = periodo;
	}

	private void setNomeRACurso(String nome, int ra, String curso) {
		setNome(nome);
		setRa(ra);
		setCurso(curso);
	}

	public void exibirAluno() {
		System.out.println("Aluno: " + getNome() + "\n" + "RA: " + getRa() + "\n" + "Curso: " + getCurso() + "\n"
				+ "Período: " + getPeriodo());
	}
}
