package br.edu.ufcg.computacao.mrbet;

import java.util.HashSet;
import java.util.Objects;

/**
 * Constrói o timne a partir de seu identificador, nome e mascote.
 * 
 * @author Nicole Brito Maracajá - 123111413.
 */
public class Time {

	/**
	 * Identificador do time.
	 */
	private String id;
	
	/**
	 * Nome do time.
	 */
	private String nome;
	
	/**
	 * Mascote do time.
	 */
	private String mascote;
	
	/**
	 * Um construtor que constrói o time.
	 * @param id Identificador do time.
	 * @param nome Nome do time.
	 * @param mascote Mascote do time.
	 */
	public Time(String id, String nome, String mascote) {
		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
	}
	
	/**
	 * Retorna o identificador do time.
	 * @param id Identificador do time.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Retorna o nome do time.
	 * @return nome Nome do time.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Retorna o mascote do time.
	 * @return mascote Mascote do time.
	 */
	public String getMascote() {
		return this.mascote;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return getId().toUpperCase().equalsIgnoreCase(other.getId());
	}
	
	/**
	 * toString que retorna as informações do time.
	 */
	@Override
	public String toString() {
		return "[" + this.id + "]" + " " + this.nome + " / " + this.mascote;
	}
}