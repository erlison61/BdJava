package com.mycompany.sistema;

public class Historico {
    private String tipoDeMovimentacao;
    private String data;
    private Double valor;
    private Integer conta;

    public Historico(String tipoDeMovimentacao, String dataDaMovimentacao, Double valor, Integer conta) {
        this.tipoDeMovimentacao = tipoDeMovimentacao;
        this.data = dataDaMovimentacao;
        this.valor = valor;
        this.conta = conta;
    }

    public Historico(String tipoDeMovimentacao, String dataDaMovimentacao, Double valor) {
        this.tipoDeMovimentacao = tipoDeMovimentacao;
        this.data = dataDaMovimentacao;
        this.valor = valor;
    }

    public String getTipoDeMovimentacao() {
        return tipoDeMovimentacao;
    }

    public void setTipoDeMovimentacao(String tipoDeMovimentacao) {
        this.tipoDeMovimentacao = tipoDeMovimentacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "sistema.Historico{Operação realizada: " + tipoDeMovimentacao + ", data: " + data +  ", valor: R$ " + valor +  "}";
    }
}
