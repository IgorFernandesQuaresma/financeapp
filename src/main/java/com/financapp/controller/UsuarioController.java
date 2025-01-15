package com.financapp.controller;

import com.financapp.model.Usuarios;
import com.financapp.repository.UsuarioRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuarios>> getAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getById(@PathVariable Long id){
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuarios>> getByNome(@PathVariable String nome) {
        List<Usuarios> usuarios = usuarioRepository.findAllByNomeContainingIgnoreCase(nome);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se a lista estiver vazia
        } else {
            return ResponseEntity.ok(usuarios); // Retorna 200 OK se a lista não estiver vazia
        }
    }

    @PostMapping
    public ResponseEntity<Usuarios> create(@RequestBody @Valid Usuarios usuario) {
        Usuarios savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuarios> update(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return usuarioRepository.findById(id).map(usuario -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nome":
                        usuario.setNome((String) value);
                        break;
                    case "email":
                        usuario.setEmail((String) value);
                        break;
                    case "senha":
                        usuario.setSenha((String) value); // Certifique-se de criptografar a senha aqui, se necessário.
                        break;
                }
            });
            Usuarios updatedUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.ok(updatedUsuario);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Usuarios> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        usuarioRepository.deleteById(id);
    };


}
