package com.luoquan.reactserver.controller;

import com.luoquan.reactserver.entity.Category;
import com.luoquan.reactserver.service.CategoryService;
import com.luoquan.reactserver.util.RetCode;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LuoQuan
 * @title: CategoryController
 * @email luo.quan@aliyun.com
 * @date 2019/6/26 22:46
 */

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/manage/category/list")
    public RetCode<List<Category>> listCategories(String parentId) {
        return categoryService.listCategory(parentId);
    }

    @PostMapping("/manage/category/add")
    public RetCode addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PostMapping("/manage/category/update")
    public RetCode updateCategory(@RequestBody Map<String, String> map) {
        String categoryName = map.get("categoryName");
        String categoryId = map.get("categoryId");
        Map<String, String> param = new HashMap<>();
        param.put("id", categoryId);
        param.put("name", categoryName);
        return categoryService.updateCategory(param);
    }

    @GetMapping("/manage/category/categoryName")
    public RetCode getCategoryName(String categoryId){
        return categoryService.getCategoryNameById(categoryId);
    }

}

