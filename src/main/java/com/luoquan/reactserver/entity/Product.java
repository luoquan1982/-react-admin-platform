package com.luoquan.reactserver.entity;

import lombok.Data;

/**
 * Product
 *
 * @author LuoQuan
 * @date 2019/7/6 9:51
 */
@Data
public class Product {
    private String id;
    private String name;
    private String desc;
    private Double price;
    private String categoryId;
    private String paCategoryId;
    private String detail;
    private Boolean status;
}
