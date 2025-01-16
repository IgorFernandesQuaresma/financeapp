package com.financapp.controller;


import com.financapp.model.Conta;
import com.financapp.model.Usuarios;
import com.financapp.repository.ContaRepository;
import com.financapp.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController         {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Conta>> getAll(){

        return ResponseEntity.ok(contaRepository.findAll());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Long id){
        return contaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Conta>> getContasByUsuarioId(@PathVariable Long usuarioId) {
        List<Conta> contas = contaRepository.findAllByUsuarioId(usuarioId);

        if (contas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se não encontrar contas
        } else {
            return ResponseEntity.ok(contas); // Retorna 200 OK com a lista de contas
        }
    }


    @GetMapping("/usuario/{usuarioId}/nome/{nome}")
    public ResponseEntity<List<Conta>> getContasByNomeAndUsuarioId(@PathVariable Long usuarioId, @PathVariable String nome) {
        List<Conta> contas = contaRepository.findAllByNomeContainingIgnoreCaseAndUsuarioId(nome, usuarioId);

        if (contas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se não encontrar contas
        } else {
            return ResponseEntity.ok(contas); // Retorna 200 OK com a lista de contas
        }
    }


    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Conta> createConta(@PathVariable Long usuarioId, @RequestBody @Valid Conta conta) {


        Optional<Usuarios> usuarioOptional = usuarioRepository.findById(usuarioId);


        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se o usuário não existir
        }

        Usuarios usuario = usuarioOptional.get();
        conta.setUsuario(usuario);
        conta.setSaldoInicial(0);

        Conta savedConta = contaRepository.save(conta);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedConta);
    }


    @PatchMapping("/conta/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return contaRepository.findById(id).map(conta -> {
            // Verifica se há alteração no campo 'nome'
            if (updates.containsKey("nome")) {
                conta.setNome((String) updates.get("nome"));
            }

            // Não permite alteração de 'tipo' e 'saldoInicial', então nada é feito para esses campos.

            // Salva a conta com as alterações
            Conta updatedConta = contaRepository.save(conta);

            // Retorna a conta atualizada com status 200 OK
            return ResponseEntity.ok(updatedConta);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se a conta não existir
    }








}
