package br.com.rafaelstelles.rest;

import static org.testng.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.com.rafaelstelles.util.Constantes;

public class SondaRestServiceTest {

	private static UndertowJaxrsServer server;

	@BeforeTest
	public void init() {
		server = new UndertowJaxrsServer().start();
	}

	@AfterTest
	public void end(){
		server.stop();
	}

	@DataProvider(name = "comandosConjunto", parallel = true)
	Object[][] comandosConjunto() {
		return new Object[][]{
				{"5 5" + System.lineSeparator() +
						"1 2 N" + System.lineSeparator() +
						"LMLMLMLMM" + System.lineSeparator() +
						"3 3 E" + System.lineSeparator() +
						"MMRMMRMRRM", "1 3 N" + System.lineSeparator() + "5 1 E"
				}
		};
	}

	@Test(dataProvider = "comandosConjunto")
	public void testProcessar(String comandos, String experado) {
		server.deploy(TestRest.class);
		Form form = new Form();
		form.param("comando", comandos);

		final Client client = ClientBuilder.newClient();
		final String url = Constantes.DEFAULT_PATH_REST + SondaRestService.SONDA + SondaRestService.COMANDAR;
		final String resultado = client.target(TestPortProvider.generateURL(url)).request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		assertEquals(resultado, experado);
	}
}
