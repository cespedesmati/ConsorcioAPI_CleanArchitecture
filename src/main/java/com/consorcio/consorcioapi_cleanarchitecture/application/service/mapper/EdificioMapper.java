package com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.EdificioDTO;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Edificio;
import org.springframework.stereotype.Component;

@Component
public class EdificioMapper implements IMapper<Edificio, EdificioDTO> {

    @Override
    public Edificio toModel(EdificioDTO edificioDTO) {
        return Edificio.builder()
                .nombre(edificioDTO.getNombre())
                .direccion(edificioDTO.getDireccion())
                .build();
    }

    @Override
    public EdificioDTO toDTO(Edificio edificioModel) {
        return EdificioDTO.builder()
                .codigo(edificioModel.getCodigo())
                .nombre(edificioModel.getNombre())
                .direccion(edificioModel.getDireccion())
                .build();
    }
}
