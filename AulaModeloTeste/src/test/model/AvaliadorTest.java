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
		// instanciar os objetos
		usuario1 = new Usuario("João");
		usuario2 = new Usuario("Jose");
		usuario3 = new Usuario("Maria");
		leilao = new Leilao("Playstation 3");
		avaliador = new Avaliador();
	}

	@Test
	public void testarMaiorLanceSemOrdem() {
		// cenário
		leilao.propoe(new Lance(usuario1, 300));
		leilao.propoe(new Lance(usuario2, 400));
		leilao.propoe(new Lance(usuario3, 250));
		double resultadoEsperado = 400;
		// execucao
		avaliador.avalia(leilao);
		// comparação
		assertEquals(resultadoEsperado, avaliador.getMaiorLance(), 0.0001);
	}
	
	@Test
	public void testarMaiorLanceOrdemCrescente() {
		// cenário
		leilao.propoe(new Lance(usuario3, 250));
		leilao.propoe(new Lance(usuario1, 300));
		leilao.propoe(new Lance(usuario2, 400));
		double resultadoEsperado = 400;
		// execucao
		avaliador.avalia(leilao);
		// comparação
		assertEquals(resultadoEsperado, avaliador.getMaiorLance(), 0.0001);		
	}

	@Test
	public void testarMaiorLanceOrdemDecrescente() {
		// cenário
		leilao.propoe(new Lance(usuario2, 400));
		leilao.propoe(new Lance(usuario1, 300));
		leilao.propoe(new Lance(usuario3, 250));
		double resultadoEsperado = 400;
		// execucao
		avaliador.avalia(leilao);
		// comparação
		assertEquals(resultadoEsperado, avaliador.getMaiorLance(), 0.0001);		
	}
	
	
	@Test
	public void testarMenorLanceSemOrdem() {
		// cenário
		leilao.propoe(new Lance(usuario1, 300));
		leilao.propoe(new Lance(usuario2, 400));
		leilao.propoe(new Lance(usuario3, 250));
		double resultadoEsperado = 250;
		// execucao
		avaliador.avalia(leilao);
		// comparação
		assertEquals(resultadoEsperado, avaliador.getMenorLance(), 0.0001);
	}
	
	@Test
	public void testarMenorLanceOrdemCrescente() {
		// cenário
		leilao.propoe(new Lance(usuario3, 250));
		leilao.propoe(new Lance(usuario1, 300));
		leilao.propoe(new Lance(usuario2, 400));
		double resultadoEsperado = 250;
		// execucao
		avaliador.avalia(leilao);
		// comparação
		assertEquals(resultadoEsperado, avaliador.getMenorLance(), 0.0001);
	}	

	@Test
	public void testarMenorLanceOrdemDecrescente() {
		// cenário
		leilao.propoe(new Lance(usuario2, 400));
		leilao.propoe(new Lance(usuario1, 300));
		leilao.propoe(new Lance(usuario3, 250));
		double resultadoEsperado = 250;
		// execucao
		avaliador.avalia(leilao);
		// comparação
		assertEquals(resultadoEsperado, avaliador.getMenorLance(), 0.0001);
	}	
	
	@Test
	public void testarUmLance() {
		// cenário
		leilao.propoe(new Lance(usuario3, 250));
		double resultadoEsperado = 250;
		// execucao
		avaliador.avalia(leilao);
		// comparação
		assertEquals(resultadoEsperado, avaliador.getMaiorLance(), 0.0001);		
	}
	
	@Test
	public void testarTresMaioresValoresOrdemCrescente() {
		leilao.propoe(new Lance(usuario3, 250));
		leilao.propoe(new Lance(usuario1, 350));
		leilao.propoe(new Lance(usuario2, 450));
		leilao.propoe(new Lance(usuario3, 550));
		
		avaliador.avalia(leilao);
		
		List<Lance> maiores = avaliador.getTresMaiores();
		
		assertEquals(3, maiores.size());
		
		assertEquals(550, maiores.get(0).getValor());
		assertEquals(450, maiores.get(1).getValor());
		assertEquals(350, maiores.get(2).getValor());
		
		
	}

	@Test
	public void testarSemLance() {
		Assertions.assertThrows(RuntimeException.class, ()->{avaliador.avalia(leilao);});
	}
	
	
}
