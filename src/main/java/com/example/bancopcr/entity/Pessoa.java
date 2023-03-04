package com.example.bancopcr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    @Column(name = "posicao_fila")
    private int posicaoFila;
    private String Telefone;

    public Pessoa() {}

    public Pessoa(String nome, int idade, int posicaoFila) {
        this.nome = nome;
        this.idade = idade;
        this.posicaoFila = posicaoFila;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", posicaoFila=" + posicaoFila +
                '}';
    }
}