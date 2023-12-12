package com.quinbay.springboot.controller;
import com.quinbay.springboot.model.vo.ProductVo;
import com.quinbay.springboot.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/inventory")

public class HttpMethodController {

    @Autowired
    ProductServices prodServices;

    @GetMapping("/getallproduct")
    public List<ProductVo> getDetails()
    {
        return prodServices.getProductList();
    }

    @GetMapping("/findproduct")
    public List<ProductVo> getDetails(Long id, String pname, String cname)

    {
        return prodServices.searchProductList(id,pname,cname);
    }

    @PostMapping(value = "/addproduct")
    public String saveDetails(@RequestBody ProductVo product)
    {
        return prodServices.postProductList(product);
    }

    @PutMapping(value= "/updateproduct")
    public String updateDetails(@RequestBody ProductVo product)
    {
        return prodServices.updateProductList(product);
    }

    @PutMapping(value="/checkproductquantity")
    public String checkProductQuantity(@RequestParam(value="id") Long id,@RequestParam(value="quantity") int quantity)
    {
        System.out.println("----------------------"+quantity);
        System.out.println("hi");
        return prodServices.checkQuantity(id,quantity);
    }

//    @PostMapping(value="/checkproductquantity")
//    public String checkProductQuantity(Long id,int quantity)
//    {
//        System.out.println("hi");
//        return prodServices.checkQuantity(id,quantity);
//    }

    @DeleteMapping("/deleteproduct")
    public String deleteDetail(@RequestParam(value="id") Long id)
    {
        return prodServices.deleteProductList(id);
    }

    @GetMapping("/redisCache")
    public String getRedisCache(@RequestParam(value="key") int key,@RequestParam(value = "value", required = false) String value)
    {
        return prodServices.redisCache(key,value);
    }


}
