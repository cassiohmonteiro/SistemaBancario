public class ContaCorrente extends ContaBancaria implements MovimentacaoBancaria{
	// A classe ContaCorrente extende a classe ContaBancaria e implementa a interface MovimentacaoBancaria.
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double limiteChequeEspecial; // Declaração de uma variável de instância 'limiteChequeEspecial' que irá armazenar o limite do cheque especial.
	
	// Construtor da classe.
	public ContaCorrente(String numeroConta, double saldo, double limiteChequeEspecial) throws IllegalAccessException {
		super(numeroConta, saldo, numeroConta); // Chama o construtor da classe mãe (ContaBancaria) passando o número da conta e saldo.
		this.limiteChequeEspecial = limiteChequeEspecial; // Inicializa o limite do cheque especial com o valor fornecido.
	}

	// Getters e setters
	public double getLimiteChequeEspecial() {
		return limiteChequeEspecial; 
	}

	public void setLimiteChequeEspecial(double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}
	
	// Método para realizar um saque. Pode lançar uma exceção SaldoInsuficienteException.
	public void sacar(double valor) throws SaldoInsuficienteException {
		if(valor > getSaldo() + limiteChequeEspecial) { // Se o valor do saque for maior que o saldo mais o limite do cheque especial...
			throw new SaldoInsuficienteException("Saldo insuficiente para conta"); // ...lança uma exceção indicando saldo insuficiente.
		}
		setSaldo(getSaldo() - valor); // Caso contrário, subtrai o valor do saldo.
	}
	
	
}
