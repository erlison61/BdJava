package com.mycompany.sistema;

import com.mycompany.Contas.TipoDeConta;

import com.mycompany.database.ConexaoMySQL;
import com.mycompany.interfaces.Aplicacao;
import com.mycompany.interfaces.Conta;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public  class Sistema implements Aplicacao {
    
    public Sistema() {
        super();
    }

    public void cadastrarBanco(Banco banco) {
        if(ConexaoMySQL.cadastrarBanco(banco)) {
            JOptionPane.showMessageDialog(null,"Banco: '" + banco.getNome() + "' cadastrado com sucesso!");
        }else{
            JOptionPane.showMessageDialog(null,"Erro ao cadastrastrar Banco: '" + banco.getNome() + "'");
        }
    }

    public void cadastrarConta(TipoDeConta conta, Integer codigoDoBanco) throws SQLException {
        com.mycompany.interfaces.Conta contaCheck = this.buscaConta(codigoDoBanco, conta.numeroDaConta(), conta.numeroDaAgencia());
        if (contaCheck == null){
            JOptionPane.showMessageDialog(null,"Conta cadastrada com sucesso!");
            ConexaoMySQL.cadastrarConta(conta, codigoDoBanco);
        }else{
            JOptionPane.showMessageDialog(null,"Conta já existente");
        }
    }

    public void depositar(Integer codigoBanco, String numeroDaConta, String numeroDaAgencia, Double valor){
        TipoDeConta conta = ConexaoMySQL.listarConta(codigoBanco, numeroDaConta, numeroDaAgencia);
        if (valor <= 0){
            JOptionPane.showMessageDialog(null,"Informe um valor válido!");
        }else if (conta != null) {
            conta.depositar(valor);
            ConexaoMySQL.atualizarSaldo(conta.getId(), conta.getSaldo());
            JOptionPane.showMessageDialog(null,"Depósito realizado com sucesso!");
        }else{
            JOptionPane.showMessageDialog(null,"Não foi possível realizar o depósito!");
        }
    }


    public void sacar(Integer codigoBanco, String numeroDaConta, String numeroDaAgencia, Double valor){
        if (valor <= 0){
            JOptionPane.showMessageDialog(null,"Informe um valor válido!");
        }else {
            TipoDeConta conta = ConexaoMySQL.listarConta(codigoBanco, numeroDaConta, numeroDaAgencia);
            if (conta != null) {
                conta.sacar(valor);
                ConexaoMySQL.atualizarSaldo(conta.getId(), conta.getSaldo());
                JOptionPane.showMessageDialog(null,"Saque de R$ " + valor + "foi realizado com sucesso!");
            }
        }
    }
    public void exibirSaldo(Integer codigoBanco, String numeroConta, String agencia){
        com.mycompany.interfaces.Conta conta = (com.mycompany.interfaces.Conta) ConexaoMySQL.listarConta(codigoBanco, numeroConta, agencia);
        if (conta == null) {
            JOptionPane.showMessageDialog(null,"Conta não encontrada!");
        }else{
            conta.exibirSaldo();
        }
    }

    public void transferir(Integer bancoOrigem, String numeroDaContaDeOrigem, String numeroDaAgenciaDeOrigem, Integer bancoDestino, String numeroContaDestino, String numeroDaAgenciaDestino, Double valorDaTransferencia){

        com.mycompany.Contas.TipoDeConta contaDeOrigem = ConexaoMySQL.listarConta(bancoOrigem , numeroDaContaDeOrigem, numeroDaAgenciaDeOrigem);
        com.mycompany.Contas.TipoDeConta contaDestino = ConexaoMySQL.listarConta(bancoDestino, numeroContaDestino, numeroDaAgenciaDestino);
        if(valorDaTransferencia < 0)
            JOptionPane.showMessageDialog(null,"Valor incorreto!");
        if (contaDeOrigem == null || contaDestino == null){
            JOptionPane.showMessageDialog(null,"Dados da conta inválidos!");
        }else {
            Double valor = contaDeOrigem.transferir(contaDestino, valorDaTransferencia);
            if(valor > 0) {
                contaDestino.depositar(valorDaTransferencia);
                ConexaoMySQL.atualizarSaldo(contaDestino.getId(), contaDestino.getSaldo());
                ConexaoMySQL.atualizarSaldo(contaDeOrigem.getId(), contaDeOrigem.getSaldo());
                JOptionPane.showMessageDialog(null,"R$ : " + valorDaTransferencia + " transferido de :" + contaDeOrigem.numeroDaConta() + " para " + contaDestino.numeroDaConta());

            }
        }
    }

    public void encerrarConta(Integer codigoBanco, String numeroDaConta, String numeroDaAgencia){
        com.mycompany.Contas.TipoDeConta conta = ConexaoMySQL.listarConta(codigoBanco, numeroDaConta, numeroDaAgencia);
        if(conta == null){
            return;
        }

        boolean status = ConexaoMySQL.atualizarStatus(conta.getId(), 0);
        if(!status){
            JOptionPane.showMessageDialog(null,"Erro ao tentar encerrada conta!");
        }else{
            JOptionPane.showMessageDialog(null,"Conta encerrada!");
        }
    }

    public void reativarConta(Integer codigoBanco, String numeroDaConta, String numeroDaAgencia){
        com.mycompany.Contas.TipoDeConta conta = ConexaoMySQL.listarConta(codigoBanco, numeroDaConta, numeroDaAgencia);
        if(conta == null){
            return;
        }
        boolean status = ConexaoMySQL.atualizarStatus(conta.getId(), 1);
        if(!status){
            JOptionPane.showMessageDialog(null,"A conta já está ativa!");
        }else{
            JOptionPane.showMessageDialog(null,"Conta ativada");
        }
    }

    public void exibirExtrato(Integer codigoBanco, String numero, String agencia) {
        com.mycompany.Contas.TipoDeConta conta = ConexaoMySQL.listarConta(codigoBanco, numero, agencia);
        if(conta == null){
            JOptionPane.showMessageDialog(null,"Conta não encontrada!");
        }else{
            List<Historico> historicos = ConexaoMySQL.listarHistorico(conta.getId());
            conta.setHistoricos(historicos);
            conta.exibirExtrato();
        }
    }

    private Conta buscaConta(Integer codigoBanco, String numeroConta, String agencia){

        com.mycompany.interfaces.Conta conta = (com.mycompany.interfaces.Conta) ConexaoMySQL.listarConta(codigoBanco, numeroConta, agencia);
        if (conta == null) {
            JOptionPane.showMessageDialog(null,"Conta não encontrada!");
            return null;
        }
        return conta;
    }

    public void listarBancos(){
        List<Banco> bancos = ConexaoMySQL.getBancos();

        for (Banco b: bancos ) {
            JOptionPane.showMessageDialog(null, b.toString());
        }
    }


    @Override
    public void calcularRendimento(String data) {
    }
}
