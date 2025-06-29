package com.example.demo.controller;

import com.example.demo.model.ContaBancaria;
import com.example.demo.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contas")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService service;

    @PostMapping
    public ContaBancaria criarConta(@RequestBody ContaBancaria conta) {
        return service.criarConta(conta);
    }

    @GetMapping
    public List<ContaBancaria> buscarTodasContas() {
        return service.buscarTodasContas();
    }

    @GetMapping("/{id}")
    public ContaBancaria buscarPorId(@PathVariable Long id) {
        return service.buscarContaPorId(id);
    }

    @PutMapping("/{id}/depositar")
    public ContaBancaria depositar(@PathVariable Long id, @RequestBody Map<String, Double> payload) {
        return service.depositar(id, payload.get("valor"));
    }

    @PutMapping("/{id}/sacar")
    public ContaBancaria sacar(@PathVariable Long id, @RequestBody Map<String, Double> payload) {
        return service.sacar(id, payload.get("valor"));
    }

    @PutMapping("/{id}/status")
    public ContaBancaria alterarStatus(@PathVariable Long id, @RequestParam boolean ativa) {
        return service.ativarDesativarConta(id, ativa);
    }
}