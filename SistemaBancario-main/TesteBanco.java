public class TesteBanco {

	public static void main(String[] args) throws IllegalAccessException {
		try {
			ContaCorrente contaCorrente = new ContaCorrente("123", 1000.0, 500.0);
			contaCorrente.depositar(200.0);
			contaCorrente.sacar(300.0);
			
			System.out.println("Saldo da conta corrente: " + contaCorrente.getSaldo());
			
			ContaPoupanca contaPoupanca = new ContaPoupanca("456", 2000.0, 0.03);
			contaPoupanca.depositar(100.0);
			
			System.out.println("Saldo da conta poupança: " + contaPoupanca.getSaldo());
			System.out.println("Rendimento da conta poupança: " + contaPoupanca.calcularRendimento());
		} catch(SaldoInsuficienteException e) {
			System.out.println(e.getMessage());
		}
	}
}
