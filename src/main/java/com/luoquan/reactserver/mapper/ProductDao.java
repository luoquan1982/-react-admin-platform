package com.luoquan.reactserver.mapper;

import com.luoquan.reactserver.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ProductDao
 *
 * @author LuoQuan
 * @date 2019/7/6 9:53
 */
@Mapper
@Repository
public interface ProductDao {
    List<Product> listProduct();

    List<Product> searchProduct(Map<String, String> map);

    int updateStatus(Map<String,Object> map);
}
