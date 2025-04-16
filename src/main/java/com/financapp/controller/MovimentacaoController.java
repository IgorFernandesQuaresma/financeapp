package com.financapp.controller;


import com.financapp.model.Movimentacao;
import com.financapp.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository repository;

    @GetMapping
    public List<Movimentacao> listarTodas() {
        return repository.findAll();
    }

    @PostMapping
    public Movimentacao criar(@RequestBody Movimentacao mov) {
        return repository.save(mov); //
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id); //
    }

    @GetMapping("/tipo/{tipo}")
    public List<Movimentacao> buscarPorTipo(@PathVariable String tipo) {
        return repository.findByTipo(tipo);
    }

    @GetMapping("/buscar-por-intervalo")
    public List<Movimentacao> buscarPorIntervalo(
            @RequestParam String dataInicio,
            @RequestParam String dataFim
    ) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);
        return repository.findByDataMovimentacaoBetween(inicio, fim);
    }

    @GetMapping("/buscar-depois")
    public List<Movimentacao> buscarDepois(@RequestParam String data) {
        LocalDate dataFormatada = LocalDate.parse(data);
        return repository.findByDataMovimentacaoAfter(dataFormatada);
    }

    @GetMapping("/buscar-antes")
    public List<Movimentacao> buscarAntes(@RequestParam String data) {
        LocalDate dataFormatada = LocalDate.parse(data);
        return repository.findByDataMovimentacaoBefore(dataFormatada);
    }
}
