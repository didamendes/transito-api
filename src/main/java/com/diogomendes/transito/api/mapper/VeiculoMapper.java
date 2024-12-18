package com.diogomendes.transito.api.mapper;

import com.diogomendes.transito.api.model.VeiculoModel;
import com.diogomendes.transito.api.model.input.VeiculoInput;
import com.diogomendes.transito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class VeiculoMapper {

    private final ModelMapper modelMapper;

    public VeiculoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Veiculo toEntity(VeiculoInput veiculoInput) {
        return modelMapper.map(veiculoInput, Veiculo.class);
    }

    public VeiculoModel toModel(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoModel.class);
    }

    public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(this::toModel)
                .toList();
    }

}
