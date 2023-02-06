package com.mycompany.Contas;

public class ContaPoupanca extends TipoDeConta {

    public ContaPoupanca() {}

    public ContaPoupanca(Integer id, String cliente, String numeroDaConta, String numeroDaAgencia, Integer ativa, Double saldo, String tipo, Double limit) {
        super(id, cliente, numeroDaConta, numeroDaAgencia, ativa, saldo, tipo, limit);
    }

    public ContaPoupanca(String cliente, String numeroDaConta, String numeroDaAgencia, Double saldo, Double limit) {
        super(cliente, numeroDaConta, numeroDaAgencia, saldo, "CONTA POUPANCA", limit);
    }
}
