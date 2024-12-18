package com.diogomendes.transito.domain.service;

import com.diogomendes.transito.domain.exception.NegocioException;
import com.diogomendes.transito.domain.model.Proprietario;
import com.diogomendes.transito.domain.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository repository;

    public RegistroProprietarioService(ProprietarioRepository repository) {
        this.repository = repository;
    }

    public Proprietario buscarProprietario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NegocioException("Proprietario não encontrado"));
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean emailEmUso = repository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if (emailEmUso) {
            throw new NegocioException("O email já está em uso");
        }

        return repository.save(proprietario);
    }

    @Transactional
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
