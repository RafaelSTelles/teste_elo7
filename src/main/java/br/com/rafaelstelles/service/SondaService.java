package br.com.rafaelstelles.service;

import static br.com.rafaelstelles.util.Constantes.ESPACO;
import static br.com.rafaelstelles.util.Constantes.PRIMEIRA_LINHA;
import static br.com.rafaelstelles.util.Constantes.QUEBRA_LINHA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import br.com.rafaelstelles.model.Comando;
import br.com.rafaelstelles.model.Coordenada;
import br.com.rafaelstelles.model.DadosSonda;
import br.com.rafaelstelles.model.DirecaoCardinal;
import br.com.rafaelstelles.model.Sonda;
import br.com.rafaelstelles.model.TamanhoPlano;

@Singleton
public class SondaService {

	private static final int COMANDOS_POR_SONDA = 2;

	public List<Sonda> processarComando(String comando) {
		final List<String> linhas = quebrarStringEmLinhas(comando);
		final List<Sonda> sondas = new ArrayList<>();
		final int numeroComandosMenosOPrimeiro = linhas.size() - 1;

		if (linhas.size() == 1 || !(numeroComandosMenosOPrimeiro % COMANDOS_POR_SONDA == 0)) {
			throw new IllegalArgumentException("O número de comandos deve ser multiplo de 2 mais 1");
		}

		final TamanhoPlano tamanhoPlano = processarTamanhoPlano(linhas.get(PRIMEIRA_LINHA));
		for (int i = 0; i < linhas.size(); i++) {
			if (i == PRIMEIRA_LINHA) {
				continue;
			}

			final Sonda sonda = processarSonda(linhas.get(i), linhas.get(++i), tamanhoPlano);
			sondas.add(sonda);
		}

		return sondas;
	}

	protected List<String> quebrarStringEmLinhas(String comando) {
		final List<String> resultados = Arrays.asList(comando.split("\\" + QUEBRA_LINHA));
		if(resultados.isEmpty() || resultados.size() == 1) {
			return Arrays.asList(comando.split(QUEBRA_LINHA));
		}
		return resultados;
	}

	private TamanhoPlano processarTamanhoPlano(String linha) {
		final String[] split = limparString(linha).split(ESPACO);
		if (split.length != 2) {
			throw new IllegalArgumentException("Para ser possível descobir a coordenada inicial, é necessário ter dois pontos");
		}

		return new TamanhoPlano(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
	}

	private DadosSonda processarPontoInicialSonda(String linha) {
		final String[] split = limparString(linha).split(ESPACO);
		if (split.length != 3) {
			throw new IllegalArgumentException("Para ser possível descobir a coordenada inicial, é necessário ter dois pontos");
		}

		final Coordenada coordenada = new Coordenada(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
		final DirecaoCardinal direcaoCardinal = DirecaoCardinal.valueOf(split[2]);
		return new DadosSonda(coordenada, direcaoCardinal);
	}

	private List<Comando> processarMovimentosSonda(String linha) {
		linha = limparString(linha);
		List<Comando> comandos = new ArrayList<>();
		for (int i = 0; i < linha.length(); i++) {
			char c = linha.charAt(i);
			comandos.add(Comando.valueOf(String.valueOf(c)));
		}
		return comandos;
	}

	protected Sonda processarSonda(String linha1, String linha2, TamanhoPlano tamanhoPlano) {
		final DadosSonda dadosSonda = processarPontoInicialSonda(linha1);
		final Sonda sonda = new Sonda(dadosSonda);
		final List<Comando> comandos = processarMovimentosSonda(linha2);
		sonda.comandar(comandos, tamanhoPlano);
		return sonda;
	}

	private String limparString(String value) {
		if (value == null) {
			return "";
		}

		return value.trim();
	}

}
