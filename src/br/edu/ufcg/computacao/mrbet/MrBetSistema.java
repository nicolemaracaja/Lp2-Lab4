package br.edu.ufcg.computacao.mrbet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MrBetSistema, que implementa as funções incorporadas no Main.
 * 
 * @author Nicole Brito Maracajá - 123111413.
 */
public class MrBetSistema {
	
	/**
	 * HashMap dos times, representados pelo seu id.
	 */
	private HashMap<String, Time> times;
	
	/**
	 * HashMap dos campeonatos, representados pelo seu nome.
	 */
	private HashMap<String, Campeonato> campeonatos; 
	
	/**
	 * ArrayList das apostas.
	 */
	private ArrayList<Aposta> apostas;
	
	/**
	 * Index de uma aposta.
	 */
	private int idAposta;
	
	/**
	 * Cria o MrBetSistema.
	 */
	public MrBetSistema() {
		this.times = new HashMap<>();
		this.campeonatos = new HashMap<>();
		this.apostas = new ArrayList<>();
		this.idAposta = 0;
	}
	
	/**
	 * Cadastra um time no sistema.
	 * @param id Identificador do time.
	 * @param nome Nome do time.
	 * @param mascote Mascote do time.
	 */
	public String cadastraTime(String id, String nome, String mascote) {
		Time time = new Time(id, nome, mascote);
			
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("CÓDIGO OBRIGATÓRIO!");
		}
		if (times.containsKey(id.toUpperCase())) {
			throw new IllegalArgumentException("TIME JÁ EXISTE!");
		}
		times.put(id.toUpperCase(), time);
		return "INCLUSÃO REALIZADA!";
	}
	
	/**
	 * Recupera o time do identificador.
	 * @param id Identificador do time.
	 * @return ToString com a representação textual do time.
	 */
	public String recuperaTime(String id) {
		
		if(this.times.get(id) != null) {
			return this.times.get(id).toString();
		}
		return "TIME NÃO EXISTE!";
	}
	
	/**
	 * Adiciona um campeonato ao sistema.
	 * @param nome Nome do campeonato.
	 * @param qtdParticipantes Quantidade máxima de participantes que podem participar do campeonato.
	 * @return Uma String com o status da ação.
	 */
	public String adicionaCampeonato(String nome, int qtdParticipantes) {
		Campeonato campeonato = new Campeonato(nome, qtdParticipantes);
		
		if(campeonatos.containsKey(nome)) {
			throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!");
		}else if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("NOME OBRIGATÓRIO!");
		}else {
			campeonatos.put(nome, campeonato);
			return "CAMPEONATO ADICIONADO!";
		}
	}
	
	/**
	 * Recupera o campeonato pelo nome.
	 * @param nome Nome do campeonato.
	 * @return ToString com a representação textual do campeonato.
	 */
	public String recuperaCampeonato(String nome) {
		
		if(this.campeonatos.get(nome) != null) {
			return this.campeonatos.get(nome).toString();
		}
		return "CAMPEONATO NÃO EXISTE!";
	}
	
	/**
	 * Verifica as condições para incluir um time no campeonato.
	 * @param idTime O identificador do timne.
	 * @param campeonato O nome do campeonato.
	 * @return true ou false.
	 */
	public boolean podeAdicionarCampeonato(String idTime, String campeonato) {
		
		if (!times.containsKey(idTime)) {
			throw new NullPointerException("TIME NÃO EXISTE!");
		}
		if (!campeonatos.containsKey(campeonato)) {
			throw new NullPointerException("CAMPEONATO NÃO EXISTE!");
		}
		if (campeonatos.get(campeonato).getParticipantes() >= campeonatos.get(campeonato).getMaxParticipantes()) {
			throw new IndexOutOfBoundsException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
		}
		return true;
	}
	
	/**
	 * Adiciona times a um campeonato.
	 * @param idTime Código do time.
	 * @param campeonato Nome do campeonato.
	 * @return Uma string com o status da ação.
	 */
	public String adicionaTimeEmCampeonato(String idTime, String campeonato) {
		try {
			campeonatos.get(campeonato).adicionaTime(times.get(idTime));
			campeonatos.get(campeonato).incrementaParticipantes(times.get(idTime));
		}catch(NullPointerException | IndexOutOfBoundsException | IllegalArgumentException iae) {
			return iae.getMessage();
		}
		return "TIME INCLUÍDO NO CAMPEONATO!";
	}
	
	/**
	 * Verifica se o time está participando do campeonato.
	 * @param idTime Identificador do time.
	 * @param campeonato Campeonato que será verificado.
	 * @return Uma string com o status da ação.
	 */
	public boolean verificaTimeEmCampeonato(String idTime, String campeonato) {
		
		if(!campeonatos.containsKey(campeonato)) {
			throw new NullPointerException("CAMPEONATO NÃO EXISTE!");
		}else if(!times.containsKey(idTime)) { 
			throw new NullPointerException("TIME NÃO EXISTE!");
		}else if(campeonatos.get(campeonato).verificaTime(times.get(idTime)) == false) {
			throw new IllegalArgumentException("TIME NÃO ESTÁ NO CAMPEONATO!");
		}
		return true;
	}
	
	/**
	 * Exibe os campeonatos que determinado time está participando.
	 * @param idTime Identificador do time;
	 * @return Uma String que representa os campeonatos que o time está participando.
	 */
	public String exibeCampeonatosTime(String idTime) {
		
		String saida = "";
		
		if(!times.containsKey(idTime)) {
			return "O TIME NÃO EXISTE!";
		}else {
			saida = "Campeonatos do " + times.get(idTime).getNome() + ":\n";
			for(Campeonato campeonato: campeonatos.values()) {
				if(campeonato.verificaTime(times.get(idTime)) == true) {
					saida += "* " + campeonato.toString() + "\n";
				}
			}
		}
		return saida.trim();
	}
	
	/**
	 * Adiciona uma aposta ao MrBetSistenma.
	 * @param idTime Identificador do time.
	 * @param nome Nome do campeonato.
	 * @param colocacao Colocação do time que o usuário apostou.
	 * @param valorAposta Valor da aposta do usuário.
	 * @return String com o status da ação.
	 */
	public String adicionaAposta(String idTime, String nome, int colocacao, double valorAposta) {
		
		if (!times.containsKey(idTime)) {
			throw new NullPointerException("TIME NÃO EXISTE!");
		}else if (!campeonatos.containsKey(nome)) {
			throw new NullPointerException("CAMPEONATO NÃO EXISTE!");
		}else if (verificaTimeEmCampeonato(idTime, nome) != true) {
			throw new IllegalArgumentException("TIME NÃO ESTÁ NO CAMPEONATO!");
		}else if (colocacao > campeonatos.get(nome).getMaxParticipantes()) {
			return "APOSTA NÃO REGISTRADA!";
		}else {
			Aposta aposta = new Aposta(times.get(idTime), campeonatos.get(nome), colocacao, valorAposta);
			apostas.add(aposta);
		}
		return "APOSTA REGISTRADA!";
	}
	
	/**
	 * Lista as apostas feitas no sistema.
	 * @return representação textual das apostas.
	 */
	public String listaApostas() {
		String saida = "Apostas:\n\n";
		for (Aposta aposta: apostas) {
			saida += (idAposta + 1) + ". " + aposta.toString() + "\n\n";
			this.idAposta++;
		}
		return saida;
	}
}