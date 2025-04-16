package com.financapp.repository;

import com.financapp.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByTipo(String tipo);
    List<Movimentacao> findByDataMovimentacaoAfter(LocalDate data);
    List<Movimentacao> findByDataMovimentacaoBefore(LocalDate data);
    List<Movimentacao> findByDataMovimentacaoBetween(LocalDate startDate, LocalDate endDate);
}
