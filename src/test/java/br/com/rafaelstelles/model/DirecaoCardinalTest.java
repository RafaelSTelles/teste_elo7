package br.com.rafaelstelles.model;

import static br.com.rafaelstelles.model.DirecaoCardinal.E;
import static br.com.rafaelstelles.model.DirecaoCardinal.N;
import static br.com.rafaelstelles.model.DirecaoCardinal.S;
import static br.com.rafaelstelles.model.DirecaoCardinal.W;
import static br.com.rafaelstelles.model.DirecaoCardinal.rodarNoventaGrausParaDireita;
import static br.com.rafaelstelles.model.DirecaoCardinal.rodarNoventaGrausParaEsquerda;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DirecaoCardinalTest {

	@DataProvider(name = "direcaoCardinalTestNoventaGrausDireita", parallel = true)
	Object[][] direcaoCardinalTestNoventaGrausDireita() {
		return new Object[][]{
				{E, S},
				{S, W},
				{W, N},
				{N, E},
		};
	}

	@Test(dataProvider = "direcaoCardinalTestNoventaGrausDireita")
	public void noventaGrausParaDireita(DirecaoCardinal original, DirecaoCardinal experado) {
		final DirecaoCardinal direcaoMovida = rodarNoventaGrausParaDireita(original);
		assertEquals(direcaoMovida, experado);
	}

	@DataProvider(name = "direcaoCardinalTestNoventaGrausEsquerda", parallel = true)
	Object[][] direcaoCardinalTestNoventaGrausEsquerda() {
		return new Object[][]{
				{E, N},
				{N, W},
				{W, S},
				{S, E},
		};
	}

	@Test(dataProvider = "direcaoCardinalTestNoventaGrausEsquerda")
	public void noventaGrausParaEsquerda(DirecaoCardinal original, DirecaoCardinal experado) {
		final DirecaoCardinal direcaoMovida = rodarNoventaGrausParaEsquerda(original);
		assertEquals(direcaoMovida, experado);
	}

}
