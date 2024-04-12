package br.edu.ufcg.computacao.mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe que testa as funcionalidades da classe Campeonato.
 * @author Nicole Brito Maracajá - 123111413.
 */
class CampeonatoTest {

	/**
	 * Cria um campeonato base para testar as funcionalidades.
	 */
	private Campeonato campeonatoBase;
	
	/**
	 * Cria um time base para testar as funcionalidades.
	 */
	private Time timeBase;
	
	@BeforeEach
	void setUp() throws Exception {
		campeonatoBase = new Campeonato("Brasileirão Série A 2023", 30);
		timeBase = new Time("250_PB", "Nacional de Patos", "Canário");
	}

	@Test
	void testGetNome() {
		assertEquals(campeonatoBase.getNome(), "Brasileirão Série A 2023");
	}

	@Test
	void testGetMaxParticipantes() {
		assertEquals(campeonatoBase.getMaxParticipantes(), 30);
	}

	@Test
	void testGetParticipantes() {
		campeonatoBase.adicionaTime(timeBase);
		campeonatoBase.incrementaParticipantes(timeBase);
		assertEquals(campeonatoBase.getParticipantes(), 1);
	}

	@Test
	void testAdicionaTime() {
		campeonatoBase.adicionaTime(timeBase);
		assertEquals(campeonatoBase.verificaTime(timeBase), true);
	}

	@Test
	void testVerificaTime() {
		Time outroTime = new Time("152_SC", "Santa Catarina", "Ovelha");
		campeonatoBase.adicionaTime(timeBase);
		assertEquals(campeonatoBase.verificaTime(timeBase), true);
		assertEquals(campeonatoBase.verificaTime(outroTime), false);
	}

	@Test
	void testToString() {
		assertEquals(campeonatoBase.toString(), "Brasileirão Série A 2023 - 0/30");
	}

}
