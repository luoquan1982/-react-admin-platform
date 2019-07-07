package com.luoquan.reactserver.service;

import com.luoquan.reactserver.entity.Category;
import com.luoquan.reactserver.util.RetCode;

import java.util.Map;

/**
 * @author LuoQuan
 * @title: CategoryService
 * @email luo.quan@aliyun.com
 * @date 2019/6/26 22:45
 */
public interface CategoryService {

    RetCode addCategory(Category category);

    RetCode listCategory(String parentId);

    RetCode updateCategory(Map<String,String> map);

    RetCode getCategoryNameById(String categoryId);
}
