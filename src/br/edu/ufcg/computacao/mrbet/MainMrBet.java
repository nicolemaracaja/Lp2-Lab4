package br.edu.ufcg.computacao.mrbet;

import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema MrBet.
 * 
 * @author Nicole Brito Maracajá
 */
public class MainMrBet {

	public static void main(String[] args) {
		MrBetSistema mb = new MrBetSistema();
		Scanner scanner = new Scanner(System.in);
		
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, mb, scanner);
		}
	}
		
	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário/a.
	 * @return O comando escolhido.
	 */	
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(M)Minha inclusão de times\n" + 
						"(R)Recuperar Time\n" + 
						"(.)Adicionar campeonato\n" + 
						"(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n" +
						"(E)Exibir campeonatos que o time participa\n" +
						"(T)Tentar a sorte e status\n" +
						"(!)Já pode fechar o programa\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}
	
	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param mb  O sistema MrBet que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, MrBetSistema mb, Scanner scanner) {
		switch (opcao) {
		case "M":
			incluiTime(scanner, mb);
			break;
		case "R":
			recuperaTime(scanner, mb);
			break;
		case ".":
			adicionaCampeonato(scanner, mb);
			break;
		case "B":
			incluiTimeCampeonato(scanner, mb);
			break;
		case "E":
			exibeTimes(scanner, mb);
			break;
		case "T":
			tentaSorteStatus(scanner, mb);
			break;
		case "!":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	/**
	 * Inclui time no sistema MrBet.
	 * @param Scanner.
	 * @param MrBetSistema.
	 */
	private static void incluiTime(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("Código: ");
		scanner.nextLine();
		String codigo = scanner.nextLine();
		System.out.println("Nome: ");
		String nome = scanner.nextLine();
		System.out.println("Mascote: ");
		String mascote = scanner.nextLine();
		
		mb.cadastraTime(codigo, nome, mascote);
	}
	
	/**
	 * Retorna a representação textual do time.
	 * @param Scanner.
	 * @param MrBetSistema.
	 */
	private static void recuperaTime(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("Código: ");
		scanner.nextLine();
		String codigo = scanner.nextLine();
		System.out.println(mb.recuperaTime(codigo).toString());
	}

	/**
	 * Adiciona um campeonato ao sistema MrBet.
	 * @param Scanner
	 * @param MrBetSistema.
	 */
	private static void adicionaCampeonato(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("Campeonato: ");
		scanner.nextLine();
		String nome = scanner.nextLine();
		System.out.println("Participantes: ");
		int participantes = scanner.nextInt();
		
		System.out.println(mb.adicionaCampeonato(nome, participantes));
	}
	
	/**
	 * Inclui um time em um campeonato.
	 * @param Scanner.
	 * @param MrBetSistema.
	 */
	private static void incluiTimeCampeonato(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato?" );
		char escolha = scanner.next().toLowerCase().charAt(0);
		
		if (escolha == 'I') {
			
			System.out.println("Código: ");
			scanner.nextLine();
			String codigo = scanner.nextLine();
			System.out.println("Campeonato: ");
			String campeonato = scanner.nextLine();
			System.out.println(mb.adicionaTimeEmCampeonato(codigo, campeonato));
		}
		
		if (escolha == 'V') {
			
			System.out.println("Código: ");
			scanner.nextLine();
			String codigo = scanner.nextLine();
			System.out.println("Campeonato: ");
			String campeonato = scanner.nextLine();
			System.out.println(mb.verificaTimeEmCampeonato(codigo, campeonato));
		}
	}

	private static void exibeTimes(Scanner scanner, MrBetSistema mb) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}
		
}
