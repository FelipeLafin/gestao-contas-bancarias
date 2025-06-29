package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroConta;

    private String nomeTitular;

    private Double saldo = 0.0;

    private boolean ativa = true;

    public ContaBancaria() {}

    public ContaBancaria(String numeroConta, String nomeTitular, boolean ativa) {
        this.numeroConta = numeroConta;
        this.nomeTitular = nomeTitular;
        this.ativa = ativa;
    }

    public Long getId() { return id; }
    public String getNumeroConta() { return numeroConta; }
    public String getNomeTitular() { return nomeTitular; }
    public Double getSaldo() { return saldo; }
    public boolean isAtiva() { return ativa; }

    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    public void depositar(double valor) {
        if (valor > 0) this.saldo += valor;
        else throw new RuntimeException("Valor de depósito inválido");
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) this.saldo -= valor;
        else throw new RuntimeException("Saldo insuficiente ou valor inválido");
    }
}