package com.quinbay.newservices.model.entity;

import com.quinbay.newservices.model.constant.FieldName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name=FieldName.CATEGORY_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name=FieldName.CATEGORY_ID)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long category_id;

    @Column(name=FieldName.CNAME)
    private String cname;

}
