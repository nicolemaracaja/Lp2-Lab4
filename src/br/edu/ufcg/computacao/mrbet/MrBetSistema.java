package br.edu.ufcg.computacao.mrbet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MrBetSistema, que implementa as funções incorporadas no Main.
 * @author Nicole Brito Maracajá.
 */
public class MrBetSistema {
	
	private HashMap<String, Time> times; //uma representacao simploria da lista de times.
	private HashMap<String, Campeonato> campeonatos; //uma representacao simploria da lista de campeonatos.

	/**
	 * Cria o MrBetSistema.
	 */
	public MrBetSistema() {
		this.times = new HashMap<>();
		this.campeonatos = new HashMap<>();
	}
	
	/**
	 * Cadastra um time no sistema.
	 * @param id Identificador do time.
	 * @param nome Nome do time.
	 * @param mascote Mascote do time.
	 */
	public String cadastraTime(String id, String nome, String mascote) {
		Time time = new Time(id, nome, mascote);
		
		if (times.containsKey(id)) {
			throw new IllegalArgumentException("TIME JÁ EXISTE!");
		}else {
			times.put(id, time);
			return "INCLUSÃO REALIZADA!";
		}
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
		}else {
			campeonatos.put(nome, campeonato);
			return "CAMPEONATO ADICIONADO!";
		}
	}
	
	/**
	 * Adiciona times a um campeonato.
	 * @param idTime Código do time.
	 * @param campeonato Nome do campeonato.
	 * @return Uma string com o status da ação.
	 */
	public String adicionaTimeEmCampeonato(String idTime, String campeonato) {
		
		if(!campeonatos.containsKey(campeonato)) {
			throw new NullPointerException("CAMPEONATO NÃO EXISTE!");
		}else if(!times.containsKey(idTime)) {
			throw new NullPointerException("TIME NÃO EXISTE!");
		}else if(campeonatos.get(campeonato).getParticipantes() >= campeonatos.get(campeonato).getMaxParticipantes()) {
			throw new IndexOutOfBoundsException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
		}else {
			campeonatos.get(campeonato).adicionaTime(idTime, times.get(idTime));
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
		
		if(!campeonatos.containsKey(campeonato)) {
			throw new NullPointerException("O CAMPEONATO NÃO EXISTE!");
		}else if(!times.containsKey(idTime)) {
			throw new NullPointerException("O TIME NÃO EXISTE!");
		}else if(campeonatos.get(campeonato).verificaTime(idTime) == false) {
			throw new IllegalArgumentException("TIME NÃO ESTÁ NO CAMPEONATO!");	
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
		
		if(!times.containsKey(idTime)) {
			throw new NullPointerException("O TIME NÃO EXISTE!");
		}else {
			saida = "Campeonatos do " + times.get(idTime).getNome() + ": \n";
			for(Campeonato campeonato: campeonatos.values()) {
				if(campeonato.verificaTime(idTime) == true) {
					saida += "* " + campeonato.toString();
				}
			}
		}
		return saida;
	}
	
}
