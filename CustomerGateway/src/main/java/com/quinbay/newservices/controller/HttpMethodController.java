package com.quinbay.newservices.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.newservices.data.category;
import com.quinbay.newservices.model.entity.Order;
import com.quinbay.newservices.model.vo.OrderVo;
import com.quinbay.newservices.model.vo.ProductVo;
import com.quinbay.newservices.model.vo.RecommendationVo;
import com.quinbay.newservices.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class HttpMethodController {

    @Autowired
    CategoryServices catServices;


    @PostMapping("/publish")
    public void publishKafka() {
        catServices.sendMessage("hi");
    }

    @PostMapping("/publish/product")
    public void publishProductKafka(@RequestBody category product) throws JsonProcessingException {
        catServices.sendProduct(product);
    }

    @GetMapping("/viewallproduct")
    public List<ProductVo> getAllProduct() {
        return catServices.getProductCall();
    }

    @GetMapping("/searchproduct")
    public List<ProductVo> searchProduct(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "pname", required = false) String pname,
            @RequestParam(value = "cname", required = false) String cname) {

        return catServices.searchProductCall(id, pname, cname);
    }


    @PostMapping("/addproduct")
    public String addProductDetails(@RequestBody ProductVo product) {

        return catServices.addProductCall(product);
    }

    @PutMapping("/updateproduct")
    public String updateProductList(@RequestBody ProductVo product) {
        return catServices.updateProductCall(product);
    }

    @DeleteMapping("/deleteproduct")
    public String deletProductList(@RequestParam(value = "id") Long id) {
        return catServices.deleteProductCall(id);
    }

    @GetMapping("/vieworders")
    public List<OrderVo> viewAllOrders() {
        return catServices.viewAllOrdersCall();
    }

    @GetMapping("/vieworderhistory")
    public List<OrderVo> getOrderHistory(@RequestParam(value = "orderId") String orderId) {
        return catServices.viewOrderHistoryCall(orderId);
    }

    @PostMapping("/purchaseproduct")
    public String orderProduct(@RequestBody OrderVo order) {

        return catServices.orderProductCall(order);
    }


    @GetMapping("/viewrecommendation")
    public List<RecommendationVo> viewRecommendationList(@RequestParam(value = "cid") Long customerId) {
        return catServices.getRecommendationCall(customerId);

    }
}
