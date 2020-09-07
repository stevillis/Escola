package ifsuldeminas.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import ifsuldeminas.academico.Disciplina;
import ifsuldeminas.alunos.Aluno;
import ifsuldeminas.funcionarios.Professor;

public class Main {
	public static void main(String[] args) {
		// 1
		Disciplina disciplina1 = new Disciplina("História do Brasil", 1, 2, 30);

		// 2
		Aluno aluno1 = new Aluno("Roberto Araújo Junqueira", 1020, "Direito");
		Aluno aluno2 = new Aluno("Roberta Mirando Silva", 1021, "Ciências Sociais");
		Aluno aluno3 = new Aluno("Alessandra Monteiro de Souza", 1022, "Psicologia");
		Aluno aluno4 = new Aluno("Arnaldo Cezar Coelho", 1023, "História");
		Aluno aluno5 = new Aluno("Arnaldo Antunes de Oliveira", 1024, "Administração");
		Aluno aluno6 = new Aluno("Jocelio Cestari Arruda", 1025, "Ciências Contábeis");
		Aluno aluno7 = new Aluno("Marcela Duany Kerkhof", 1026, "Serviço Social");
		Aluno aluno8 = new Aluno("Bianca Fronkzack Moreira", 1027, "Arquitetura e Urbanismo");
		Aluno aluno9 = new Aluno("Pamella Ribeiro Albuquerque", 1028, "Marketing");
		Aluno aluno10 = new Aluno("Bruno de Oliveira Lemos", 1029, "Publicidade e Propaganda");

		// 3
		disciplina1.matricularAluno(aluno1);
		disciplina1.matricularAluno(aluno2);
		disciplina1.matricularAluno(aluno3);
		disciplina1.matricularAluno(aluno4);
		disciplina1.matricularAluno(aluno5);
		disciplina1.matricularAluno(aluno6);
		disciplina1.matricularAluno(aluno7);
		disciplina1.matricularAluno(aluno8);
		disciplina1.matricularAluno(aluno9);
		disciplina1.matricularAluno(aluno10);

		// 4
		ArrayList<Double> notas = new ArrayList<Double>();
		notas.add(3.0);
		notas.add(4.2);
		notas.add(6.0);
		notas.add(6.5);
		notas.add(10.0);
		notas.add(9.0);
		notas.add(5.0);
		notas.add(8.0);
		notas.add(7.3);
		notas.add(9.9);
		disciplina1.setNotas(notas);

		// 5
		Professor professor1 = new Professor("Leandro Muniz Pessoa", 9000);

		// 6
		disciplina1.setProfessor(professor1);

		// 7
		Scanner scanner = new Scanner(System.in);
		int opcao = 1;
		String mediaDisciplina = new DecimalFormat("#.00").format(disciplina1.calcularMedia());

		do {
			System.out.print("Escolha uma op��o: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				disciplina1.exibirDisciplina();
				break;
			case 2:
				System.out.println("Número de alunos na Disciplina: " + disciplina1.getNumeroAlunos());
				System.out.println("Número de alunos aprovados: " + disciplina1.getQuantidadeAprovados());
				System.out.println("Número de alunos reprovados: " + disciplina1.getQuantidadeReprovados());
				break;
			case 3:
				disciplina1.exibirAprovados();
				break;
			case 4:
				disciplina1.exibirReprovados();
				break;
			case 5:
				disciplina1.exibirMaiorNota();
				disciplina1.exibirMenorNota();
				break;
			case 6:
				disciplina1.exibirProfessor();
				break;
			case 7:
				System.out.println("Média total da turma: " + mediaDisciplina);
				disciplina1.exibirAlunosAcimaMedia();
				break;
			case 8:
				System.out.println("Média total da turma: " + mediaDisciplina);
				disciplina1.exibirAlunosAbaixoMedia();
				break;
			case 9:
				disciplina1.exibirAlunoNotaAproveitamento();
				break;
			case 10:
				disciplina1.exibirOrdenadosPorNota();
				break;
			case 11:
				System.out.println("Informe o nome do aluno: ");
				String nome = scanner.nextLine();

				System.out.println("Informe o RA do aluno (entre 1000 e 9999): ");
				int ra = scanner.nextInt();
				System.out.println(ra);
				scanner.nextLine();

				System.out.println("Informe o curso do aluno: ");
				String curso = scanner.nextLine();

				Aluno alunoNovo = new Aluno(nome, ra, curso);
				if (disciplina1.matricularAluno(alunoNovo)) {
					System.out.println("Informe a nota do aluno (entre 0 e 10): ");
					double nota = scanner.nextDouble();
					System.out.println(nota);
					disciplina1.addNota(disciplina1.getNumeroAlunos() - 1, nota);

					System.out.println("Informe a frequência do aluno: ");
					int frequencia = scanner.nextInt();
					scanner.nextLine();
					disciplina1.addFrequencia(disciplina1.getNumeroAlunos() - 1, frequencia);
				}

				break;
			}
		} while (opcao != 0);

		disciplina1.desmatricularAlunos();
		disciplina1.removerProfessor(professor1);
		scanner.close();
		System.out.println("Programa finalizado!");
	}
}
