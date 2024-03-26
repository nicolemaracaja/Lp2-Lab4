package br.edu.ufcg.computacao.mrbet;

import java.util.ArrayList;
import java.util.List;

public class MrBetSistema {
	
	private List<Time> times; //uma representacao simploria da lista de times.
	private List<Campeonato> campeonatos; //uma representacao simploria da lista de campeonatos.

	/**
	 * Cria o FilmNow.
	 */
	public MrBetSistema() {
		this.times = new ArrayList<>();
		this.campeonatos = new ArrayList<>();
	}
	
	public List<Time> getTimes() {
		return times;
	}

	public void cadastraTime(String id, String nome, String mascote) {
		Time time = new Time(id, nome, mascote);
		
		if (time.jaExiste(this.getTimes())) {
			throw new IllegalArgumentException("TIME JÁ EXISTE!");
			
		}
	}
	
}
