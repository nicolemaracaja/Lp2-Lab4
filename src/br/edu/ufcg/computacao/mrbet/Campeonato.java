package br.edu.ufcg.computacao.mrbet;

import java.util.HashSet;

/**
 * Constrói o campeonato a partir de seu nome e quantidade de participantes.
 * 
 * @author Nicole Brito Maracajá - 123111413.
 */
public class Campeonato {

	/**
	 * Nome do campeonato.
	 */
	private String nome;
	
	/**
	 * Quantidade de participantes do campeonato.
	 */
	private int qtdParticipantes;	
	
	/**
	 * Quantidade máxima de times que podem participar.
	 */
	private final int qtdMaxParticipantes;
	
	/**
	 * Set que associa os times ao campeonato.
	 */
	private HashSet<Time> timesNoCampeonato; 
	
	/**
	 * Um construtor que registra um novo campeonato.
	 * @param nome Nome do Campeonato.
	 * @param qtdMaxParticipantes Quantidade máxima de participantes do campeonato.
	 */
	public Campeonato(String nome, int qtdMaxParticipantes) {
		this.nome = nome;
		this.qtdMaxParticipantes = qtdMaxParticipantes;
		this.qtdParticipantes = 0;
		this.timesNoCampeonato = new HashSet<>();		
	}
	
	/**
	 * Retorna o nome do campeonato.
	 * @return Nome do campeonato.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Retorna a quantidade máxima de participantes que podem se inscrever no campeonato.
	 * @return A quantidade máxima de participantes.
	 */
	public int getMaxParticipantes() {
		return this.qtdMaxParticipantes;
	}
	/**
	 * Retorna a quantidade de participantes do campeonato.
	 * @return A quantidade de participantes.
	 */
	public int getParticipantes() {
		return this.qtdParticipantes;
	}
	
	/**
	 * Adiciona times no campeonato.
	 * @param time Time.
	 * @return true se adicionou, se não, false.
	 */
	public boolean adicionaTime(Time time) {

		if (this.qtdParticipantes < this.qtdMaxParticipantes) {
			this.timesNoCampeonato.add(time);
			return true;
		}
		return false;
	}
	
	/**
	 * Incrementa a quantidade de participantes no campeonato.
	 * @param nome Nome do campeonato.
	 * @param time Time.
	 */
	public void incrementaParticipantes(Time time) {
		if (adicionaTime(time)) {
			this.qtdParticipantes++;
		}
	}
	
	/**
	 * Verifica se um time está contido no campeonato.
	 * @param nome Nome do time.
	 * @return um valor booleano, true se o time estiver no campeonato, false caso contrário.
	 */
	public boolean verificaTime(Time time) {
		if(this.timesNoCampeonato.contains(time)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna uma String com as informaçoes sobre campeonato formatadas.
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.timesNoCampeonato.size() + "/" + this.qtdMaxParticipantes;
	}
}