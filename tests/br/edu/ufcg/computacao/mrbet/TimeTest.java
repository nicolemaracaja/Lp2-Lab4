package br.edu.ufcg.computacao.mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
* Classe que testa as funcionalidades da classe Time.
* @author Nicole Brito Maracajá - 123111413.
*/
class TimeTest {
	
	/**
	 * Cria um time base para testar as funcionalidades.
	 */
	private Time timeBase;

	@BeforeEach
	void setUp() throws Exception {
		timeBase = new Time("250_PB", "Nacional de Patos", "Canário");
	}

	@Test
	void testGetId() {
		assertEquals(timeBase.getId(), "250_PB");
	}

	@Test
	void testGetNome() {
		assertEquals(timeBase.getNome(), "Nacional de Patos");
	}

	@Test
	void testToString() {
		assertEquals(timeBase.toString(), "[250_PB] Nacional de Patos / Canário");
	}
}
