package com.quinbay.springboot.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.springboot.dao.api.ProductRepository;
import com.quinbay.springboot.data.Product;

import com.quinbay.springboot.services.ProductServices;
import com.quinbay.springboot.model.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("prodServices")
public class ProductServiceImpl implements ProductServices {


    @Autowired
    ProductRepository productRepository;



    @Override
    public  List<ProductVo> getProductList()
    {
        List<com.quinbay.springboot.model.entity.Product> productList = productRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(productList,List.class);

    }
   public List<ProductVo> searchProductList(Long id, String pname, String cname)
   {
       List<com.quinbay.springboot.model.entity.Product> productList = productRepository.findByIdOrPnameOrCategory_Cname(id, pname, cname);
       ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.convertValue(productList,List.class);
   }
    @Override
    public String postProductList(ProductVo product)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        productRepository.save(objectMapper.convertValue(product, com.quinbay.springboot.model.entity.Product.class));
        return "product added";
    }

    @Override
    public String updateProductList(ProductVo product)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        productRepository.save(objectMapper.convertValue(product, com.quinbay.springboot.model.entity.Product.class));
        return "product updated";
    }

    public String deleteProductList(Long id)
    {

        productRepository.deleteById(id);
        return "product deleted";
    }

    public String checkQuantity(Long id,int quantity) {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<com.quinbay.springboot.model.entity.Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            com.quinbay.springboot.model.entity.Product product = optionalProduct.get();
            com.quinbay.springboot.model.entity.Product mappedProduct = objectMapper.convertValue(product, com.quinbay.springboot.model.entity.Product.class);

            if (mappedProduct.getQuantity() < quantity) {
                return "Order cannot be placed, insufficient quantity available";
            }
            else {
            mappedProduct.setQuantity ( mappedProduct.getQuantity() - quantity);

                productRepository.save(objectMapper.convertValue(mappedProduct, com.quinbay.springboot.model.entity.Product.class));
                return "Sufficient quantity available";
            }
        }
        else {
            return "Product not found";

        }
    }

    @KafkaListener(topics = "com.quinbay.product.create",groupId="group-id")
    public void listen(String msg) throws JsonProcessingException
    {
        ObjectMapper obj =new ObjectMapper();
        Product product=obj.readValue(msg,Product.class);
        System.out.println("Recieved message in group - group-id: " + product.getProductName());
    }

    @Override
    @Cacheable(value = "SpringBoot",key="#key")
    public String redisCache(int key,String value)
    {
        return value;
    }

}
