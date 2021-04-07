public class State {
    private String value;
	private State anterior;

	public State getAnterior() {
		return anterior;
	}

	public void setAnterior(State anterior) {
		this.anterior = anterior;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String valor) {
		this.value = valor;
	}
}
