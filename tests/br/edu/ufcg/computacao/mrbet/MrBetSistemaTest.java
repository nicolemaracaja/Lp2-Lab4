package br.edu.ufcg.computacao.mrbet;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe que testa as funcionalidades do controller do sistema MrBet.
 * @author Nicole Brito Maracajá - 123111413.
 */
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
		assertEquals(mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote()), "INCLUSÃO REALIZADA!");
	}
	
	@Test
	void testCadastraTimeQueJaExiste() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		try {
			mb.cadastraTime("002_RJ", "Rio", "Mico-leão");
		}catch(IllegalArgumentException iae) {
			assertEquals("TIME JÁ EXISTE!", iae.getMessage());
		}
	}

	@Test
	void testRecuperaTime() {
		mb.cadastraTime(time4.getId(), time4.getNome(), time4.getMascote());
		assertEquals(mb.recuperaTime(time4.getId()), "[105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião");
	}
	
	@Test
	void testRecuperaTimeQueNaoExiste() {
		assertEquals(mb.recuperaTime("100_AM"), "TIME NÃO EXISTE!");
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
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		assertEquals("Brasileirão Série A 2023 - 0/30", mb.recuperaCampeonato("Brasileirão Série A 2023"));
	}
	
	@Test
	void testRecuperaCampeonatoQueNaoExiste() {
		assertEquals("CAMPEONATO NÃO EXISTE!", mb.recuperaCampeonato("Brasileirão Série D 2023"));
	}

	@Test
	void testPodeAdicionarCampeonato() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		assertEquals(mb.podeAdicionarCampeonato(time3.getId(), "Brasileirão Série A 2023"), true);
	}
	
	@Test
	void testPodeAdicionarCampeonatoTimeInexistente() {
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		try {
			mb.podeAdicionarCampeonato("100_AM", "Brasileirão Série A 2023");
		}catch(NullPointerException iae) {
			assertEquals("TIME NÃO EXISTE!", iae.getMessage());
		}
	}
	
	@Test
	void testPodeAdicionarCampeonatoCampeonatoInexistente() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		try {
			mb.podeAdicionarCampeonato(time3.getId(), "Brasileirão Série D 2023");
		}catch(NullPointerException iae) {
			assertEquals("CAMPEONATO NÃO EXISTE!", iae.getMessage());
		}
	}
	
	@Test
	void testPodeAdicionarCampeonatoLimiteExcedido() {
		mb.cadastraTime(time1.getId(), time1.getNome(), time1.getMascote());
		mb.cadastraTime(time2.getId(), time2.getNome(), time2.getMascote());
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 2);
		
		mb.adicionaTimeEmCampeonato(time1.getId(), "Brasileirão Série A 2023");
		mb.adicionaTimeEmCampeonato(time2.getId(), "Brasileirão Série A 2023");
		
		try {
			mb.podeAdicionarCampeonato(time3.getId(), "Brasileirão Série A 2023");
			mb.adicionaTimeEmCampeonato(time3.getId(), "Brasileirão Série A 2023");
			fail();
		}catch(IndexOutOfBoundsException iae) {
			assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", iae.getMessage());
		}
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
			mb.podeAdicionarCampeonato("005_PB", "Brasileirão Série A 2023");
			mb.adicionaTimeEmCampeonato("005_PB", "Brasileirão Série A 2023");
			fail();
		}catch(NullPointerException iae) {
			assertEquals("TIME NÃO EXISTE!", iae.getMessage());
		}
	}
	
	@Test
	void testAdicionaTimeemCampeonatoQueNaoExiste() { //caso 6
		try {
			mb.cadastraTime(time2.getId(), time2.getNome(), time2.getMascote());
			mb.podeAdicionarCampeonato(time2.getId(), "Brasileirão Série D 2023");
			mb.adicionaTimeEmCampeonato(time2.getId(), "Brasileirão Série D 2023");
			fail();
		}catch(NullPointerException iae) {
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
		mb.cadastraTime(time1.getId(), time1.getNome(), time1.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 10);
		mb.adicionaTimeEmCampeonato(time1.getId(), "Brasileirão Série A 2023");
		
		assertEquals(mb.verificaTimeEmCampeonato(time1.getId(), "Brasileirão Série A 2023"), true);
	}
	
	@Test
	void testVerificaTimeQueNaoEstaNoCampeonato() {
		mb.cadastraTime(time2.getId(), time2.getNome(), time2.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 10);
		
		try {
			mb.verificaTimeEmCampeonato(time2.getId(), "Brasileirão Série A 2023");
		}catch(IllegalArgumentException iae) {
			assertEquals("TIME NÃO ESTÁ NO CAMPEONATO!", iae.getMessage());
		}
	}
	
	@Test
	void testVerificaTimeEmCampeonatoInexistente() { //caso 9
		try {
			mb.verificaTimeEmCampeonato(time2.getId(), "Brasileirão Série D 2023");
		}catch(NullPointerException iae) {
			assertEquals("CAMPEONATO NÃO EXISTE!", iae.getMessage());
		}
	}
	
	@Test
	void testVerificaTimeInexistenteEmCampeonato() { //caso 10
		mb.adicionaCampeonato("Copa do Nordeste 2023", 10);
		try {
			mb.verificaTimeEmCampeonato("005_PB", "Copa do Nordeste 2023");
		}catch(NullPointerException iae) {
			assertEquals("TIME NÃO EXISTE!", iae.getMessage());
		}
	}
	@Test
	void testExibeCampeonatosTime() {
		mb.cadastraTime(time1.getId(), time1.getNome(), time1.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		mb.adicionaCampeonato("Paraibano 2024", 10);
		
		mb.adicionaTimeEmCampeonato(time1.getId(), "Brasileirão Série A 2023");
		mb.adicionaTimeEmCampeonato(time1.getId(), "Paraibano 2024");
		
		assertEquals("Campeonatos do Nacional de Patos:" + "\n" 
		+ "* Brasileirão Série A 2023 - 1/30" + "\n" + "* Paraibano 2024 - 1/10", mb.exibeCampeonatosTime(time1.getId()).toString());
	}

	@Test
	void testAdicionaAposta() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		mb.adicionaTimeEmCampeonato(time3.getId(), "Brasileirão Série A 2023");
		
		assertEquals(mb.adicionaAposta(time3.getId(), "Brasileirão Série A 2023", 2, 30.00), "APOSTA REGISTRADA!");
	}
	
	@Test
	void testAdicionaApostaTimeInexistente() {
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
	
		try {
			mb.adicionaAposta("100_AM", "Brasileirão Série A 2023", 2, 30.00);
			fail();
		}catch (NullPointerException iae) {
			assertEquals("TIME NÃO EXISTE!", iae.getMessage());
		}	
	}
	
	@Test
	void testAdicionaApostaCampeonatoInexistente() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		
		try {
			mb.adicionaAposta(time3.getId(), "Brasileirão Série D 2023", 10, 30.00);
			fail();
		}catch (NullPointerException iae) {
			assertEquals("CAMPEONATO NÃO EXISTE!", iae.getMessage());
		}	
	}
	
	@Test
	void testAdicionaApostaTimeNaoEstaNoCampeonato() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);

		try {
			mb.adicionaAposta(time3.getId(), "Brasileirão Série A 2023", 10, 30.00);
			fail();
		}catch (IllegalArgumentException iae) {
			assertEquals("TIME NÃO ESTÁ NO CAMPEONATO!", iae.getMessage());
		}	
	}
	
	@Test
	void testAdicionaApostaColocacaoUltrapassaQtdParticipantes() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 10);
		mb.adicionaTimeEmCampeonato(time3.getId(), "Brasileirão Série A 2023");
		
		assertEquals(mb.adicionaAposta(time3.getId(), "Brasileirão Série A 2023", 15, 30.00), "APOSTA NÃO REGISTRADA!");
	}

	@Test
	void testListaApostas() {
		mb.cadastraTime(time3.getId(), time3.getNome(), time3.getMascote());
		mb.cadastraTime(time4.getId(), time4.getNome(), time4.getMascote());
		mb.adicionaCampeonato("Brasileirão Série A 2023", 30);
		mb.adicionaCampeonato("Paraibano 2024", 10);

		mb.adicionaTimeEmCampeonato(time3.getId(), "Brasileirão Série A 2023");
		mb.adicionaTimeEmCampeonato(time4.getId(), "Paraibano 2024");
		
		mb.adicionaAposta(time3.getId(), "Brasileirão Série A 2023", 3, 100.00);
		mb.adicionaAposta(time4.getId(), "Paraibano 2024", 1, 300.00);
		
		assertEquals("Apostas:\n"
				+ "\n"
				+ "1. [002_RJ] Clube de Regatas do Flamengo / Urubu\n"
				+ "Brasileirão Série A 2023\n"
				+ "3/30\n"
				+ "R$ 100,00\n"
				+ "\n"
				+ "2. [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião\n"
				+ "Paraibano 2024\n"
				+ "1/10\n"
				+ "R$ 300,00\n"
				+ "\n", mb.listaApostas());
	}
}
