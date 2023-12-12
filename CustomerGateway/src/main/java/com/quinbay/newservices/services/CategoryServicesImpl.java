package com.quinbay.newservices.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.newservices.data.category;
import com.quinbay.newservices.model.vo.OrderVo;
import com.quinbay.newservices.model.vo.ProductVo;
import com.quinbay.newservices.model.vo.RecommendationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;;
import java.util.Arrays;
import java.util.List;

@Service("catServices")
public class CategoryServicesImpl implements CategoryServices{


    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String productUrl="http://localhost:8091/inventory";
    private String orderUrl="http://localhost:8081/order";
    private String personalizeUrl="http://localhost:8083/personalize";

    @Override
    public List<category> getProduct(String category)
    {

        HttpHeaders headers = new HttpHeaders();;
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(productUrl+"/book")
                .queryParam("category",category).build();
        return restTemplate.exchange(builder.toUriString(),HttpMethod.GET , entity,List.class).getBody();
    }


    @Override
    public List<category> updateDetails(int prodId,String key,String value){
    HttpHeaders headers = new HttpHeaders();;
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(productUrl+"/product/partial")
                .queryParam("id", prodId)
                .queryParam("key", key)
                .queryParam("value",value).build();
        return restTemplate.exchange(builder.toUriString(),HttpMethod.PATCH , entity,List.class).getBody();
    }

    @Override
    public List<category> updateAllDetails(category c)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<category> entity = new HttpEntity<>(c, headers);

        return restTemplate.exchange(productUrl+"/product/change", HttpMethod.PUT, entity, List.class).getBody();
    }
    public String deleteDetails(category c)
    {
        System.out.println("i");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<category> entity = new HttpEntity<>(c, headers);
        return restTemplate.exchange(productUrl+"/product/delete", HttpMethod.DELETE, entity, String.class).getBody();
    }


    @Override
    public void sendMessage(String msg)
    {
        kafkaTemplate.send("com.quinbay.product.create",msg);
    }
    @Override
    public void sendProduct(category product) throws JsonProcessingException
    {

        ObjectMapper objectMapper = new ObjectMapper();

        kafkaTemplate.send("com.quinbay.product.create",objectMapper.writeValueAsString(product));
    }

    public List<ProductVo> getProductCall()
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(productUrl+"/getallproduct", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProductVo>>() {}
        ).getBody();
    }

    public List<ProductVo> searchProductCall(Long id, String pname, String cname)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(productUrl + "/findproduct")
                .queryParam("id", id)
                .queryParam("pname", pname)
                .queryParam("cname", cname);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProductVo>>() {}
        ).getBody();
    }

    public String addProductCall(ProductVo product)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductVo> entity = new HttpEntity<>(product, headers);
        return restTemplate.exchange(productUrl+"/addproduct", HttpMethod.POST, entity, String.class).getBody();


    }

    public String updateProductCall(ProductVo product)
    {
        HttpHeaders header=new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductVo> entity =new HttpEntity<>(product,header);
        System.out.println(entity.getBody());
        return restTemplate.exchange(productUrl+"/updateproduct",HttpMethod.PUT,entity,String.class).getBody();

    }

    public String deleteProductCall(Long id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(productUrl + "/deleteproduct")
                .queryParam("id", id)
                .build();
        return restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class).getBody();
    }

     public List<OrderVo> viewAllOrdersCall()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(orderUrl+"/orderlist", HttpMethod.GET, entity, new ParameterizedTypeReference<List<OrderVo>>() {}
        ).getBody();
    }

    public List<OrderVo> viewOrderHistoryCall(String orderId)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(orderUrl + "/findorderhistory")
                .queryParam("orderId", orderId);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<OrderVo>>() {}
        ).getBody();
    }

    public String orderProductCall(OrderVo order)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductVo> entity = new HttpEntity(order, headers);
        return restTemplate.exchange(orderUrl+"/orderproduct", HttpMethod.POST, entity, String.class).getBody();
    }

    public List<RecommendationVo> getRecommendationCall(Long customerId)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(personalizeUrl + "/viewrecommendation")
                .queryParam("cid", customerId);
        System.out.println(builder.toUriString());
        return restTemplate.exchange(builder.build().toUriString(),HttpMethod.GET, entity, List.class).getBody();
    }

    }




