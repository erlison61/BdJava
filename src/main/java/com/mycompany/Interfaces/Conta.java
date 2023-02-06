package com.mycompany.interfaces;

public interface Conta {
    public Double depositar(Double valor);
    public Double sacar(Double valor);
    public void exibirExtrato();
    public void exibirSaldo();
    public Double transferir(Conta destino, Double valor);
    public boolean encerrarConta();
    public boolean reativarConta();
    public String numeroDaConta();
    public String numeroDaAgencia();
    public void registrarHistorico(String msg, Double valor);
}
