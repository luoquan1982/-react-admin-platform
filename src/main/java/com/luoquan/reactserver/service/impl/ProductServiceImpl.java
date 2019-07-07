package com.luoquan.reactserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.luoquan.reactserver.entity.Product;
import com.luoquan.reactserver.mapper.ProductDao;
import com.luoquan.reactserver.service.ProductService;
import com.luoquan.reactserver.util.RetCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProductServiceImpl
 *
 * @author LuoQuan
 * @date 2019/7/6 10:00
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public RetCode listProduct(int page, int pageSize) throws Exception {
        if (page < 0 || pageSize < 0) {
            return new RetCode(402, "参数错误");
        }

        RetCode retCode;
        try {
            PageHelper.startPage(page, pageSize);
            List<Product> data = productDao.listProduct();
            retCode = RetCode.success(data);
        } catch (Exception e) {
            throw new Exception("服务器错误");
        }
        return retCode;
    }

    @Override
    public RetCode searchProduct(int page, int pageSize, Map<String, String> param) throws Exception {

        if (page < 0 || pageSize < 0 || null == param || param.isEmpty()) {
            return new RetCode(401, "缺少必要的参数");
        }

        String productDesc = param.get("productDesc");
        String productName = param.get("productName");
        if (StringUtils.isAllBlank(productDesc, productName)) {
            return new RetCode(401, "缺少必要的参数");
        }

        RetCode retCode;
        try {
            Map<String, String> map = new HashMap<>();
            PageHelper.startPage(page, pageSize);
            List<Product> data = productDao.searchProduct(param);
            retCode = RetCode.success(data);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
        return retCode;
    }

    @Override
    public RetCode updateStatus(Map<String, Object> param) {
        String productId = (String) param.get("productId");
        Boolean status = (Boolean) param.get("status");
        if (null == productId || null == status) {
            System.out.println(productId + ":" + status);
            return new RetCode(402, "参数错误");
        }

        int result = productDao.updateStatus(param);
        if (result < 0) {
            return new RetCode(-1, "服务器错误");
        }

        return RetCode.success();
    }
}