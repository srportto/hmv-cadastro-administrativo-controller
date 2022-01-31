package br.com.srportto.controllers;

import br.com.srportto.dtos.responses.SaleDTO;
import br.com.srportto.dtos.responses.SaleSuccessDTO;
import br.com.srportto.dtos.responses.SaleSumDTO;
import br.com.srportto.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
@AllArgsConstructor
public class SaleController {
    private final SaleService service;

    @GetMapping
    public ResponseEntity<Page<SaleDTO>> findAll(Pageable pageable) {
        Page<SaleDTO> salePage = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(salePage);
    }

    @GetMapping(value = "/amount-by-seller")
    public ResponseEntity<List<SaleSumDTO>> findAmoutGroupedBySeller() {
        List<SaleSumDTO> resultado= service.listAmoutGroupedBySeller();
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping(value = "/success-by-seller")
    public ResponseEntity<List<SaleSuccessDTO>> findSuccessGroupedBySeller() {
        List<SaleSuccessDTO> resultado= service.listSuccessGroupedBySeller();
        return ResponseEntity.ok().body(resultado);
    }
}
