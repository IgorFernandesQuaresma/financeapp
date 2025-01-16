package com.financapp.repository;

import com.financapp.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findAllByNomeContainingIgnoreCase(String nome);
    List<Conta> findAllByUsuarioId(Long usuarioId);
    List<Conta> findAllByNomeContainingIgnoreCaseAndUsuarioId(String nome, Long usuarioId);
}
