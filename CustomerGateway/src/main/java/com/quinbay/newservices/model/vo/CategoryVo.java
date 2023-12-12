package com.quinbay.newservices.model.vo;

import lombok.Data;

@Data
public class CategoryVo {
    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    private Long category_id;
    private String cname;



    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
