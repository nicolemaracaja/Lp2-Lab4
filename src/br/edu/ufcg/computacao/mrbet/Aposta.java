package br.edu.ufcg.computacao.mrbet;

/**
 * Classe que cria uma aposta.
 * 
 * @author Nicole Brito Maracajá.
 */
public class Aposta {

	/**
	 * Time que será apostado.
	 */
	private Time time;
	
	/**
	 * Campeonato que o time apostado participa.
	 */
	private Campeonato campeonato;
	
	/*
	 * Colocação do time apostada. 
	 */
	private int colocacao;
	
	/*
	 * Valor da aposta.
	 */
	private double valorAposta;
	
	/**
	 * Cria a aposta.
	 * @param time O Time.
	 * @param campeonato O Campeonato.
	 * @param colocacao A colocação apostada do time em um determinado campeonato.
	 * @param valorAposta O valor da aposta.
	 */
	public Aposta(Time time, Campeonato campeonato, int colocacao, double valorAposta) {
		this.time = time;
		this.campeonato = campeonato;
		this.colocacao = colocacao;
		this.valorAposta = valorAposta;
	}
	
	/**
	 * Retorna a colocação que o usuário apostou.
	 * @return colocação apostada.
	 */
	public int getColocacao() {
		return this.colocacao;
	}
	
	/**
	 * Retorna o valor da aposta.
	 * @return valor da aposta.
	 */
	public double getValorAposta() {
		return this.valorAposta;
	}
	
	@Override
	public String toString() {
		return this.time + "\n" + this.campeonato.getNome() + "\n" 
		+ this.colocacao + "/" + this.campeonato.getParticipantes() + "\n"
		+ "R$ " + String.format("%.2f", this.valorAposta);				
	}
}