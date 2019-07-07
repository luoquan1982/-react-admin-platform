package com.luoquan.reactserver.entity;

import lombok.Data;

/**
 * @author luoquan
 */
@Data
public class Category {
    private String id;
    private String parentId;
    private String name;
}
