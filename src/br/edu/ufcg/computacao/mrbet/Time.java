package br.edu.ufcg.computacao.mrbet;

import java.util.List;
import java.util.Objects;

public class Time {

	private String id;
	private String nome;
	private String mascote;
	
	public Time(String id, String nome, String mascote) {
		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
	}
	
	public String getId() {
		return this.id;
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
		return Objects.equals(id, other.id);
	}
	
	public boolean jaExiste(List<Time> times) {
		Time t = this;
		return times.contains(t);
	}
	
	@Override
	public String toString() {
		return "[" + this.id + "]" + " " + this.nome + " / " + this.mascote;
	}
}
