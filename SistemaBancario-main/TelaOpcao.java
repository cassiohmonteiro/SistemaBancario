import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class TelaOpcao {

	private JFrame frmMenu;
	private JTextField txtOpcao;
	int numeroContas = 0;
	private HashMap<String, ContaBancaria> contas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOpcao window = new TelaOpcao();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaOpcao() {
		initialize();
		contas = new HashMap<>();
		numeroContas = GerenciadorContas.carregarContas(contas);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("MENU");
		frmMenu.setBounds(100, 100, 450, 300);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Escolha uma opção:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(130, 11, 231, 37);
		frmMenu.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1 - Criar conta");
		lblNewLabel_1.setBounds(70, 50, 91, 14);
		frmMenu.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("2 - Procurar Conta");
		lblNewLabel_2.setBounds(70, 75, 110, 14);
		frmMenu.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("3 - Apagar Conta");
		lblNewLabel_3.setBounds(70, 100, 102, 14);
		frmMenu.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("4 - Gerar relatório");
		lblNewLabel_4.setBounds(70, 125, 131, 14);
		frmMenu.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("0 - Sair");
		lblNewLabel_5.setBounds(70, 150, 46, 14);
		frmMenu.getContentPane().add(lblNewLabel_5);
		
		txtOpcao = new JTextField();
		txtOpcao.setBounds(130, 210, 163, 29);
		frmMenu.getContentPane().add(txtOpcao);
		txtOpcao.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcao = Integer.parseInt(txtOpcao.getText());
					switch (opcao) {
					case 0: {
						GerenciadorContas.salvarContas(contas);
						JOptionPane.showMessageDialog(null, "Obrigado! Volte sempre!");
						System.exit(0);
						break;
					}
					case 1: {
					    frmMenu.dispose();
					    
					    // Opção de criar conta
					    String[] tipos = {"Poupança", "Corrente"};
					    JRadioButton radioButtonPoupanca = new JRadioButton("Poupança");
					    JRadioButton radioButtonCorrente = new JRadioButton("Corrente");

					    // Grupo de botões para permitir apenas uma seleção
					    ButtonGroup group = new ButtonGroup();
					    group.add(radioButtonPoupanca);
					    group.add(radioButtonCorrente);

					    JTextField txtSaldo = new JTextField();

					    Object[] message = {
					        "Nova Conta",
					        "Tipo:",
					        radioButtonPoupanca,
					        radioButtonCorrente,
					        "Número da conta: " + (++numeroContas),
					        "Saldo em R$:",
					        txtSaldo
					    };

					    int option = JOptionPane.showOptionDialog(null, message, "Criar Conta",
					            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

					    if (option == JOptionPane.OK_OPTION) {
					        // Botão de criar conta foi pressionado
					        if (radioButtonPoupanca.isSelected() || radioButtonCorrente.isSelected()) {
					            // Pelo menos uma opção de conta foi selecionada
					            if (!txtSaldo.getText().isEmpty()) {
					                // O saldo foi inserido
					                double saldo = Double.parseDouble(txtSaldo.getText());
					                String tipoConta = radioButtonPoupanca.isSelected() ? "Poupança" : "Corrente";
					                String numeroConta = Integer.toString(numeroContas);

					                // Cria a conta
					                ContaBancaria novaConta = null;
									try {
										novaConta = new ContaBancaria(numeroConta, saldo, tipoConta);
									} catch (IllegalAccessException e1) {
										e1.printStackTrace();
									}
					                
					                // Adiciona a conta ao HashMap
					                contas.put(numeroConta, novaConta);

					                JOptionPane.showMessageDialog(null, "Conta " + tipoConta + " criada com sucesso!");					              
					            } else {
					                JOptionPane.showMessageDialog(null, "Por favor, insira um saldo.", "Erro ao criar a conta", JOptionPane.ERROR_MESSAGE);
					                numeroContas--;
					            }
					        } else {
					            JOptionPane.showMessageDialog(null, "Por favor, selecione um tipo de conta.", "Erro ao criar a conta", JOptionPane.ERROR_MESSAGE);
					            numeroContas--;
					        }
					    } else {
					    	JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					    }
					    GerenciadorContas.salvarContas(contas);
					    frmMenu.setVisible(true);
					    break;
					}
					case 2: {
						frmMenu.dispose();
						
					    String numeroConta = JOptionPane.showInputDialog("DIGITE O NÚMERO DA CONTA:");
					    
					    if(numeroConta == null) {
					    	// O usuário clicou em Cancelar ou fechou a janela
					    	frmMenu.setVisible(true);
					    	break;
					    }
					    try {
					    	if (contaExiste(numeroConta)) {
						        JTextField txtOpcaoOperacao = new JTextField();

						        Object[] message = {
						            "Escolha uma opção:",
						            "1-DEPOSITAR\n2-SACAR\n3-TRANSFERIR\n4-GERAR RELATÓRIO\n0-VOLTAR",
						            "Digite sua opção:",
						            txtOpcaoOperacao
						        };

						        int option = JOptionPane.showOptionDialog(null,
						                message,
						                "Opções",
						                JOptionPane.OK_CANCEL_OPTION,
						                JOptionPane.WARNING_MESSAGE,
						                null,
						                null,
						                null);

						        if (option == JOptionPane.OK_OPTION) {
						            int operacao = Integer.parseInt(txtOpcaoOperacao.getText());

						            switch (operacao) {
						                case 1: // 1-DEPOSITAR
						                    double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("DIGITE O VALOR R$:"));
						                    contas.get(numeroConta).depositar(valorDeposito);
						                    JOptionPane.showMessageDialog(null, "Depósito efetuado. Novo saldo: " + contas.get(numeroConta).getSaldo());
						                    frmMenu.setVisible(true);
						                    break;
						                case 2: // 2-SACAR
						                    double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("DIGITE O VALOR R$:"));
						                    try {
						                        contas.get(numeroConta).sacar(valorSaque);
						                        JOptionPane.showMessageDialog(null, "Saque efetuado. Novo saldo: " + contas.get(numeroConta).getSaldo());
						                    } catch (SaldoInsuficienteException e1) {
						                        JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						                    }
						                    frmMenu.setVisible(true);
						                    break;
						                case 3: // 3-TRANSFERIR
						                    String numeroContaDestino = JOptionPane.showInputDialog("DIGITE O NÚMERO DA CONTA DESTINATÁRIA:");
						                    if (contaExiste(numeroContaDestino)) {
						                        double valorTransferencia = Double.parseDouble(JOptionPane.showInputDialog("DIGITE O VALOR R$:"));
						                        try {
						                            contas.get(numeroConta).sacar(valorTransferencia);
						                            contas.get(numeroContaDestino).depositar(valorTransferencia);
						                            JOptionPane.showMessageDialog(null, "Transferência efetuada!");
						                        } catch (SaldoInsuficienteException e1) {
						                            JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						                        }
						                    } else {
						                        JOptionPane.showMessageDialog(null, "Conta destinatária inexistente ou inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
						                    }
						                    frmMenu.setVisible(true);
						                    break;
						                case 4: // 4-GERAR RELATÓRIO
						                    ContaBancaria contaSelecionada = contas.get(numeroConta);
						                    String relatorio = "Número da Conta: " + contaSelecionada.getNumeroConta() +
						                            "\nSaldo: R$" + contaSelecionada.getSaldo();

						                    if (contaSelecionada instanceof ContaCorrente) {
						                        double limiteChequeEspecial = ((ContaCorrente) contaSelecionada).getLimiteChequeEspecial();
						                        relatorio += "\nLimite do Cheque Especial: R$" + limiteChequeEspecial;
						                    }

						                    JOptionPane.showMessageDialog(null, relatorio, "Relatório", JOptionPane.INFORMATION_MESSAGE);
						                    frmMenu.setVisible(true);
						                    break;
						                case 0: // 0-VOLTAR
						                	frmMenu.setVisible(true);
						                    break;
						                default:
						                	JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, escolha uma opção de 0 a 4.", "Erro", JOptionPane.ERROR_MESSAGE);
						                    break;
						            }
						        }
						    } else {
						        JOptionPane.showMessageDialog(null, "Conta inexistente ou inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
						    }
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					    GerenciadorContas.salvarContas(contas);
					    frmMenu.setVisible(true);
					    break;
					}
					case 3: {
					    String numeroContaApagar = JOptionPane.showInputDialog("DIGITE O NÚMERO DA CONTA:");
					    if (contaExiste(numeroContaApagar)) {
					        // Apagar os dados da conta
					        contas.remove(numeroContaApagar);

					        // Mostrar mensagem de confirmação
					        JOptionPane.showMessageDialog(null, "Dados apagados");

					        // Mostrar mensagem de conta apagada
					        JOptionPane.showMessageDialog(null, "CONTA " + numeroContaApagar + " APAGADA!");
					    } else {
					        JOptionPane.showMessageDialog(null, "Conta inexistente ou inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
					    }
					    GerenciadorContas.salvarContas(contas);
					    break;
					}
					case 4: {
					    if (contas.isEmpty()) {
					        JOptionPane.showMessageDialog(null, "Não há contas registradas.", "Erro", JOptionPane.ERROR_MESSAGE);
					    } else {
					        StringBuilder relatorio = new StringBuilder();
					        for (ContaBancaria conta : contas.values()) {
					            relatorio.append("Tipo: ").append(conta.getTipoConta())
					                    .append("\nNúmero da Conta: ").append(conta.getNumeroConta())
					                    .append("\nSaldo: R$").append(conta.getSaldo());
					            if (conta instanceof ContaCorrente) {
					                relatorio.append("\nLimite Cheque Especial: R$").append(((ContaCorrente) conta).getLimiteChequeEspecial());
					            } else if (conta instanceof ContaPoupanca) {
					                relatorio.append("\nTaxa de Rendimento: ").append(((ContaPoupanca) conta).getTaxaRendimemto());
					            }
					            relatorio.append("\n\n");
					        }

					        JOptionPane.showMessageDialog(null, relatorio.toString(), "Relatório de Contas", JOptionPane.INFORMATION_MESSAGE);
					    }
					    break;
					}
					default:
						JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, escolha uma opção de 0 a 4.", "Erro", JOptionPane.ERROR_MESSAGE);
						break;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			private boolean contaExiste(String numeroConta) {
				return contas.containsKey(numeroConta);
			}
			
		});
		btnEnviar.setBounds(306, 209, 89, 29);
		frmMenu.getContentPane().add(btnEnviar);
	}
	
}
