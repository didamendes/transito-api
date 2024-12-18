package com.diogomendes.transito.domain.service;

import com.diogomendes.transito.domain.model.Autuacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroAutuacaoService {

    private final RegistroVeiculoService veiculoService;

    public RegistroAutuacaoService(RegistroVeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Transactional
    public Autuacao registrar(Long id, Autuacao autuacao) {
        return veiculoService.buscar(id).adicionarAutuacao(autuacao);
    }

}
