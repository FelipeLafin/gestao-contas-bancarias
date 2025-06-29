package com.example.demo.service;

import com.example.demo.model.ContaBancaria;
import com.example.demo.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository repository;

    public ContaBancaria criarConta(ContaBancaria conta) {
        return repository.save(conta);
    }

    public List<ContaBancaria> buscarTodasContas() {
        return repository.findAll();
    }

    public ContaBancaria buscarContaPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public ContaBancaria depositar(Long id, double valor) {
        ContaBancaria conta = buscarContaPorId(id);
        conta.depositar(valor);
        return repository.save(conta);
    }

    public ContaBancaria sacar(Long id, double valor) {
        ContaBancaria conta = buscarContaPorId(id);
        conta.sacar(valor);
        return repository.save(conta);
    }

    public ContaBancaria ativarDesativarConta(Long id, boolean ativa) {
        ContaBancaria conta = buscarContaPorId(id);
        conta.setAtiva(ativa);
        return repository.save(conta);
    }
}