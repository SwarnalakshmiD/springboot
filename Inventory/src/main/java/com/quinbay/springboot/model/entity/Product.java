package com.quinbay.springboot.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.quinbay.springboot.model.constant.FieldName;

import javax.persistence.*;

@Entity
@Table(name=FieldName.PRODUCT_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name=FieldName.ID)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name=FieldName.NAME,nullable=false)
    private String pname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name=FieldName.PRICE,nullable=false)
    private double price;

    @Column(name=FieldName.QUANTITY,nullable=false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name=FieldName.CATEGORY_ID,nullable=false)
    private Category category;



}
