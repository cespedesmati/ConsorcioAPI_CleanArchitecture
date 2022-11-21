package com.consorcio.consorcioapi_cleanarchitecture.API.controllers;

import com.consorcio.consorcioapi_cleanarchitecture.application.dto.EdificioDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.dto.UnidadDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IEdificioService;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag( name = "EDIFICIO", description = "API para Edificios" )
@RequestMapping( "/api/edificio" )
public class EdificioController {

    private IEdificioService edificioService;

    @Autowired
    public EdificioController(IEdificioService edificioService) {
        this.edificioService = edificioService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<EdificioDTO>>> getEdificios() throws Exception {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.getAllEdificios());
    }

    @GetMapping( "/{codigo}/unidades" )
    public ResponseEntity<BaseResponse<List<UnidadDTO>>> getUnidadesPorEdificio(@PathVariable Integer codigo) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.getUnidadesPorEdificio(codigo));
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Boolean>> createEdificio(EdificioDTO edificioDTO) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.createEdificio(edificioDTO));
    }

    @PutMapping( "/{codigo}" )
    public ResponseEntity<BaseResponse<Boolean>> updateEdificio(@RequestBody EdificioDTO edificioDTO) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.updateEdificio(edificioDTO));
    }

    @DeleteMapping( "/{codigo}" )
    public ResponseEntity<BaseResponse<Boolean>> eliminarEdificio(@PathVariable Integer codigo) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.deleteEdificio(codigo));
    }

    @GetMapping( "/{codigo}/habilitados" )
    public ResponseEntity<BaseResponse<List<PersonaDTO>>> habilitadosPorEdificio(@PathVariable Integer codigo) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.habilitadosPorEdificio(codigo));
    }

    @GetMapping( "/{codigo}/duenios" )
    public ResponseEntity<BaseResponse<List<PersonaDTO>>> dueniosPorEdificio(@PathVariable Integer codigo) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.dueniosPorEdificio(codigo));
    }

    @GetMapping( "/{codigo}/habitantes" )
    public ResponseEntity<BaseResponse<List<PersonaDTO>>> habitantesPorEdificio(@PathVariable Integer codigo) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(edificioService.habitantesPorEdificio(codigo));
    }

}
