package br.com.rafaelstelles.model;

public enum DirecaoCardinal {

	N("North"),
	W("West"),
	E("East"),
	S("South");

	private final String descricao;

	DirecaoCardinal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static DirecaoCardinal rodarNoventaGrausParaEsquerda(DirecaoCardinal direcaoCardinal) {
		switch (direcaoCardinal) {
			case N:
				return DirecaoCardinal.W;
			case W:
				return DirecaoCardinal.S;
			case S:
				return DirecaoCardinal.E;
			case E:
				return DirecaoCardinal.N;
			default:
				throw new IllegalArgumentException(String.format("A direção %s não está configurada a rotação para esquerda", direcaoCardinal));
		}
	}

	public static DirecaoCardinal rodarNoventaGrausParaDireita(DirecaoCardinal direcaoCardinal) {
		switch (direcaoCardinal) {
			case N:
				return DirecaoCardinal.E;
			case E:
				return DirecaoCardinal.S;
			case S:
				return DirecaoCardinal.W;
			case W:
				return DirecaoCardinal.N;
			default:
				throw new IllegalArgumentException(String.format("A direção %s não está configurada a rotação para direita", direcaoCardinal));
		}
	}
}
