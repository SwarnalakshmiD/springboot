package com.quinbay.newservices.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.newservices.data.category;
import com.quinbay.newservices.model.vo.OrderVo;
import com.quinbay.newservices.model.vo.ProductVo;
import com.quinbay.newservices.model.vo.RecommendationVo;

import java.util.List;

public interface CategoryServices {

    List<category> getProduct(String category);

    List<category> updateDetails(int prodId,String key,String value);
    List<category> updateAllDetails(category c);
    String deleteDetails(category c);

     void sendMessage(String msg);
    void sendProduct(category product) throws JsonProcessingException;

    String addProductCall(ProductVo product);

    String updateProductCall(ProductVo product);

    String deleteProductCall(Long id);

    List<ProductVo> getProductCall();

    List<ProductVo> searchProductCall(Long id, String pname, String cname);

    String orderProductCall(OrderVo order);

    List<OrderVo> viewAllOrdersCall();

    List<OrderVo> viewOrderHistoryCall(String orderId);

    List<RecommendationVo> getRecommendationCall(Long customerId);
}
