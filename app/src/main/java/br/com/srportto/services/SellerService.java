package br.com.srportto.services;

import br.com.srportto.dtos.responses.SellerDTO;
import br.com.srportto.models.entities.Seller;
import br.com.srportto.repositories.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

    @Transactional(readOnly = true)
    public Page<SellerDTO> findAllPaged(Pageable pageable){
        Page<Seller> sellersPage = sellerRepository.findAll(pageable);
        var result =  sellersPage.map(seller -> new SellerDTO(seller));
        return result;
    }

    @Transactional(readOnly = true)
    public Page<SellerDTO> searchByName(String name, Pageable pageable) {
        Page<Seller> sellersPage = sellerRepository.findByNameContainingIgnoreCase(name, pageable);
        var result =  sellersPage.map(seller -> new SellerDTO(seller));
        return result;
    }
}
