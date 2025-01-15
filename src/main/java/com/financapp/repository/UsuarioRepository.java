package com.financapp.repository;

import com.financapp.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    List<Usuarios> findAllByNomeContainingIgnoreCase(String nome);
}
