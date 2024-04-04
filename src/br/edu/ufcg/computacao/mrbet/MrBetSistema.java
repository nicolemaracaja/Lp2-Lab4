package br.edu.ufcg.computacao.mrbet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MrBetSistema, que implementa as funções incorporadas no Main.
 * 
 * @author Nicole Brito Maracajá.
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
	private int idAposta = 0;
	
	/**
	 * Cria o MrBetSistema.
	 */
	public MrBetSistema() {
		this.times = new HashMap<>();
		this.campeonatos = new HashMap<>();
		this.apostas = new ArrayList<>();
	}
	
	/**
	 * Cadastra um time no sistema.
	 * @param id Identificador do time.
	 * @param nome Nome do time.
	 * @param mascote Mascote do time.
	 */
	public String cadastraTime(String id, String nome, String mascote) {
		Time time = new Time(id, nome, mascote);
		
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
		
		if(campeonatos.containsKey(nome.toUpperCase())) {
			throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!");
		}else {
			campeonatos.put(nome.toUpperCase(), campeonato);
			return "CAMPEONATO ADICIONADO!";
		}
	}
	
	/**
	 * Recupera o campeonato pelo nome.
	 * @param nome Nome do campeonato.
	 * @return ToString com a representação textual do campeonato.
	 */
	public String recuperaCampeonato(String nome) {
		
		if(this.campeonatos.get(nome.toUpperCase()) != null) {
			return this.campeonatos.get(nome.toUpperCase()).toString();
		}
		return "CAMPEONATO NÃO EXISTE!";
	}
	
	/**
	 * Adiciona times a um campeonato.
	 * @param idTime Código do time.
	 * @param campeonato Nome do campeonato.
	 * @return Uma string com o status da ação.
	 */
	public String adicionaTimeEmCampeonato(String idTime, String campeonato) {
		
		if(!campeonatos.containsKey(campeonato.toUpperCase())) {
			return "CAMPEONATO NÃO EXISTE!";
		}else if(!times.containsKey(idTime.toUpperCase())) {
			return "TIME NÃO EXISTE!";
		}else if(campeonatos.get(campeonato.toUpperCase()).getParticipantes() >= campeonatos.get(campeonato.toUpperCase()).getMaxParticipantes()) {
			return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
		}else {
			campeonatos.get(campeonato.toUpperCase()).adicionaTime(idTime.toUpperCase(), times.get(idTime));
			return "TIME INCLUÍDO NO CAMPEONATO!";
		}		
	}
	
	/**
	 * Verifica se o time está participando do campeonato.
	 * @param idTime Identificador do time.
	 * @param campeonato Campeonato que será verificado.
	 * @return Uma string com o status da ação.
	 */
	public String verificaTimeEmCampeonato(String idTime, String campeonato) {
		
		if(!campeonatos.containsKey(campeonato.toUpperCase())) {
			return "O CAMPEONATO NÃO EXISTE!";
		}else if(!times.containsKey(idTime.toUpperCase())) {
			return "O TIME NÃO EXISTE!";
		}else if(campeonatos.get(campeonato.toUpperCase()).verificaTime(idTime.toUpperCase()) == false) {
			return "TIME NÃO ESTÁ NO CAMPEONATO!";	
		}
		return "O TIME ESTÁ NO CAMPEONATO!";
	}
	
	/**
	 * Exibe os campeonatos que determinado time está participando.
	 * @param idTime Identificador do time;
	 * @return Uma String que representa os campeonatos que o time está participando.
	 */
	public String exibeCampeonatosTime(String idTime) {
		
		String saida = "";
		
		if(!times.containsKey(idTime.toUpperCase())) {
			return "O TIME NÃO EXISTE!";
		}else {
			saida = "Campeonatos do " + times.get(idTime.toUpperCase()).getNome() + ": \n";
			for(Campeonato campeonato: campeonatos.values()) {
				if(campeonato.verificaTime(idTime.toUpperCase()) == true) {
					saida += "* " + campeonato.toString() + "\n";
				}
			}
		}
		return saida;
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
			return "TIME NÃO EXISTE!";
		}else if (!campeonatos.containsKey(nome)) {
			return "CAMPEONATO NÃO EXISTE!";
		}else if (colocacao > campeonatos.get(nome).getParticipantes()) {
			return "APOSTA NÃO REGISTRADA!";
		}else {
			Aposta aposta = new Aposta(times.get(idTime), campeonatos.get(nome), colocacao, valorAposta);
			apostas.add(aposta);
			return "APOSTA REGISTRADA!";
		}
	}
	
	/**
	 * Lista as apostas feitas no sistema.
	 * @return representação textual das apostas.
	 */
	public String listaApostas() {
		idAposta++;
		String saida = "Apostas: \n\n";
		for (Aposta aposta: apostas) {
			saida += idAposta + ". " + aposta.toString();
		}
		return saida;
	}
	
}