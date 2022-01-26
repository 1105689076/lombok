package com.jiang.controller;



import com.jiang.pojo.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ParamController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/emp/save")
    public String formToEntity(
            // SpringMVC 会自动调用实体类中的 setXxx() 注入请求参数
            Employee employee) {
        logger.info("ParamHandler.formToEntity的请求参数是：{}", employee);
        return "target";
    }

    @GetMapping("/param/one/name/one/value")
    public String oneNameOneValue(
            // 使用 defaultValue 属性给请求参数设置默认值：
            @RequestParam(name = "userName", defaultValue = "没有名字？") String userName) {
        System.out.println("ParamHandler.oneNameOneValue的请求参数是：{}"+userName);
        return "target";
    }

    @PostMapping("/param/one/name/multi/value")
    public String oneNameMultiValue(@RequestParam(name = "team")List<String> team ){
        System.out.println("ParamHandler.oneNameMultiValue的请求参数是：{}"+team);
        return "targe";
    }

}
