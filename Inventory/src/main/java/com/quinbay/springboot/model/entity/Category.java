package com.quinbay.springboot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.quinbay.springboot.model.constant.FieldName;

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

    @Column(name=FieldName.CNAME,nullable=false)
    private String cname;

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
