package br.edu.ufcg.computacao.mrbet;

import java.util.Scanner;

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
			incluiTime(mb, scanner);
			break;
		case "R":
			recuperaTime(mb);
			break;
		case ".":
			detalharFilme(mb, scanner);
			break;
		case "B":
			exibirHotList(mb);
			break;
		case "E":
			adicionarHot(mb, scanner);
			break;
		case "T":
			removerHot(mb, scanner);
			break;
		case "!":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}
	
	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}
		
}
