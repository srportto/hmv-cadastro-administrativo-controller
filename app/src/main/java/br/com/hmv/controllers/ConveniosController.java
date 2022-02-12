package br.com.hmv.controllers;

import br.com.hmv.dtos.request.ConvenioInsertRequestDTO;
import br.com.hmv.dtos.responses.ConvenioDefaultResponseDTO;
import br.com.hmv.services.ConvenioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/convenios")
@AllArgsConstructor
public class ConveniosController {
    private ConvenioService service;

    @PostMapping
    public ResponseEntity<ConvenioDefaultResponseDTO> insert(@RequestBody @Valid ConvenioInsertRequestDTO dto) {
        var newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDTO dto) {
//        UserDTO newDto = service.update(id, dto);
//        return ResponseEntity.ok().body(newDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<UserDefaultResponseDTO>> findAll(Pageable pageable) {
//        Page<UserDefaultResponseDTO> list = service.findAllPaged(pageable);
//        return ResponseEntity.ok().body(list);
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<UserExtendsResponseDTO> findById(@PathVariable Long id) {
//        UserExtendsResponseDTO dto = service.findById(id);
//        return ResponseEntity.ok().body(dto);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
