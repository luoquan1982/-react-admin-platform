package com.luoquan.reactserver.service.impl;

import com.luoquan.reactserver.entity.Category;
import com.luoquan.reactserver.mapper.CategoryDao;
import com.luoquan.reactserver.service.CategoryService;
import com.luoquan.reactserver.util.RetCode;
import com.luoquan.reactserver.util.SuperUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LuoQuan
 * @title: CategoryServiceImpl
 * @email luo.quan@aliyun.com
 * @date 2019/6/26 22:46
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public RetCode addCategory(Category category) {
        String parentId = category.getParentId();
        String name = category.getName();

        if (StringUtils.isAnyBlank(parentId, name)) {
            return new RetCode(401,"缺少必要的参数");
        }

        int result;
        String id = SuperUtil.getUUID(24);
        category.setId(id);
        result = categoryDao.isCategoryExist(name);
        if (result > 0) {
            return new RetCode(402,"分类已存在");
        }
        //添加一级分类
        if ("0".equals(parentId)) {
            //新建已经分类
            result = categoryDao.addCategory(category);
            if (result > 0) {
                return RetCode.success();
            }
            return new RetCode(400,"创建分类失败");
        }
        //如果是二级分类,需要查看父分类存不存在,如果不存在则不能新建
        result = categoryDao.isParentCategoryExist(parentId);
        if (result == 0) {
            return new RetCode(403,"父分类不存在,无法创建子分类");
        }
        result = categoryDao.addCategory(category);
        if (result > 0) {
            return RetCode.success();
        }
        return new RetCode(403,"创建分类失败");
    }

    @Override
    public RetCode listCategory(String parentId) {
        List<Category> list = null;
        try {
            list = categoryDao.listCategory(parentId);
            return RetCode.success(list);
        } catch (Exception e) {
            return new RetCode(-1,e.getLocalizedMessage());
        }
    }

    @Override
    public RetCode updateCategory(Map<String, String> map) {
        String name = map.get("name");
        String id = map.get("id");
        if (StringUtils.isAnyBlank(id, name)) {
            return new RetCode(401,"缺少必要的参数");
        }

        try {
            int result = categoryDao.updateCategory(map);
            if(result>0) {
                return RetCode.success();
            }
            return new RetCode(405,"未找到修改的数据");
        } catch (Exception e) {
            return new RetCode(-1,"服务器错误");
        }
    }

    @Override
    public RetCode getCategoryNameById(String categoryId) {
        if(StringUtils.isBlank(categoryId)){
            return new RetCode(401,"缺少必要的参数");
        }

        RetCode retCode;
        String categoryName = categoryDao.getCategoryNameById(categoryId);
        List<String> result = new ArrayList<>();
        result.add(categoryName);
        retCode = RetCode.success(result);
        return retCode;
    }
}
