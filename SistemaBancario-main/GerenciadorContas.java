import java.io.*;
import java.util.HashMap;

public class GerenciadorContas {

    private static final String ARQUIVO_CONTAS = "contas.txt";

    // Método para salvar as contas em um arquivo
    public static void salvarContas(HashMap<String, ContaBancaria> contas) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CONTAS))) {
            outputStream.writeObject(contas);
            System.out.println("Contas salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar as contas: " + e.getMessage());
        }
    }

    // Método para carregar as contas de um arquivo
    @SuppressWarnings("unchecked")
    public static int carregarContas(HashMap<String, ContaBancaria> contas) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_CONTAS))) {
            Object obj = inputStream.readObject();
            if (obj instanceof HashMap) {
                System.out.println("Contas carregadas com sucesso!");
                contas.putAll((HashMap<String, ContaBancaria>) obj);
                return contas.size();  // Retorna o número de contas carregadas
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar as contas: " + e.getMessage());
        }
        return 0;
    }

}
