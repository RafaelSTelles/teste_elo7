package br.com.rafaelstelles.model;

import java.io.Serializable;
import java.util.Objects;

public class Coordenada implements Serializable {

	protected static final Coordenada INICIAL = new Coordenada(0, 0);

	private int x;
	private int y;

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Coordenada)) {
			return false;
		}

		Coordenada that = (Coordenada) o;
		return getX() == that.getX() && getY() == that.getY();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}

	@Override
	public String toString() {
		return "Coordenada{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	public void incrementaX(int max) {
		if (x > max) {
			throw new IllegalStateException(String.format("Não é possível mover o ponto para um lugar maior que o plano x=%s max=%s", x, max));
		}
		x++;
	}

	public void decrementaX() {
		if (x < INICIAL.getX()) {
			throw new IllegalStateException(String.format("Não é possível mover o ponto menor a coordenada inicial x=%s inicial=%s", x, INICIAL.getX()));
		}
		x--;
	}

	public void incrementaY(int max) {
		if (y > max) {
			throw new IllegalStateException(String.format("Não é possível mover o ponto para um lugar maior que o plano y=%s max=%s", y, max));
		}
		y++;
	}

	public void decrementaY() {
		if (y < INICIAL.getX()) {
			throw new IllegalStateException(String.format("Não é possível mover o ponto menor a coordenada inicial y=%s inicial=%s", x, INICIAL.getY()));
		}
		y--;
	}
}
