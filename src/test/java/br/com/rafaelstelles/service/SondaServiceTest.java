package br.com.rafaelstelles.service;

import static br.com.rafaelstelles.model.DirecaoCardinal.E;
import static br.com.rafaelstelles.model.DirecaoCardinal.N;
import static br.com.rafaelstelles.model.DirecaoCardinal.S;
import static br.com.rafaelstelles.model.DirecaoCardinal.W;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.com.rafaelstelles.model.Coordenada;
import br.com.rafaelstelles.model.DadosSonda;
import br.com.rafaelstelles.model.Sonda;
import br.com.rafaelstelles.model.TamanhoPlano;

public class SondaServiceTest {

	private SondaService sondaService;
	private TamanhoPlano tamanhoPlano;

	@BeforeTest
	public void initService() {
		sondaService = new SondaService();
		tamanhoPlano = new TamanhoPlano(5, 5);
	}

	@DataProvider(name = "comandosIndividuais", parallel = true)
	Object[][] comandosIndividuais() {
		return new Object[][]{
				{"1 2 N" + System.lineSeparator() +
						"LMLMLMLMM", new DadosSonda(new Coordenada(1, 3), N)},
				{"3 3 E" + System.lineSeparator() +
						"MMRMMRMRRM", new DadosSonda(new Coordenada(5, 1), E)},
		};
	}

	@Test(dataProvider = "comandosIndividuais")
	public void comandar(String comandos, DadosSonda experado) {
		final List<String> linhas = sondaService.quebrarStringEmLinhas(comandos);
		final Sonda sonda = sondaService.processarSonda(linhas.get(0), linhas.get(1), tamanhoPlano);
		assertEquals(sonda.getPosicaoFinal(), experado);
	}

	@DataProvider(name = "comandosConjunto", parallel = true)
	Object[][] comandosConjunto() {
		return new Object[][]{
				{"5 5" + System.lineSeparator() +
						"1 2 N" + System.lineSeparator() +
						"LMLMLMLMM" + System.lineSeparator() +
						"3 3 E" + System.lineSeparator() +
						"MMRMMRMRRM" + System.lineSeparator() +
						"1 1 E" + System.lineSeparator() +
						"MMLMM" + System.lineSeparator() +
						"2 4 E" + System.lineSeparator() +
						"LRLRLR" + System.lineSeparator() +
						"5 5 N " + System.lineSeparator() +
						" " + System.lineSeparator() +
						"0 0 N" + System.lineSeparator() +
						"MLLMR" + System.lineSeparator() +
						"4 1 N" + System.lineSeparator() +
						"MMLMRMRMRM" + System.lineSeparator(),
						Arrays.asList(new DadosSonda(new Coordenada(1, 3), N),
								new DadosSonda(new Coordenada(5, 1), E),
								new DadosSonda(new Coordenada(3, 3), N),
								new DadosSonda(new Coordenada(2, 4), E),
								new DadosSonda(new Coordenada(5, 5), N),
								new DadosSonda(new Coordenada(0, 0), W),
								new DadosSonda(new Coordenada(4, 3), S)
						)
				},
		};
	}

	@Test(dataProvider = "comandosConjunto")
	public void comandarConjunto(String comandos, List<DadosSonda> experados) {
		final List<Sonda> sondas = sondaService.processarComando(comandos);
		final List<DadosSonda> posicoesFinais = sondas.stream().map(Sonda::getPosicaoFinal).collect(Collectors.toList());
		assertEquals(posicoesFinais, experados);
	}

	@DataProvider(name = "comandosErrados", parallel = true)
	Object[][] comandosErrados() {
		return new Object[][]{
				{"5 5" + System.lineSeparator() +
						"1 2 N" + System.lineSeparator()},
				{"5 5" + System.lineSeparator() +
						"1 2 N" + System.lineSeparator() +
						"5 5" + System.lineSeparator() +
						"1 2 N"},
				{"5 5" + System.lineSeparator()},
		};
	}

	@Test(dataProvider = "comandosErrados", expectedExceptions = IllegalArgumentException.class)
	public void comandosErrados(String comandos) {
		sondaService.processarComando(comandos);
	}

	@DataProvider(name = "comandosMaiorQueOPlano", parallel = true)
	Object[][] comandosMaiorQueOPlano() {
		return new Object[][]{
				{"2 2" + System.lineSeparator() +
						"1 2 N" + System.lineSeparator() +
						"LMLMLMLMM" + System.lineSeparator() +
						"3 3 E" + System.lineSeparator() +
						"MMRMMRMRRM"
				},
		};
	}

	@Test(dataProvider = "comandosMaiorQueOPlano", expectedExceptions = IllegalStateException.class)
	public void comandosMaiorQueOPlano(String comandos) {
		sondaService.processarComando(comandos);
	}

}
