package br.com.rafaelstelles.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.rafaelstelles.model.Sonda;
import br.com.rafaelstelles.service.SondaService;

@Path(SondaRestService.SONDA)
public class SondaRestService {

	private static final Logger LOGGER = Logger.getLogger(SondaRestService.class.getName());

	public static final String SONDA = "/sonda";
	public static final String ECHO = "/echo";
	public static final String COMANDAR = "/comandar";

	@Inject
	private SondaService sondaService;

	@GET
	@Path(ECHO + "/{echo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response echo(final @PathParam("echo") String echo) {
		return Response.ok(echo).build();
	}

	@POST
	@Path(COMANDAR)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response comandar(final @FormParam("comando") String comando) {
		try {
			final List<Sonda> sondas = sondaService.processarComando(comando);
			final String resultado = Sonda.paraString(sondas);
			return Response.ok(resultado).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.encoding(e.getMessage())
					.build();
		}
	}

	public void setSondaService(SondaService sondaService) {
		this.sondaService = sondaService;
	}
}
