import java.io.Serializable;

public class ContaBancaria implements MovimentacaoBancaria, Serializable{
	// A classe ContaBancaria implementa a interface MovimentacaoBancaria,
    // o que significa que ela precisa fornecer implementações para todos os métodos definidos na interface.
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroConta; // Declaração de uma variável de instância 'numeroConta' que irá armazenar o número da conta bancária.
	private double saldo; // Declaração de uma variável de instância 'saldo' que irá armazenar o saldo da conta bancária.
	private String tipoConta;
	
	public ContaBancaria(String numeroConta, double saldo, String tipoConta) throws IllegalAccessException {
		// Construtor da classe. Recebe o número da conta e o saldo inicial como parâmetros.
		if(numeroConta == null || numeroConta.isEmpty()) {
			throw new IllegalAccessException("O número da conta não pode ser vazio ou nulo."); // Se o número da conta for nulo ou vazio, uma exceção IllegalAccessException é lançada.
		}
		this.numeroConta = numeroConta; // Inicializa o número da conta com o valor fornecido.
		this.saldo = saldo; // Inicializa o saldo com o valor fornecido.
		this.tipoConta = tipoConta;
	}
	
	// Getters e setters
	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Override // Implementação do método 'depositar' que adiciona o valor ao saldo.
	public void depositar(double valor) {
		this.saldo += valor;
		
	}

	@Override
	public void sacar(double valor) throws SaldoInsuficienteException{
		if(valor > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente para saque."); // Se o valor de saque for maior que o saldo, uma exceção SaldoInsuficienteException é lançada.
		}
		this.saldo -= valor; // Caso contrário, o valor é subtraído do saldo.
	}
	
}
