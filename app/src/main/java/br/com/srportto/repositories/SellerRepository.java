package br.com.srportto.repositories;

import br.com.srportto.models.entities.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    @Query("SELECT obj FROM Seller obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Seller> searchByName(String name, Pageable pageable);

    Page<Seller> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
