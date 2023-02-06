package com.mycompany.Contas;

import com.mycompany.database.ConexaoMySQL;
import com.mycompany.interfaces.Conta;
import com.mycompany.sistema.Historico;
import com.mycompany.sistema.Sistema;

import javax.sound.midi.SysexMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TipoDeConta implements Conta {

    protected Integer id;
    protected String cliente;
    protected String numeroDaConta;
    protected String numeroDaAgencia;
    protected Integer ativa;
    protected Double saldo;
    protected String tipo;
    protected Double limit;

    protected List<Historico> historicos;

    public TipoDeConta(){
    }

    public TipoDeConta(String cliente, String numeroDaConta, String numeroDaAgencia, Double saldo, String tipo, Double limit) {
        this.cliente = cliente;
        this.numeroDaConta = numeroDaConta;
        this.numeroDaAgencia = numeroDaAgencia;
        this.ativa = 1;
        this.saldo = saldo;
        this.historicos = new ArrayList<>();
        this.tipo = tipo;
        this.limit = limit;
    }

    public TipoDeConta(Integer id, String cliente, String numeroDaConta, String numeroDaAgencia, Integer ativa, Double saldo, String tipo, Double limit) {
        this.id = id;
        this.cliente = cliente;
        this.numeroDaConta = numeroDaConta;
        this.numeroDaAgencia = numeroDaAgencia;
        this.ativa = ativa;
        this.saldo = saldo;
        this.tipo = tipo;
        this.limit = limit;
        this.historicos = new ArrayList<>();
    }

    @Override
    public Double depositar(Double valor) {
        registrarHistorico("Depósito R$: %.2f", valor);
        this.saldo = this.saldo + valor;
        return this.saldo;
    }

    @Override
    public Double sacar(Double valor) {
        if(this.tipo.equals("ESPECIAL") && valor > (this.saldo + this.limit)){
            return -1.0;
        }
        if(this.tipo.equals("ESPECIAL")){
            this.saldo = this.saldo - valor;
            if(this.saldo < 0){
                this.limit = this.limit + this.saldo;
                this.saldo = 0.0;
            }
            registrarHistorico("Saque", valor);
            return this.saldo + this.limit;
        }
        registrarHistorico("Saque", valor);
        this.saldo = this.saldo - valor;
        return this.saldo;
    }

    @Override
    public Double transferir(Conta contaDestino, Double valor) {

        if(valor > this.saldo){
            return -1.0;
        }
        this.saldo = this.saldo - valor;

        registrarHistorico("Transferência enviada", valor);
        contaDestino.depositar(valor);
        contaDestino.registrarHistorico("Transferência recebida", valor);
        return this.saldo;
    }

    @Override
    public void exibirSaldo() {
        System.out.println("Saldo atual R$: " + this.saldo);
    }

    @Override
    public void exibirExtrato() {
        System.out.println("interfaces.Conta : " + this.numeroDaConta + " agência: " + this.numeroDaAgencia);
        for (int i = 0; i < historicos.size(); i++) {
            System.out.println(String.valueOf(historicos.get(i)));
        }
    }

    public void registrarHistorico(String message, Double valor) {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Historico movimentacao = new Historico(message, date, valor, this.id);
        ConexaoMySQL.salvaHistorico(movimentacao);
    }

    @Override
    public boolean encerrarConta() {
        if(this.saldo > 0) {
            System.out.println("Há R$: " + this.saldo + " para ser reembolsado!");
            return  false;
        }else if(this.saldo < 0){
            System.out.println("Há R$: " + this.saldo + " para ser pago!");
        }
        this.ativa = 0;
        return true;
    }

    @Override
    public boolean reativarConta() {
        this.ativa = 1;
        return true;
    }

    @Override
    public String numeroDaConta() {
        return this.numeroDaConta;
    }

    @Override
    public String numeroDaAgencia() {
        return this.numeroDaAgencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(String numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public String getNumeroDaAgencia() {
        return numeroDaAgencia;
    }

    public void setNumeroDaAgencia(String numeroDaAgencia) {
        this.numeroDaAgencia = numeroDaAgencia;
    }

    public Integer getAtiva() {
        return ativa;
    }

    public void setAtiva(Integer ativa) {
        this.ativa = ativa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public List<Historico> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<Historico> historicos) {
        this.historicos = historicos;
    }
}