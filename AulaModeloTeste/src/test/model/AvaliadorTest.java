package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
