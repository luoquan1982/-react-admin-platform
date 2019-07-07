package com.luoquan.reactserver.service;

import com.luoquan.reactserver.util.RetCode;

import java.util.Map;

/**
 * ProductService
 *
 * @author LuoQuan
 * @date 2019/7/6 9:59
 */
public interface ProductService {
    RetCode listProduct(int page, int pageSize) throws Exception;

    RetCode searchProduct(int page, int pageSize, Map<String, String> param) throws Exception;

    RetCode updateStatus(Map<String,Object> param);
}
