package com.example.ruanc.appcardappio.Classes;

/**
 * Created by ruanc on 06/12/2016.
 */

public class Comida {
    String nome;
    String descricao;
    double valor;
    String categoria;

    public Comida(){

    }

    public Comida(String nome, String descricao, double valor, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
