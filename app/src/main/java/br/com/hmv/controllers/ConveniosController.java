package br.com.hmv.controllers;

import br.com.hmv.dtos.request.ConvenioAtualizaAllRequestDTO;
import br.com.hmv.dtos.request.ConvenioAtualizaStatusRequestDTO;
import br.com.hmv.dtos.request.ConvenioInsertRequestDTO;
import br.com.hmv.dtos.responses.ConvenioDefaultResponseDTO;
import br.com.hmv.services.ConvenioService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "api/convenios")
@AllArgsConstructor
public class ConveniosController {
    private static Logger logger = LoggerFactory.getLogger(ConveniosController.class);
    private ConvenioService service;

    @PostMapping
    public ResponseEntity<ConvenioDefaultResponseDTO> insert(@RequestBody @Valid ConvenioInsertRequestDTO requestDTO) {
        logger.info("solicitacao de inclusao {}", requestDTO );

        var responseDTO = service.criacao(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();

        logger.info("solicitacao de inclusao concluida com sucesso {}", responseDTO.getId() );
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PatchMapping( value = "/{id}/status")
    public ResponseEntity<ConvenioDefaultResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ConvenioAtualizaStatusRequestDTO requestDTO) {
        ConvenioDefaultResponseDTO responseDTO = service.updateStatus(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping( value = "/{id}")
    public ResponseEntity<ConvenioDefaultResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ConvenioAtualizaAllRequestDTO requestDTO) {
        ConvenioDefaultResponseDTO responseDTO = service.updateAll(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ConvenioDefaultResponseDTO>> findAll(Pageable pageable) {
        Page<ConvenioDefaultResponseDTO> responseDtoInList = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(responseDtoInList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ConvenioDefaultResponseDTO> findById(@PathVariable Long id) {
        ConvenioDefaultResponseDTO responseDTO = service.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
