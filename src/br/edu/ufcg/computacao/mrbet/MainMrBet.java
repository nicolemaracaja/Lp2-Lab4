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
			exibeCampeonatos(scanner, mb);
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
		String idTime = scanner.nextLine().toUpperCase();
		System.out.println("Nome: ");
		String nome = scanner.nextLine();
		System.out.println("Mascote: ");
		String mascote = scanner.nextLine();
	
		try {
			System.out.println(mb.cadastraTime(idTime, nome, mascote)); 		
		}catch(IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
	}
	
	/**
	 * Retorna a representação textual do time.
	 * @param Scanner.
	 * @param MrBetSistema.
	 */
	private static void recuperaTime(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("Código: ");
		scanner.nextLine();
		String idTime = scanner.nextLine().toUpperCase();
		System.out.println(mb.recuperaTime(idTime).toString());
	}

	/**
	 * Adiciona um campeonato ao sistema MrBet.
	 * @param Scanner
	 * @param MrBetSistema.
	 */
	private static void adicionaCampeonato(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("Campeonato: ");
		scanner.nextLine();
		String nome = scanner.nextLine().toUpperCase();
		System.out.println("Participantes: ");
		int participantes = scanner.nextInt();
		
		try {
			System.out.println(mb.adicionaCampeonato(nome, participantes)); 		
		}catch(IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
	}
	/**
	 * Inclui um time em um campeonato.
	 * @param Scanner.
	 * @param MrBetSistema.
	 */
	private static void incluiTimeCampeonato(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato?" );
		String escolha = scanner.next().toUpperCase();
		
		if (escolha.equals("I")) {
			
			System.out.println("Código: ");
			scanner.nextLine();
			String idTime = scanner.nextLine().toUpperCase();
			System.out.println("Campeonato: ");
			String campeonato = scanner.nextLine().toUpperCase();
			
			try {
				mb.podeAdicionarCampeonato(idTime, campeonato);
			}catch(IllegalArgumentException iae) {
				String msg = iae.getMessage();
				
				if(msg.equals("TIME NÃO EXISTE!")) {
					System.out.println("TIME NÃO EXISTE!");
					return;
				}else if(msg.equals("CAMPEONATO NÃO EXISTE!")) {
					System.out.println("CAMPEONATO NÃO EXISTE!");
					return;
				}else if (msg.equals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!")) {	
					System.out.println("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS");
					return;
				}	
			}
			System.out.println("TIME INCLUíDO NO CAMPEONATO!");
			}
		
		if (escolha.equals("V")) {
			
			System.out.println("Código: ");
			scanner.nextLine();
			String idTime = scanner.nextLine().toUpperCase();
			System.out.println("Campeonato: ");
			String campeonato = scanner.nextLine().toUpperCase();
			
			try {
				mb.verificaTimeEmCampeonato(idTime, campeonato);
			}catch(IllegalArgumentException iae) {
				String msg = iae.getMessage();
				
				if(msg.equals("TIME NÃO EXISTE!")) {
					System.out.println("TIME NÃO EXISTE!");
					return;
				}else if(msg.equals("CAMPEONATO NÃO EXISTE!")) {
					System.out.println("CAMPEONATO NÃO EXISTE!");
					return;
				}else if (msg.equals("TIME NÃO ESTÁ NO CAMPEONATO!")) {
					System.out.println("TIME NÃO ESTÁ NO CAMPEONATO!");
					return;
				}	
			}
			System.out.println("TIME ESTÁ NO CAMPEONATO!");
		}
	}

	/**
	 * Exibe os campeonatos que um determinado time faz parte.
	 * @param Scanner.
	 * @param MrBetSistema.
	 */
	private static void exibeCampeonatos(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("Time: ");
		scanner.nextLine();
		String idTime = scanner.nextLine();
		System.out.println(mb.exibeCampeonatosTime(idTime).toString());
		
	}
	
	/**
	 * Realiza uma aposta ou mostra a lista de apostas já feitas.
	 * @param Scanner.
	 * @param MrBetSistema.
	 */
	private static void tentaSorteStatus(Scanner scanner, MrBetSistema mb) {
		
		System.out.println("(A)Apostar ou (S)Status das Apostas? ");
		String escolha = scanner.next().toUpperCase();
		
		if (escolha.equals("A")) {
			System.out.println("Código: ");
			scanner.nextLine();
			String idTime = scanner.nextLine();
			System.out.println("Campeonato: ");
			String nomeCampeonato = scanner.nextLine();
			System.out.println("Colocação: ");
			int colocacao = scanner.nextInt();
			System.out.println("Valor da aposta: ");
			double valorAposta = scanner.nextDouble();
			
			mb.adicionaAposta(idTime, nomeCampeonato, colocacao, valorAposta);
		}
		
		if (escolha.equals("S")) {
			System.out.println(mb.listaApostas().toString());
		}
		
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nPor hoje é só pessoal!");
		System.exit(0);
	}
		
}