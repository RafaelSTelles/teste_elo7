package br.com.rafaelstelles.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.rafaelstelles.util.Constantes;

@ApplicationPath(Constantes.DEFAULT_PATH_REST)
public class RestApplication extends Application {
}
