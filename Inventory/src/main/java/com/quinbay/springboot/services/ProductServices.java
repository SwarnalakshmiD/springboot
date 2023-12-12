package com.quinbay.springboot.services;

import com.quinbay.springboot.data.Product;
import com.quinbay.springboot.model.vo.CategoryVo;
import com.quinbay.springboot.model.vo.ProductVo;

import java.util.List;


public interface ProductServices {



    List<ProductVo> getProductList();
    String postProductList(ProductVo product);
    String updateProductList(ProductVo product);
    String redisCache(int key,String value);

    String deleteProductList(Long id);

    List<ProductVo> searchProductList(Long id, String pname, String cname);

    String checkQuantity(Long id,int quantity);
}

