package com.consorcio.consorcioapi_cleanarchitecture.application.service;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.EdificioDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.UnidadDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.EdificioException;
import com.consorcio.consorcioapi_cleanarchitecture.application.repository.IEdificioRepository;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IEdificioService;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper.IMapper;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.ReplyMessage;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Edificio;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Unidad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EdificioService implements IEdificioService {
    private final IEdificioRepository edificioRepository;
    private IMapper<Edificio, EdificioDTO> mapperEdificio;
    private IMapper<Unidad, UnidadDTO> mapperUnidad;
    private IMapper<Persona, PersonaDTO> mapperPersona;

    @Override
    public Edificio findEdificio(Integer codigo) throws EdificioException {
        Edificio edificio = edificioRepository.findByCodigo(codigo);
        if (edificio == null) {
            throw new EdificioException(ReplyMessage.MESSAGE_QUERY_EMPTY);
        }
        return edificio;
    }

    @Override
    public BaseResponse<List<EdificioDTO>> getAllEdificios() throws Exception {
        try {
            List<Edificio> edificios = edificioRepository.findAll();
            List<EdificioDTO> resultado = edificios.stream()
                    .map(mapperEdificio::toDTO).collect(Collectors.toList());
            return new BaseResponse<>(true, resultado, ReplyMessage.MESSAGE_QUERY);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<List<UnidadDTO>> getUnidadesPorEdificio(Integer codigo) throws Exception {
        try {
            Edificio edificio = findEdificio(codigo);
            var resultado = edificio.getUnidades().stream()
                    .map(mapperUnidad::toDTO).collect(Collectors.toList());
            return new BaseResponse<>(true, resultado, ReplyMessage.MESSAGE_QUERY);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> createEdificio(EdificioDTO edificioDTO) throws Exception {
        try {
            Edificio edificio = Edificio.builder()
                    .codigo(edificioDTO.getCodigo())
                    .nombre(edificioDTO.getNombre())
                    .direccion(edificioDTO.getDireccion())
                    .build();
            edificioRepository.save(edificio);
            return new BaseResponse<>(true, true, ReplyMessage.MESSAGE_SAVE);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Boolean> updateEdificio(EdificioDTO edificioDTO) throws Exception {
        try {
            Edificio edificio = findEdificio(edificioDTO.getCodigo());
            var nombre = edificioDTO.getNombre() == null ? edificio.getNombre() : edificioDTO.getNombre();
            var direccion = edificioDTO.getDireccion() == null ? edificio.getDireccion() : edificioDTO.getDireccion();
            edificio.setNombre(nombre);
            edificio.setDireccion(direccion);
            edificioRepository.save(edificio);
            return new BaseResponse<>(true, true, ReplyMessage.MESSAGE_SAVE);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public BaseResponse<Boolean> deleteEdificio(Integer codigo) throws Exception {
        try {
            Edificio edificio = findEdificio(codigo);
            edificioRepository.delete(edificio);
            return new BaseResponse<>(true, true, ReplyMessage.MESSAGE_QUERY);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public BaseResponse<List<PersonaDTO>> habilitadosPorEdificio(Integer codigo) throws Exception {
        try {
            Edificio edificio = findEdificio(codigo);
            List<PersonaDTO> habilitados = getPersonaDTOs(edificio.habilitados());
            return new BaseResponse<>(true, habilitados, ReplyMessage.MESSAGE_QUERY);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<List<PersonaDTO>> dueniosPorEdificio(Integer codigo) throws Exception {
        try {
            Edificio edificio = findEdificio(codigo);
            List<PersonaDTO> duenios = getPersonaDTOs(edificio.duenios());
            return new BaseResponse<>(true, duenios, ReplyMessage.MESSAGE_QUERY);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse<List<PersonaDTO>> habitantesPorEdificio(Integer codigo) throws Exception {
        try {
            Edificio edificio = findEdificio(codigo);
            List<PersonaDTO> habitantes = getPersonaDTOs(edificio.habitantes());
            return new BaseResponse<>(true, habitantes, ReplyMessage.MESSAGE_QUERY);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private List<PersonaDTO> getPersonaDTOs(Set<Persona> personaSet) throws EdificioException {
        List<PersonaDTO> resultado = new ArrayList<>();
        for (Persona persona : personaSet)
            resultado.add(mapperPersona.toDTO(persona));
        if (resultado.isEmpty()) {
            throw new EdificioException(ReplyMessage.MESSAGE_QUERY_EMPTY);
        }
        return resultado;
    }
}
