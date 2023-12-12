package com.quinbay.newservices.dao.api;

import com.quinbay.newservices.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Product, Long> {


    }


