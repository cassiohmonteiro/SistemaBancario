// A declaração de uma interface chamada MovimentacaoBancaria.
public interface MovimentacaoBancaria {
	abstract void depositar(double valor); // Método abstrato 'depositar' que não tem implementação na interface. Deve ser implementado por qualquer classe
    // que implemente esta interface. Recebe um parâmetro do tipo double chamado 'valor'.
	
	abstract void sacar(double valor) throws SaldoInsuficienteException; // Método abstrato 'sacar' que também não tem implementação na interface. Deve ser implementado por qualquer classe
    // que implemente esta interface. Recebe um parâmetro do tipo double chamado 'valor' e lança uma exceção
    // do tipo 'SaldoInsuficienteException'.
}
