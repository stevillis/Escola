package ifsuldeminas.academico;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ifsuldeminas.alunos.Aluno;
import ifsuldeminas.funcionarios.Professor;

public class Disciplina {
	private String nome;
	private int periodo;
	private int numAulasSemana;
	private int numTotalAulas;
	private double mediaDisciplina = 7.2;

	private Professor professor;
	private ArrayList<Aluno> alunos;
	private ArrayList<Double> notas;
	private ArrayList<Integer> frequencias;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getNumAulasSemana() {
		return this.numAulasSemana;
	}

	public void setNumAulasSemana(int numAulasSemana) {
		this.numAulasSemana = numAulasSemana;
	}

	public int getNumTotalAulas() {
		return this.numTotalAulas;
	}

	public void setNumTotalAulas(int numTotalAulas) {
		this.numTotalAulas = getNumAulasSemana() * numTotalAulas;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public ArrayList<Aluno> getAlunos() {
		return this.alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public ArrayList<Double> getNotas() {
		return this.notas;
	}

	public void setNotas(ArrayList<Double> notas) {
		for (int i = 0; i < this.alunos.size(); i++) {
			this.notas.set(i, notas.get(i));
		}
	}

	public ArrayList<Integer> getFrequencias() {
		return this.frequencias;
	}

	public void setFrequencias(ArrayList<Integer> frequencias) {
		this.frequencias = frequencias;
	}

	public Disciplina(String nome, int periodo, int numAulasSemana, int numSemanas) {
		setAtributosDefault(nome, periodo, numAulasSemana, numSemanas);
		this.professor = null;
	}

	public Disciplina(String nome, int periodo, int numAulasSemana, int numSemanas, Professor professor) {
		setAtributosDefault(nome, periodo, numAulasSemana, numSemanas);
		setProfessor(professor);
	}

	private void setAtributosDefault(String nome, int periodo, int numAulasSemana, int numSemanas) {
		setNome(nome);
		setPeriodo(periodo);
		setNumAulasSemana(numAulasSemana);
		setNumTotalAulas(numSemanas);

		alunos = new ArrayList<Aluno>();
		notas = new ArrayList<Double>();
		frequencias = new ArrayList<Integer>();
	}

	private boolean isValidIndex(ArrayList<?> array, int index) {
		return index >= 0 && index < array.size();
	}

	private boolean isAlunoMatriculado(Aluno alunoNovo) {
		for (Aluno alunoMatriculado : this.alunos) {
			if (alunoMatriculado.getRa() == alunoNovo.getRa()) {
				System.out.println("ERRO: Aluno já está matriculado!");
				return true;
			}
		}
		return false;
	}

	private boolean existsAlunosMatriculados() {
		if (this.alunos.size() > 0) {
			return true;
		} else {
			System.out.println("Disciplina não possui alunos matriculados");
			return false;
		}
	}

	public boolean matricularAluno(Aluno alunoNovo) {
		boolean alunoJaMatriculado = isAlunoMatriculado(alunoNovo);
		boolean RAInvalido = !alunoNovo.setRa(alunoNovo.getRa());
		if (alunoJaMatriculado || RAInvalido) {
			if (RAInvalido)
				System.out.println("ERRO: RA inválido");

			return false;
		}

		this.alunos.add(alunoNovo);
		this.notas.add(0.0);
		this.frequencias.add(0);
		System.out.println("Aluno matriculado com sucesso!");

		return true;
	}

	public boolean desmatricularAluno(int posAluno) {
		if (isValidIndex(this.alunos, posAluno)) {
			this.alunos.remove(posAluno);
			return true;
		}
		return false;
	}

	public void removerProfessor(Professor professor) {
		System.out.println("Removendo o professor da Disciplina...");
		setProfessor(null);
	}

	public int getNumeroAlunos() {
		return this.alunos.size();
	}

	public boolean addNota(int posAluno, double nota) {
		if (nota >= 0d && nota <= 10d) {
			this.notas.set(posAluno, nota);
			System.out.println("Nota adicionada com sucesso!");

			return true;
		}
		System.out.println("ERRO: Nota inválida!");
		return false;
	}

	public boolean addFrequencia(int posAluno, int frequenciaTotal) {
		if (frequenciaTotal >= 0 && frequenciaTotal <= getNumTotalAulas()) {
			this.frequencias.set(posAluno, frequenciaTotal);
			System.out.println("Frequência adicionada com sucesso!");
			return true;
		}
		System.out.println("ERRO: Frequência inválida!");
		return false;
	}

	public boolean estaAprovado(int posAluno) {
		if (this.notas.get(posAluno) >= 6 && this.getAproveitamentoAluno(posAluno) >= 75 / 100)
			return true;
		else
			return false;
	}

	public void exibirReprovados() {
		if (existsAlunosMatriculados()) {
			if (getQuantidadeReprovados() == 0)
				System.out.println("Não há alunos reprovados!");
			else {
				for (int i = 0; i < alunos.size(); i++) {
					if (!this.estaAprovado(i))
						System.out.println(this.alunos.get(i).getNome() + " está reprovado!" + "\n" + "Nota: "
								+ this.notas.get(i) + "\n" + "Frequência: " + frequencias.get(i) + "\n");
				}
			}
		}
	}

	public void exibirAprovados() {
		if (existsAlunosMatriculados()) {
			if (getQuantidadeAprovados() == 0) {
				System.out.println("Não há alunos aprovados!");
			} else {
				for (int i = 0; i < alunos.size(); i++) {
					if (this.estaAprovado(i)) {
						System.out.println(this.alunos.get(i).getNome() + " está aprovado!" + "\n" + "Nota: "
								+ this.notas.get(i) + "\n" + "Frequência: " + frequencias.get(i) + "\n");
					}
				}
			}
		}
	}

	public double calcularMedia() {
		if (!notas.isEmpty()) {
			double somaNotas = 0;
			for (Double nota : notas) {
				somaNotas += nota;
			}

			return somaNotas / notas.size();
		}
		return 0;
	}

	public void exibirMaiorNota() {
		if (!this.notas.isEmpty()) {
			double maiorNota = this.notas.get(0);
			int indiceAlunoMaiorNota = 0;
			for (int i = 0; i < this.notas.size(); i++) {
				if (this.notas.get(i) > maiorNota) {
					maiorNota = this.notas.get(i);
					indiceAlunoMaiorNota = i;
				}
			}
			System.out.println("Aluno com a maior nota: " + this.alunos.get(indiceAlunoMaiorNota).getNome() + "\n"
					+ "Nota: " + maiorNota + "\n");
		} else {
			System.out.println("Não há notas!");
		}
	}

	public void exibirMenorNota() {
		if (!this.notas.isEmpty()) {
			double menorNota = this.notas.get(0);
			int indiceAlunoMenorNota = 0;
			for (int i = 0; i < this.notas.size(); i++) {
				if (this.notas.get(i) < menorNota) {
					menorNota = this.notas.get(i);
					indiceAlunoMenorNota = i;
				}
			}
			System.out.println("Aluno com a menor nota: " + this.alunos.get(indiceAlunoMenorNota).getNome() + "\n"
					+ "Nota: " + menorNota + "\n");
		} else {
			System.out.println("N�o há notas!");
		}
	}

	public int getQuantidadeReprovados() {
		int quantidadeReprovados = 0;
		if (!this.alunos.isEmpty()) {
			for (int i = 0; i < alunos.size(); i++) {
				if (!this.estaAprovado(i)) {
					quantidadeReprovados++;
				}
			}
		}
		return quantidadeReprovados;
	}

	public int getQuantidadeAprovados() {
		int quantidadeAprovados = 0;
		if (!this.alunos.isEmpty()) {
			for (int i = 0; i < alunos.size(); i++) {
				if (this.estaAprovado(i)) {
					quantidadeAprovados++;
				}
			}
		}
		return quantidadeAprovados;
	}

	public void exibirProfessor() {
		if (this.professor != null)
			System.out.println(
					"Professor: " + this.professor.getNome() + "\n" + "SUAP: " + this.professor.getSuap() + "\n");
		else
			System.out.println("Disciplina não possui professor");
	}

	public double getAproveitamentoAluno(int posAluno) {
		if (isValidIndex(this.frequencias, posAluno)) {
			return this.frequencias.get(posAluno);
		}
		return 0;
	}

	public double getNotaAluno(int posAluno) {
		if (isValidIndex(this.notas, posAluno)) {
			return this.notas.get(posAluno);
		}
		return 0d;
	}

	public void exibirAlunosAcimaMedia() {
		if (existsAlunosMatriculados()) {
			System.out.println("----- Alunos acima da média -----");
			for (int i = 0; i < this.notas.size(); i++) {
				if (this.notas.get(i) >= mediaDisciplina) {
					this.alunos.get(i).exibirAluno();
					System.out.println("Nota: " + this.notas.get(i) + "\n");
				}
			}
		}
	}

	public void exibirAlunosAbaixoMedia() {
		if (existsAlunosMatriculados()) {
			System.out.println("----- Alunos abaixo da m�dia -----");
			for (int i = 0; i < this.notas.size(); i++) {
				if (this.notas.get(i) < mediaDisciplina) {
					this.alunos.get(i).exibirAluno();
					System.out.println("Nota: " + this.notas.get(i) + "\n");
				}
			}
		}
	}

	public void exibirAlunoNotaAproveitamento() {
		if (existsAlunosMatriculados()) {
			for (int i = 0; i < this.alunos.size(); i++) {
				System.out.println("Aluno: " + this.alunos.get(i).getNome() + "\n" + "Nota: " + this.getNotaAluno(i)
						+ "\n" + "Aproveitamento: " + this.getAproveitamentoAluno(i) + "\n");
			}
		}
	}

	public void desmatricularAlunos() {
		System.out.println("Desmatriculando todos os alunos...");
		this.alunos.clear();
		this.notas.clear();
		this.frequencias.clear();
	}

	public void exibirOrdenadosPorNota() {
		if (this.existsAlunosMatriculados()) {
			// Bubble Sort
			double notaAux;
			Aluno alunoAux;
			for (int i = 0; i < this.notas.size(); i++) {
				for (int j = 0; j < this.notas.size() - 1 - i; j++) {
					if (this.getNotaAluno(j) < this.getNotaAluno(j + 1)) {
						// Ordena as notas
						notaAux = this.notas.get(j);
						this.notas.set(j, this.getNotaAluno(j + 1));
						this.notas.set(j + 1, notaAux);

						// Ordena os alunos
						alunoAux = this.alunos.get(j);
						this.alunos.set(j, this.alunos.get(j + 1));
						this.alunos.set(j + 1, alunoAux);
					}
				}
			}

			for (int i = 0; i < this.alunos.size(); i++)
				System.out
						.println("Aluno: " + this.alunos.get(i).getNome() + "\n" + "Nota: " + this.notas.get(i) + "\n");
		}
	}

	public void exibirDisciplina() {
		if (this.existsAlunosMatriculados()) {
			System.out.println("----- Disciplina -----");
			System.out.println(
					"Nome: " + this.getNome() + "\n" + "Período: " + this.getPeriodo() + "\n" + "Aulas na Semana: "
							+ this.getNumAulasSemana() + "\n" + "Total de aulas: " + this.getNumTotalAulas());

			System.out.println("--- Professor ---");
			this.getProfessor().exibirProfessor();

			System.out.println("----- Alunos -----");
			this.exibirAlunoNotaAproveitamento();

			System.out.println("Média total da turma: " + new DecimalFormat("#.00").format(this.calcularMedia()));
			System.out.println("Quantidade de alunos: " + this.getNumeroAlunos());
			System.out.println("Quantidade de alunos aprovados: " + this.getQuantidadeAprovados());
			System.out.println("Quantidade de alunos reprovados: " + this.getQuantidadeReprovados());
		}
	}
}