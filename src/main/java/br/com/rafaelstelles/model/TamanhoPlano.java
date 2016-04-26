package br.com.rafaelstelles.model;

import java.io.Serializable;
import java.util.Objects;

public class TamanhoPlano implements Serializable {

	private final int altura;
	private final int largura;

	public TamanhoPlano(int altura, int largura) {
		this.altura = altura;
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof TamanhoPlano)) {
			return false;
		}

		TamanhoPlano that = (TamanhoPlano) o;
		return getAltura() == that.getAltura() && getLargura() == that.getLargura();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAltura(), getLargura());
	}

	@Override
	public String toString() {
		return "TamanhoPlano{" +
				"altura=" + altura +
				", largura=" + largura +
				'}';
	}
}
