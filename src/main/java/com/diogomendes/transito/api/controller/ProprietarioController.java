package com.diogomendes.transito.api.controller;

import com.diogomendes.transito.domain.model.Proprietario;
import com.diogomendes.transito.domain.repository.ProprietarioRepository;
import com.diogomendes.transito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("proprietarios")
public class ProprietarioController {

    private final ProprietarioRepository repository;
    private final RegistroProprietarioService service;

    public ProprietarioController(ProprietarioRepository repository, RegistroProprietarioService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Proprietario> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscar(
            @PathVariable Long id
    ) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario salvar(@Valid @RequestBody Proprietario proprietario) {
        return service.salvar(proprietario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> alterar(
            @PathVariable Long id,
            @Valid @RequestBody Proprietario proprietario
    ) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        proprietario.setId(id);
        return ResponseEntity.ok(service.salvar(proprietario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
