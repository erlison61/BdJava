package com.mycompany.sistema;

import com.mycompany.interfaces.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private Integer id;
    private String nome;
    private final List<Conta> contas;

    public Banco(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cadastrarConta(Conta c){
        this.contas.add(c);
    }

    public Conta buscarContaCadastrada(String numeroDaConta, String numeroDaAgencia){
        for (Conta c: this.contas) {
            if (c.numeroDaConta().equals(numeroDaConta) && c.numeroDaAgencia().equals(numeroDaAgencia)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Nome do Banco: " +
                "\nCódigo : " + id + ", " +
                "\nNome:" + nome +
                "\n\n";
    }
}
