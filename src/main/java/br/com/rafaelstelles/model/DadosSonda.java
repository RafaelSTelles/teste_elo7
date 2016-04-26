package br.com.rafaelstelles.model;

import java.io.Serializable;
import java.util.Objects;

public class DadosSonda implements Serializable {

	private Coordenada coordenada;
	private DirecaoCardinal direcaoCardinal;

	public DadosSonda(Coordenada coordenada, DirecaoCardinal direcaoCardinal) {
		this.coordenada = coordenada;
		this.direcaoCardinal = direcaoCardinal;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public DirecaoCardinal getDirecaoCardinal() {
		return direcaoCardinal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof DadosSonda)) {
			return false;
		}

		DadosSonda that = (DadosSonda) o;
		return Objects.equals(getCoordenada(), that.getCoordenada()) &&
				getDirecaoCardinal() == that.getDirecaoCardinal();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCoordenada(), getDirecaoCardinal());
	}

	@Override
	public String toString() {
		return "DadosSonda{" +
				"coordenada=" + coordenada +
				", direcaoCardinal=" + direcaoCardinal +
				'}';
	}
}
