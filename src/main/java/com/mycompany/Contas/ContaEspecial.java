package com.mycompany.Contas;

public class ContaEspecial extends TipoDeConta {

    public ContaEspecial(String cliente, String numero, String agencia, Double saldo, Double limit) {
        super(cliente, numero, agencia, saldo, "CONTA ESPECIAL", limit);
    }

    public ContaEspecial() {
    }

    @Override
    public Double sacar(Double valor) {
        if(valor > (this.getSaldo() + this.limit)){
            System.out.println("Saldo insuficiente");
            return -1.0;
        }else{
            this.saldo = this.saldo - valor;
            if(this.saldo < 0){
                this.limit = this.saldo + this.saldo;
                this.saldo = 0.0;
            }
            registrarHistorico("Saque", valor);
            return this.saldo;
        }
    }

    @Override
    public void exibirSaldo() {
        System.out.println("Saldo atual R$: " + this.saldo + " + R$: " + this.limit + " de limite");
    }
}
