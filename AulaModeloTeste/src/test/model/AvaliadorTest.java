package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Avaliador;
import model.Lance;
import model.Leilao;
import model.Usuario;

public class AvaliadorTest {
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Leilao leilao;
	private Avaliador avaliador;
	
	@BeforeEach
	public void instanciarObjetos() {
		usuario1 = new Usuario("João");
		usuario1 = new Usuario("José");
		usuario1 = new Usuario("Maria");
		leilao = new Leilao("Playstation 3 Novo");
		avaliador = new Avaliador();
	}
	
	@Test
	public void testarMaiorLancesSemOrdem() {
		// cenário: 3 lances sem ordem
		leilao.propoe(new Lance(usuario1,300.0));
		leilao.propoe(new Lance(usuario2,400.0));
		leilao.propoe(new Lance(usuario3,250.0));
		double resultadoEsperado = 400;
		// executando a ação
		avaliador.avalia(leilao);
		// comparando a saída com o esperado
		double resultadoObtido = avaliador.getMaiorLance();
		assertEquals(resultadoEsperado, resultadoObtido, 0.0001);
	}
	
	@Test
	public void testarMaiorLancesOrdemCrescente() {
		// cenário: 3 lances sem ordem
		leilao.propoe(new Lance(usuario3,250.0));
		leilao.propoe(new Lance(usuario1,300.0));
		leilao.propoe(new Lance(usuario2,400.0));
		double resultadoEsperado = 400;
		// executando a ação
		avaliador.avalia(leilao);
		// comparando a saída com o esperado
		double resultadoObtido = avaliador.getMaiorLance();
		assertEquals(resultadoEsperado, resultadoObtido, 0.0001);
	}
	
	@Test
	public void testarMenorLancesOrdemCrescente() {
		// cenário: 3 lances sem ordem
		leilao.propoe(new Lance(usuario3,250.0));
		leilao.propoe(new Lance(usuario1,300.0));
		leilao.propoe(new Lance(usuario2,400.0));
		double resultadoEsperado = 250;
		// executando a ação
		avaliador.avalia(leilao);
		// comparando a saída com o esperado
		double resultadoObtido = avaliador.getMenorLance();
		assertEquals(resultadoEsperado, resultadoObtido, 0.0001);
	}
	
	
	
	@Test
	public void testarMenorLancesSemOrdem() {
		// cenário: 3 lances sem ordem
		leilao.propoe(new Lance(usuario1,300.0));
		leilao.propoe(new Lance(usuario2,400.0));
		leilao.propoe(new Lance(usuario3,250.0));
		double resultadoEsperado = 250;
		// executando a ação
		avaliador.avalia(leilao);
		// comparando a saída com o esperado
		double resultadoObtido = avaliador.getMenorLance();
		assertEquals(resultadoEsperado, resultadoObtido, 0.0001);
	}
	
	@Test
	public void testarMenorLancesUmLance() {
		// cenário: 3 lances sem ordem
		leilao.propoe(new Lance(usuario3,250.0));
		double resultadoEsperado = 250;
		// executando a ação
		avaliador.avalia(leilao);
		// comparando a saída com o esperado
		double resultadoObtido = avaliador.getMenorLance();
		assertEquals(resultadoEsperado, resultadoObtido, 0.0001);
	}
	
	@Test
	public void testarTresMaioresLancesOrdemCrescente() {		
		leilao.propoe(new Lance(usuario1, 100.0));
		leilao.propoe(new Lance(usuario2, 200.0));
		leilao.propoe(new Lance(usuario1, 300.0));
		leilao.propoe(new Lance(usuario2, 400.0));
		avaliador.avalia(leilao);
		List<Lance> maiores = avaliador.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
		assertEquals(300, maiores.get(1).getValor(), 0.00001);
		assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void testarTresMaioresLancesListaVazia() {
		Assertions.assertThrows(RuntimeException.class, ()->avaliador.avalia(leilao));
	}
	
	@Test
	public void testarTresMaioresLancesListaComUm() {		
		leilao.propoe(new Lance(usuario1, 100.0));
		avaliador.avalia(leilao);
		List<Lance> maiores = avaliador.getTresMaiores();
		assertEquals(1, maiores.size());
		assertEquals(100, maiores.get(0).getValor(), 0.00001);
	}
	
}
