package com.jiang.controller;

import com.jiang.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Slf4j
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping("/save/product")
    public String saveProduct(Product product) {

        logger.info("ProductHandler.saveProduct的请求参数{}", product);

        return "target";
    }

}
