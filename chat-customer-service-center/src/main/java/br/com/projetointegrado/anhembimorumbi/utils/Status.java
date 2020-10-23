package br.com.projetointegrado.anhembimorumbi.utils;

public enum Status {
	ONLINE(1), OFFLINE(2), BUSY(3), INVISIBLE(4), AWAY(5);
	private final int value;

	private Status(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
