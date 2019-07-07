package com.luoquan.reactserver.mapper;

import com.luoquan.reactserver.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author LuoQuan
 * @title: CategoryDao
 * @email luo.quan@aliyun.com
 * @date 2019/6/26 22:46
 */
@Repository
@Mapper
public interface CategoryDao {
    /**
     * 添加分类
     * @param category 分类名称
     * @return
     */
    int addCategory(Category category);

    /**
     * 判断分类是否已存在
     * @param name
     * @return
     */
    int isCategoryExist(String name);

    /**
     * 判断父分类是否已经存在
     * @param parentId
     * @return
     */
    int isParentCategoryExist(String parentId);

    /**
     * 按照parentId列出分类列表
     * @param parentId
     * @return
     */
    List<Category> listCategory(String parentId);

    int updateCategory(Map<String,String> map);

    String getCategoryNameById(String categoryId);
}
