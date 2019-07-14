package com.luoquan.reactserver.controller;

import com.luoquan.reactserver.service.ProductService;
import com.luoquan.reactserver.util.RetCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ProductController
 *
 * @author LuoQuan
 * @date 2019/7/6 10:18
 */
@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/manage/product/list")
    public RetCode getProductByPage(String page, String pageSize) throws Exception {
        int iPage = Integer.valueOf(page);
        int iPageSize = Integer.valueOf(pageSize);

        return productService.listProduct(iPage, iPageSize);
    }

    @GetMapping("/manage/product/search")
    public RetCode searchProductByPage(String page, String pageSize,
                                       @RequestParam(value = "productName", required = false) String productName,
                                       @RequestParam(value = "productDesc", required = false) String productDesc) throws Exception {
        int iPage = Integer.valueOf(page);
        int iPageSize = Integer.valueOf(pageSize);
        Map<String, String> param = new HashMap<>();
        if (StringUtils.isBlank(productName)) {
            param.put("productDesc", productDesc);
        } else {
            param.put("productName", productName);
        }
        return productService.searchProduct(iPage, iPageSize, param);
    }

    @PostMapping("/manage/product/updateStatus")
    public RetCode updateProductStatus(@RequestBody Map<String, Object> param) {
        return productService.updateStatus(param);
    }

    @PostMapping("/manage/product/add")
    public RetCode addProduct() {

        return null;
    }

}
