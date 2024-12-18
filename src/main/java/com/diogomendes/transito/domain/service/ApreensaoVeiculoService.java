package com.diogomendes.transito.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApreensaoVeiculoService {

    private final RegistroVeiculoService veiculoService;

    public ApreensaoVeiculoService(RegistroVeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Transactional
    public void apreender(Long id) {
        veiculoService.buscar(id).apreender();
    }

    @Transactional
    public void removerApreensao(Long id) {
        veiculoService.buscar(id).removerApreensao();
    }

}
