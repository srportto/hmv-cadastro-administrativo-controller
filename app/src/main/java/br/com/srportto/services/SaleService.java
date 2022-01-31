package br.com.srportto.services;

import br.com.srportto.dtos.responses.SaleDTO;
import br.com.srportto.dtos.responses.SaleSuccessDTO;
import br.com.srportto.dtos.responses.SaleSumDTO;
import br.com.srportto.models.entities.Sale;
import br.com.srportto.repositories.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public Page<SaleDTO> findAllPaged(Pageable pageable){
        Page<Sale> salePage = saleRepository.findAll(pageable);
        var result =  salePage.map(sale -> new SaleDTO(sale));
        return result;
    }

    @Transactional(readOnly = true)
    public List<SaleSumDTO> listAmoutGroupedBySeller(){
       return saleRepository.amoutGroupedBySeller();
    }

    @Transactional(readOnly = true)
    public List<SaleSuccessDTO> listSuccessGroupedBySeller(){
        return saleRepository.successGroupedBySeller();
    }

}
