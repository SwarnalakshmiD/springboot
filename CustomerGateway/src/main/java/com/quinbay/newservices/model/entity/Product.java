package com.quinbay.newservices.model.entity;


import com.quinbay.newservices.model.constant.FieldName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name=FieldName.PRICE,nullable=false)
    private double price;

    @Column(name=FieldName.QUANTITY,nullable=false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name=FieldName.CATEGORY_ID,nullable=false)
    private Category category;



}
