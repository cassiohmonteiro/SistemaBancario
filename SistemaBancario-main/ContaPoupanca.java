// A classe ContaPoupanca extende a classe ContaBancaria.
public class ContaPoupanca extends ContaBancaria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double taxaRendimemto; // Declaração de uma variável de instância 'taxaRendimento' que irá armazenar a taxa de rendimento da poupança.
	
	// Construtor da classe.
	public ContaPoupanca(String numeroConta, double saldo, double taxaRendimemto) throws IllegalAccessException {
		super(numeroConta, saldo, numeroConta); // Chama o construtor da classe mãe (ContaBancaria) passando o número da conta e saldo.
		this.taxaRendimemto = taxaRendimemto; // Inicializa a taxa de rendimento com o valor fornecido.
	}
	
	// Getters e setters
	public double getTaxaRendimemto() {
		return taxaRendimemto;
	}

	public void setTaxaRendimemto(double taxaRendimemto) {
		this.taxaRendimemto = taxaRendimemto;
	}
	
	// Método que calcula o rendimento da conta poupança, multiplicando o saldo pela taxa de rendimento.
	public double calcularRendimento() {
		return getSaldo() * taxaRendimemto;
	}
}
