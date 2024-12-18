package com.diogomendes.transito.api.mapper;

import com.diogomendes.transito.api.model.AutuacaoModel;
import com.diogomendes.transito.api.model.input.AutuacaoInput;
import com.diogomendes.transito.domain.model.Autuacao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutuacaoMapper {

    private final ModelMapper modelMapper;

    public AutuacaoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AutuacaoModel toModel(Autuacao autuacao) {
        return modelMapper.map(autuacao, AutuacaoModel.class);
    }

    public List<AutuacaoModel> toCollectionModel(List<Autuacao> autuacoes) {
        return autuacoes.stream()
                .map(this::toModel)
                .toList();
    }

    public Autuacao toEntity(AutuacaoInput autuacaoInput) {
        return modelMapper.map(autuacaoInput, Autuacao.class);
    }

}
