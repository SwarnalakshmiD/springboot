package com.quinbay.springboot.dao.api;
import com.quinbay.springboot.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

        @Override
        List<Product> findAll();

    List<Product> findByIdOrPnameOrCategory_Cname(Long id, String pname, String cname);


    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Optional<Product> findById(@Param("productId") Long productId);


}

