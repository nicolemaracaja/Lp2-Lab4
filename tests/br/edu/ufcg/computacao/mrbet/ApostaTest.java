package br.edu.ufcg.computacao.mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApostaTest {

	private Time timeBase;
	private Campeonato campeonatoBase;
	private Aposta apostaBase;
	
	@BeforeEach
	void setUp() throws Exception {
		timeBase = new Time("250_PB", "Nacional de Patos", "Canário");
		campeonatoBase = new Campeonato("Brasileirão Série A 2023", 30);
		apostaBase = new Aposta(timeBase, campeonatoBase, 2, 30.00);
	}
	
	@Test
	void testGetColocacao() {
		assertEquals(apostaBase.getColocacao(), 2);
	}

	@Test
	void testGetValorAposta() {
		assertEquals(apostaBase.getValorAposta(), 30.00);
	}

	@Test
	void testToString() {
		assertEquals("[250_PB] Nacional de Patos / Canário" + 
	"\n" + "Brasileirão Série A 2023" + "\n" + "2/30" + "\n" + "R$ 30,00", apostaBase.toString());
	}

}
