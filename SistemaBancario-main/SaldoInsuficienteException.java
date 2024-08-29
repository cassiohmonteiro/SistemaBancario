// Declaração de uma classe chamada SaldoInsuficienteException que estende a classe Exception.
public class SaldoInsuficienteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Construtor da classe. Recebe uma mensagem como argumento.
	public SaldoInsuficienteException(String mensagem) {
		super(mensagem); // Chama o construtor da classe Exception (classe pai) e passa a mensagem como argumento.
	}
}
