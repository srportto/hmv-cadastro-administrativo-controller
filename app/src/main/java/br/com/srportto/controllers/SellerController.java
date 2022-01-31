package br.com.srportto.controllers;

import br.com.srportto.dtos.responses.SellerDTO;
import br.com.srportto.services.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sellers")
@AllArgsConstructor
public class SellerController {

    private final SellerService service;

    @GetMapping
    public ResponseEntity<Page<SellerDTO>> findAll(Pageable pageable) {
        Page<SellerDTO> sallerPage= service.findAllPaged(pageable);
        return ResponseEntity.ok().body(sallerPage);
    }

    @GetMapping(value = "/search-name")
    public ResponseEntity<Page<SellerDTO>> searchByName(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        Page<SellerDTO> result = service.searchByName(name, pageable);
        return ResponseEntity.ok(result);
    }
}
