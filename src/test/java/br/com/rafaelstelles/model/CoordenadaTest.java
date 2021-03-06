package br.com.rafaelstelles.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CoordenadaTest {

	private TamanhoPlano tamanhoPlano;

	@BeforeTest
	public void initService() {
		tamanhoPlano = new TamanhoPlano(5, 5);
	}

	@DataProvider(name = "coordenadasXIncremento", parallel = true)
	Object[][] coordenadasXIncremento() {
		return new Object[][]{
				{0, 0, 1, 0},
				{1, 5, 2, 5},
				{4, 1, 5, 1},
		};
	}

	@Test(dataProvider = "coordenadasXIncremento")
	public void incrementarX(int x, int y, int experadoX, int experadoY) {
		final Coordenada coordenada = new Coordenada(x, y);
		coordenada.incrementaX(tamanhoPlano.getLargura());
		assertEquals(coordenada.getX(), experadoX);
		assertEquals(coordenada.getY(), experadoY);
	}

	@DataProvider(name = "coordenadasXDecremento", parallel = true)
	Object[][] coordenadasXDecremento() {
		return new Object[][]{
				{2, 0, 1, 0},
				{3, 5, 2, 5},
				{4, 1, 3, 1},
		};
	}

	@Test(dataProvider = "coordenadasXDecremento")
	public void decrementarX(int x, int y, int experadoX, int experadoY) {
		final Coordenada coordenada = new Coordenada(x, y);
		coordenada.decrementaX();
		assertEquals(coordenada.getX(), experadoX);
		assertEquals(coordenada.getY(), experadoY);
	}

	@DataProvider(name = "coordenadasYIncremento", parallel = true)
	Object[][] coordenadasYIncremento() {
		return new Object[][]{
				{0, 0, 0, 1},
				{1, 4, 1, 5},
				{5, 1, 5, 2},
		};
	}

	@Test(dataProvider = "coordenadasYIncremento")
	public void incrementarY(int x, int y, int experadoX, int experadoY) {
		final Coordenada coordenada = new Coordenada(x, y);
		coordenada.incrementaY(tamanhoPlano.getAltura());
		assertEquals(coordenada.getX(), experadoX);
		assertEquals(coordenada.getY(), experadoY);
	}

	@DataProvider(name = "coordenadasYDecremento", parallel = true)
	Object[][] coordenadasYDecremento() {
		return new Object[][]{
				{2, 5, 2, 4},
				{3, 4, 3, 3},
				{5, 1, 5, 0},
		};
	}

	@Test(dataProvider = "coordenadasYDecremento")
	public void decrementarY(int x, int y, int experadoX, int experadoY) {
		final Coordenada coordenada = new Coordenada(x, y);
		coordenada.decrementaY();
		assertEquals(coordenada.getX(), experadoX);
		assertEquals(coordenada.getY(), experadoY);
	}

	@DataProvider(name = "coordenadasXIncrementoErro", parallel = true)
	Object[][] coordenadasXIncrementoErro() {
		return new Object[][]{
				{1, 5},
				{5, 5},
		};
	}

	@Test(dataProvider = "coordenadasXIncrementoErro", expectedExceptions = IllegalStateException.class)
	public void incrementarXErro(int x, int y) {
		final Coordenada coordenada = new Coordenada(x, y);
		coordenada.incrementaY(tamanhoPlano.getAltura());
	}

	@DataProvider(name = "coordenadasYIncrementoErro", parallel = true)
	Object[][] coordenadasYIncrementoErro() {
		return new Object[][]{
				{0, 5},
				{5, 5},
		};
	}

	@Test(dataProvider = "coordenadasYIncrementoErro", expectedExceptions = IllegalStateException.class)
	public void incrementarYErro(int x, int y) {
		final Coordenada coordenada = new Coordenada(x, y);
		coordenada.incrementaY(tamanhoPlano.getAltura());
	}

}
