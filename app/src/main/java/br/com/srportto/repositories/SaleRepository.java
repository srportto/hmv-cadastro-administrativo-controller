package br.com.srportto.repositories;

import br.com.srportto.models.entities.Sale;
import br.com.srportto.dtos.responses.SaleSuccessDTO;
import br.com.srportto.dtos.responses.SaleSumDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

    @Query("SELECT new br.com.srportto.dtos.responses.SaleSumDTO(obj.seller, SUM(obj.amount)) " +
            " FROM Sale as obj GROUP BY obj.seller")
    List<SaleSumDTO> amoutGroupedBySeller();    // quantidade de vendas por vendedor

    @Query("SELECT new br.com.srportto.dtos.responses.SaleSuccessDTO(obj.seller, SUM(obj.visited), SUM(obj.deals)) " +
            " FROM Sale as obj GROUP BY obj.seller")
    List<SaleSuccessDTO> successGroupedBySeller();  //quantidade de sucesso agrupada por vendedor
}
