package br.com.rafaelstelles.model;

import static br.com.rafaelstelles.model.DirecaoCardinal.E;
import static br.com.rafaelstelles.model.DirecaoCardinal.N;
import static br.com.rafaelstelles.model.DirecaoCardinal.S;
import static br.com.rafaelstelles.model.DirecaoCardinal.W;
import static br.com.rafaelstelles.model.DirecaoCardinal.rodarNoventaGrausParaDireita;
import static br.com.rafaelstelles.model.DirecaoCardinal.rodarNoventaGrausParaEsquerda;
import static br.com.rafaelstelles.util.Constantes.ESPACO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Sonda implements Serializable {

	private Coordenada coordenadaInicial;
	private DirecaoCardinal direcaoCardinalInicial;

	private LinkedList<Comando> comandos = new LinkedList<>();

	private Coordenada coordenadaProcessada;
	private DirecaoCardinal direcaoCardinalFinal;

	private Sonda(Coordenada coordenada, DirecaoCardinal direcaoCardinal) {
		this.coordenadaInicial = coordenada;
		this.coordenadaProcessada = coordenada;
		this.direcaoCardinalInicial = direcaoCardinal;
		this.direcaoCardinalFinal = direcaoCardinal;
	}

	public static String paraString(List<Sonda> sondas) {
		return sondas.stream()
				.map(Sonda::getParaExibicao)
				.collect(Collectors.joining(System.lineSeparator()));
	}

	public Sonda(DadosSonda dadosSonda) {
		this(dadosSonda.getCoordenada(), dadosSonda.getDirecaoCardinal());
	}

	public Coordenada getCoordenadaInicial() {
		return coordenadaInicial;
	}

	public DirecaoCardinal getDirecaoCardinalInicial() {
		return direcaoCardinalInicial;
	}

	public Collection<Comando> getComandos() {
		return Collections.unmodifiableCollection(comandos);
	}

	public void comandar(List<Comando> comandos, TamanhoPlano tamanhoPlano) {
		comandos.forEach(c -> comandar(c, tamanhoPlano));
	}

	public void comandar(Comando comando, TamanhoPlano tamanhoPlano) {
		comandos.add(comando);
		processarComando(comando, tamanhoPlano);
	}

	private void processarComando(Comando comando, TamanhoPlano tamanhoPlano) {
		switch (comando) {
			case L:
				moverParaEsquerda();
				break;
			case R:
				moverParaDireita();
				break;
			case M:
				moverParaFrente(tamanhoPlano);
				break;
			default:
				throw new IllegalArgumentException("Movimentação não configurada do movimento");
		}
	}

	private void moverParaEsquerda() {
		direcaoCardinalFinal = rodarNoventaGrausParaEsquerda(direcaoCardinalFinal);
	}

	private void moverParaDireita() {
		direcaoCardinalFinal = rodarNoventaGrausParaDireita(direcaoCardinalFinal);
	}

	private void moverParaFrente(TamanhoPlano tamanhoPlano) {
		if (N == direcaoCardinalFinal) {
			coordenadaProcessada.incrementaY(tamanhoPlano.getAltura());
		}

		if (S == direcaoCardinalFinal) {
			coordenadaProcessada.decrementaY();
		}

		if (E == direcaoCardinalFinal) {
			coordenadaProcessada.incrementaX(tamanhoPlano.getLargura());
		}

		if (W == direcaoCardinalFinal) {
			coordenadaProcessada.decrementaX();
		}
	}

	public DadosSonda getPosicaoFinal() {
		return new DadosSonda(coordenadaProcessada, direcaoCardinalFinal);
	}

	public String getParaExibicao() {
		return coordenadaProcessada.getX() + ESPACO
				+ coordenadaProcessada.getY() + ESPACO
				+ direcaoCardinalFinal;
	}

}
