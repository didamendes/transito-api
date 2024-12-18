package com.diogomendes.transito.api.controller;

import com.diogomendes.transito.api.mapper.AutuacaoMapper;
import com.diogomendes.transito.api.model.AutuacaoModel;
import com.diogomendes.transito.api.model.input.AutuacaoInput;
import com.diogomendes.transito.domain.service.RegistroAutuacaoService;
import com.diogomendes.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos/{id}/autuacoes")
public class AutuacaoController {

    private final AutuacaoMapper mapper;
    private final RegistroVeiculoService veiculoService;
    private final RegistroAutuacaoService autuacaoService;

    public AutuacaoController(AutuacaoMapper mapper, RegistroVeiculoService veiculoService, RegistroAutuacaoService autuacaoService) {
        this.mapper = mapper;
        this.veiculoService = veiculoService;
        this.autuacaoService = autuacaoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long id,
                                   @Valid @RequestBody AutuacaoInput autuacaoInput) {
        return mapper.toModel(autuacaoService.registrar(id, mapper.toEntity(autuacaoInput)));
    }

    @GetMapping
    public List<AutuacaoModel> listar(@PathVariable Long id) {
        return mapper.toCollectionModel(veiculoService.buscar(id).getAutuacoes());
    }

}
