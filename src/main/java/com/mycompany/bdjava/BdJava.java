package com.mycompany.bdjava;

import com.mycompany.Contas.ContaCorrente;
import com.mycompany.Contas.ContaEspecial;
import com.mycompany.Contas.ContaPoupanca;
import com.mycompany.Contas.TipoDeConta;
import com.mycompany.sistema.Banco;
import com.mycompany.sistema.Sistema;


import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;

public class BdJava {
    public static void main(String[] args) throws SQLException {
      // Cria o objeto sistema
        Sistema sistema = new Sistema();

        Scanner input = new Scanner(System.in);
        int opcao = 1;
        OUTER:
        while (opcao != 0) {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                 -----BANCO-----
                                                                 
                                                                 Para continuar, digite uma op\u00e7\u00e3o:
                                                                 
                                                                 1 - Cadastrar novo Banco
                                                                 2 - Listar Banco
                                                                 3 - Cadastrar nova Conta
                                                                 4 - Depositar
                                                                 5 - Sacar
                                                                 6 - Transferir
                                                                 7 - Exibir saldo
                                                                 8 - Exibir extrato
                                                                 9 - Ver rendimentos
                                                                 10 - Desativar conta
                                                                 11 - Reativar conta
                                                                 0 - Sair"""));
            switch (opcao) {
                case 1 -> {
                    String nomeBanco = JOptionPane.showInputDialog("""
                                                                   ----- CADASTRO DE NOVO BANCO -----
                                                                   Nome do Banco a ser cadastrado""");
                    sistema.cadastrarBanco(new Banco(nomeBanco));
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "----- LISTA DOS BANCOS CADASTRADOS -----");
                    sistema.listarBancos();
                }
                case 3 ->                     {
                        String cliente = JOptionPane.showInputDialog("""
                                                                     ----- CADASTRAR NOVA CONTA -----
                                                                     Informe o nome do cliente:""");
                        String numeroDaConta = JOptionPane.showInputDialog("""
                                                                           ----- CADASTRAR NOVA CONTA -----
                                                                           Informe o n\u00famero da conta:""");
                        String numeroDaAgencia = JOptionPane.showInputDialog("""
                                                                             ----- CADASTRAR NOVA CONTA -----
                                                                             Informe o n\u00famero da ag\u00eancia:""");
                        String saldo = JOptionPane.showInputDialog("""
                                                                   ----- CADASTRAR NOVA CONTA -----
                                                                   Informe o valor desejado para abrir a conta:""");
                        String tipo = JOptionPane.showInputDialog("""
                                                                  ----- CADASTRAR NOVA CONTA -----
                                                                  Qual tipo de conta deseja abrir?
                                                                  
                                                                  Escolha uma das op\u00e7\u00f5es: 
                                                                  1 - Conta Corrente
                                                                  2 - Conta Poupan\u00e7a 
                                                                  3 - Conta Especial""");
                        JOptionPane.showMessageDialog(null, """
                                                            Informe o c\u00f3digo do banco:
                                                            Opc\u00f5es na pr\u00f3xima tela!""");
                        sistema.listarBancos();
                        String banco = JOptionPane.showInputDialog("Digite código do banco desejado: ");
                        TipoDeConta conta = null;
                switch (tipo) {
                    case "1" ->{ 
                        conta = new ContaPoupanca(cliente, numeroDaConta, numeroDaAgencia,  Double.parseDouble(saldo), 0.0);
                        sistema.cadastrarConta(conta, Integer.parseInt(banco));
                    }
                    case "2" -> {
                        conta = new ContaCorrente(cliente, numeroDaConta, numeroDaAgencia,  Double.parseDouble(saldo), 0.0);
                        sistema.cadastrarConta(conta, Integer.parseInt(banco));
                    }
                    case "3" -> {
                        System.out.println("Informe o limit da conta especial: ");
                        String limit = input.nextLine();
                        conta = new ContaEspecial(cliente, numeroDaConta, numeroDaAgencia,  Double.parseDouble(saldo), Double.parseDouble(limit));
                        sistema.cadastrarConta(conta, Integer.parseInt(banco));
                    }
                    default -> {
                    }
                }
                

                    }
                case 4 ->                     {
                        System.out.println("############ Depositar ##############");
                        System.out.println("Informe o número da conta:");
                        String numero = input.nextLine();
                        System.out.println("Informe a agência:");
                        String agencia = input.nextLine();
                        System.out.println("Informe o código do banco (veja as opções a seguir):");
                        sistema.listarBancos();
                        String banco = input.nextLine();
                        System.out.println("Informe o valor a ser depositado:");
                        String valor = input.nextLine();
                        sistema.depositar(Integer.parseInt(banco), numero, agencia, Double.parseDouble(valor));
                    }
                case 5 ->                     {
                        System.out.println("############ Sacar ##############");
                        System.out.println("Informe o número da conta:");
                        String numero = input.nextLine();
                        System.out.println("Informe a agência:");
                        String agencia = input.nextLine();
                        System.out.println("Informe o código do banco (veja as opções a seguir):");
                        sistema.listarBancos();
                        String banco = input.nextLine();
                        System.out.println("Informe o valor a ser sacado:");
                        String valor = input.nextLine();
                        sistema.sacar(Integer.parseInt(banco), numero, agencia, Double.parseDouble(valor));
                    }
                case 6 ->                     {
                        System.out.println("############ Transferir ##############");
                        System.out.println("Informe o número da conta:");
                        String numero = input.nextLine();
                        System.out.println("Informe a agência:");
                        String agencia = input.nextLine();
                        System.out.println("Informe o código do banco (veja as opções a seguir):");
                        sistema.listarBancos();
                        String banco = input.nextLine();
                        System.out.println("Informe o número da conta Destino:");
                        String numeroDestino = input.nextLine();
                        System.out.println("Informe a agência da conta Destino:");
                        String agenciaDestino = input.nextLine();
                        System.out.println("Informe o código do banco da conta Destino (veja as opções a seguir):");
                        sistema.listarBancos();
                        String bancoDestino = input.nextLine();
                        System.out.println("Informe o valor a ser transferido:");
                        String valor = input.nextLine();
                        sistema.transferir(Integer.parseInt(banco), numero, agencia, Integer.parseInt(bancoDestino), numeroDestino, agenciaDestino, Double.parseDouble(valor));
                    }
                case 7 ->                     {
                        System.out.println("############ Exibir saldo ##############");
                        System.out.println("Informe o número da conta:");
                        String numero = input.nextLine();
                        System.out.println("Informe a agência:");
                        String agencia = input.nextLine();
                        System.out.println("Informe o código do banco (veja as opções a seguir):");
                        sistema.listarBancos();
                        String banco = input.nextLine();
                        sistema.exibirSaldo(Integer.parseInt(banco), numero, agencia);
                    }
                case 8 ->                     {
                        System.out.println("############ Exibir extrato ##############");
                        System.out.println("Informe o número da conta:");
                        String numero = input.nextLine();
                        System.out.println("Informe a agência:");
                        String agencia = input.nextLine();
                        System.out.println("Informe o código do banco (veja as opções a seguir):");
                        sistema.listarBancos();
                        String banco = input.nextLine();
                        sistema.exibirExtrato(Integer.parseInt(banco), numero, agencia);
                    }
                case 9 -> System.out.println("############ ver rendimento ##############");
                case 10 ->                     {
                        System.out.println("############ Desativar conta ##############");
                        System.out.println("Informe o número da conta:");
                        String numero = input.nextLine();
                        System.out.println("Informe a agência:");
                        String agencia = input.nextLine();
                        System.out.println("Informe o código do banco (veja as opções a seguir):");
                        sistema.listarBancos();
                        String banco = input.nextLine();
                        sistema.encerrarConta(Integer.parseInt(banco), numero, agencia);
                    }
                case 11 ->                     {
                        System.out.println("############ Reativar conta ##############");
                        System.out.println("Informe o número da conta:");
                        String numero = input.nextLine();
                        System.out.println("Informe a agência:");
                        String agencia = input.nextLine();
                        System.out.println("Informe o código do banco (veja as opções a seguir):");
                        sistema.listarBancos();
                        String banco = input.nextLine();
                        sistema.reativarConta(Integer.parseInt(banco), numero, agencia);
                    }
                case 0 -> {
                    System.out.println("############ Finalizando sistema ##############");
                    break OUTER;
                }
                default -> {
                }
            }
        }

    }
}