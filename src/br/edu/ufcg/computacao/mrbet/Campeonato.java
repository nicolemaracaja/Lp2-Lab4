package br.edu.ufcg.computacao.mrbet;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {

	String nome;
	List<Time> times;
	
	public Campeonato(String nome) {
		this.nome = nome;
		this.times = new ArrayList<>();
	}
	
	
}
