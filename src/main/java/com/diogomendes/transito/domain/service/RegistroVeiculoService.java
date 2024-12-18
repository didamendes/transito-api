package com.diogomendes.transito.domain.service;

import com.diogomendes.transito.domain.exception.EntidadeNaoEncontradaException;
import com.diogomendes.transito.domain.exception.NegocioException;
import com.diogomendes.transito.domain.model.Proprietario;
import com.diogomendes.transito.domain.model.StatusVeiculo;
import com.diogomendes.transito.domain.model.Veiculo;
import com.diogomendes.transito.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class RegistroVeiculoService {

    private final VeiculoRepository repository;
    private final RegistroProprietarioService proprietarioService;

    public RegistroVeiculoService(VeiculoRepository repository, RegistroProprietarioService proprietarioService) {
        this.repository = repository;
        this.proprietarioService = proprietarioService;
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        veiculoNovo(novoVeiculo);
        validarPlaca(novoVeiculo);

        var proprietario = buscarProprietario(novoVeiculo);

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return repository.save(novoVeiculo);
    }

    public Veiculo buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Veiculos não encontrado"));
    }

    private Proprietario buscarProprietario(Veiculo novoVeiculo) {
        return proprietarioService.buscarProprietario(novoVeiculo.getProprietario().getId());
    }

    private void validarPlaca(Veiculo novoVeiculo) {
        boolean placaEmUso = repository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Placa em uso !!!");
        }
    }

    private void veiculoNovo(Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veiculos a ser cadastrado não deve codigo");
        }
    }

}
