package com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.EdificioDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.UnidadDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.EdificioException;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Edificio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEdificioService {

    Edificio findEdificio(Integer codigo) throws EdificioException;

    BaseResponse<List<EdificioDTO>> getAllEdificios() throws Exception;

    BaseResponse<Boolean> createEdificio(EdificioDTO edificioDTO) throws Exception;
    BaseResponse<Boolean> updateEdificio(EdificioDTO edificioDTO) throws Exception;

    BaseResponse<Boolean> deleteEdificio(Integer codigo) throws Exception;

    BaseResponse<List<UnidadDTO>> getUnidadesPorEdificio(Integer codigo) throws Exception;

    BaseResponse<List<PersonaDTO>> habilitadosPorEdificio(Integer codigo) throws Exception;

    BaseResponse<List<PersonaDTO>> dueniosPorEdificio(Integer codigo) throws Exception;

    BaseResponse<List<PersonaDTO>> habitantesPorEdificio(Integer codigo) throws Exception;


}
