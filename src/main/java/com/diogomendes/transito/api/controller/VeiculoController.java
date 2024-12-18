package com.diogomendes.transito.api.controller;

import com.diogomendes.transito.api.mapper.VeiculoMapper;
import com.diogomendes.transito.api.model.VeiculoModel;
import com.diogomendes.transito.api.model.input.VeiculoInput;
import com.diogomendes.transito.domain.repository.VeiculoRepository;
import com.diogomendes.transito.domain.service.ApreensaoVeiculoService;
import com.diogomendes.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {

    private final VeiculoMapper mapper;
    private final VeiculoRepository repository;
    private final RegistroVeiculoService service;
    private final ApreensaoVeiculoService apreensaoService;

    public VeiculoController(VeiculoMapper mapper, VeiculoRepository repository, RegistroVeiculoService service, ApreensaoVeiculoService apreensaoService) {
        this.mapper = mapper;
        this.repository = repository;
        this.service = service;
        this.apreensaoService = apreensaoService;
    }

    @GetMapping
    public List<VeiculoModel> listar() {
        return mapper.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastrar(@Valid @RequestBody VeiculoInput veiculoInput) {
        return mapper.toModel(service.cadastrar(mapper.toEntity(veiculoInput)));
    }

    @PutMapping("/{id}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long id) {
        apreensaoService.apreender(id);
    }

    @DeleteMapping("/{id}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void liberar(@PathVariable Long id) {
        apreensaoService.removerApreensao(id);
    }

}
