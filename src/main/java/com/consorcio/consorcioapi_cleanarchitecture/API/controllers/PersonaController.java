package com.consorcio.consorcioapi_cleanarchitecture.API.controllers;


import com.consorcio.consorcioapi_cleanarchitecture.application.dto.PersonaDTO;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.PersonaException;
import com.consorcio.consorcioapi_cleanarchitecture.application.service.interfaceService.IPersonaService;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.BaseResponse;
import com.consorcio.consorcioapi_cleanarchitecture.application.util.ReplyMessage;
import com.consorcio.consorcioapi_cleanarchitecture.domain.Persona;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "PERSONAS", description = "API para Personas")
@Slf4j
@RequestMapping("/api/persona")
public class PersonaController {

    private IPersonaService personaService;

    @Autowired
    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }


    @GetMapping("{documento}")
    public ResponseEntity<BaseResponse<PersonaDTO>> getPersonaPorDocumento(@PathVariable String documento) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.findPersonaToDto(documento));
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<PersonaDTO>>> getPersonas() throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.getAllPersonas());
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<String>> createPersona(@RequestBody PersonaDTO personaDTO) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaService.createPersona(personaDTO));
    }

    @DeleteMapping("{documento}")
    public ResponseEntity<BaseResponse<String>> deletePersona(@PathVariable("documento") String documento) throws Exception {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(personaService.deletePersona(documento));
    }

    @PutMapping("{documento}")
    public ResponseEntity<BaseResponse<String>> updatePersona(@RequestBody PersonaDTO personaDTO) throws Exception {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(personaService.updatePersona(personaDTO));
    }
}

