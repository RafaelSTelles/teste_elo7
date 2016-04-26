package br.com.rafaelstelles.rest;

import static br.com.rafaelstelles.util.Constantes.DEFAULT_PATH_REST;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.rafaelstelles.service.SondaService;

@ApplicationPath(DEFAULT_PATH_REST)
public class TestRest extends Application {

	@Override
	public Set<Object> getSingletons() {
		Set<Object> objs = new HashSet<>();

		SondaRestService sondaRestService = new SondaRestService();
		SondaService sondaService = new SondaService();
		sondaRestService.setSondaService(sondaService);

		objs.add(sondaRestService);

		return objs;
	}
}
