package br.edu.ufcg.computacao.mrbet;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MrBetSistemaTest {

	private MrBetSistema mb;
	private Time time1;
	private Time time2;
	private Time time3;
	private Time time4;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mb = new MrBetSistema();
		this.time1 = new Time("250_PB", "Nacional de Patos", "Canário");
		this.time2 = new Time("252_PB", "Sport Lagoa Seca", "Carneiro");
		this.time3 = new Time("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
		this.time4 = new Time("105_PB", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");
	}

	@Test
	void testCadastraTime() {
	}

	@Test
	void testRecuperaTime() {
	}

	@Test
	void testAdicionaCampeonato() { //caso 1
		assertEquals("CAMPEONATO ADICIONADO!", mb.adicionaCampeonato("Brasileirão Série A 2023", 30));
	}
	
	@Test
	void testAdicionaCampeonatoComNomeJaExistente() { //caso 2
		try {
			mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
			mb.adicionaCampeonato("Brasileirão Série A 2023", 10);
			fail();
		}catch(IllegalArgumentException iae) {
    		assertEquals("CAMPEONATO JÁ EXISTE!", iae.getMessage());
		}
	}

	@Test
	void testRecuperaCampeonato() {
	}

	@Test
	void testPodeAdicionarCampeonato() {
	}

	@Test
	void testAdicionaTimeEmCampeonato() { //caso 3
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.adicionaTimeEmCampeonato(time1.getId(), "Brasileirão Série A 2023"));
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.adicionaTimeEmCampeonato(time2.getId(), "Brasileirão Série A 2023"));
	}

	@Test
	void testAdicionaTimeRepetidoEmCampeonato() { //caso 4
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.adicionaTimeEmCampeonato(time1.getId(), "Brasileirão Série A 2023"));
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.adicionaTimeEmCampeonato(time2.getId(), "Brasileirão Série A 2023"));
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.adicionaTimeEmCampeonato(time2.getId(), "Brasileirão Série A 2023"));
	}
	
	@Test
	void testAdicionaTimeQueNaoExisteEmCampeonato() { //caso 5
		mb.adicionaCampeonato("Brasileirão Série A 2023", 10);
		try {
			mb.adicionaTimeEmCampeonato("005_PB", "Brasileirão Série A 2023");
			fail();
		}catch(IllegalArgumentException iae) {
			assertEquals("TIME NÃO EXISTE!", iae.getMessage());
		}
	}
	
	@Test
	void testAdicionaTimeemCampeonatoQueNaoExiste() { //caso 6
		try {
			mb.adicionaTimeEmCampeonato("252_PB", "Brasileirão Série D 2023");
			fail();
		}catch(IllegalArgumentException iae) {
			assertEquals("CAMPEONATO NÃO EXISTE!", iae.getMessage());
		}
	}
	
	@Test
	void testAdicionaTimeEmCampeonatoLotado() { //caso 7
		mb.adicionaCampeonato("Brasileirão Série A 2023", 1);
		try {
			mb.adicionaTimeEmCampeonato(time2.getId(), "Brasileirão Série A 2023");
			mb.adicionaTimeEmCampeonato(time1.getId(), "Brasileirão Série A 2023");
		}catch(IllegalArgumentException iae) {
			assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", iae.getMessage());
		}
	}

	@Test
	void testVerificaTimeEmCampeonato() { //caso 8
		mb.adicionaCampeonato("Copa do Nordeste 2023", 10);
		mb.adicionaTimeEmCampeonato(time1.getId(), "Copa do Nordeste 2023");
		assertEquals(mb.verificaTimeEmCampeonato(time1.getId(), "Copa do Nordeste 2023"), true);
		
		try {
			mb.verificaTimeEmCampeonato(time2.getId(), "Copa do Nordeste 2023");
			fail();
		}catch(IllegalArgumentException iae) {
			assertEquals("TIME NÃO ESTÁ NO CAMPEONATO!", iae.getMessage());
		}
	}
	
	@Test
	void testVerificaTimeEmCampeonatoInexistente() { //caso 9
		try {
			mb.verificaTimeEmCampeonato(time2.getId(), "Brasileirão Série D 2023");
		}catch(IllegalArgumentException iae) {
			assertEquals("CAMPEONATO NÃO EXISTE!", iae.getMessage());
		}
	}
	
	@Test
	void testVerificaTimeInexistenteEmCampeonato() { //caso 10
		mb.adicionaCampeonato("Copa do Nordeste 2023", 10);
		try {
			mb.verificaTimeEmCampeonato("005_PB", "Copa do Nordeste 2023");
		}catch(IllegalArgumentException iae) {
			assertEquals("TIME NÃO EXISTE!", iae.getMessage());
		}
	}
	@Test
	void testExibeCampeonatosTime() {
	}

	@Test
	void testAdicionaAposta() {
	}

	@Test
	void testListaApostas() {
	}

}
