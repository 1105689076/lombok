package com.jiang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/experiment")
@Controller
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 使用 @ResponseBody 告诉 SpringMVC，将当前方法的返回值作为响应体，不要再去寻找视图。
    // 方法返回值的类型：
    // 情况①：简单类型，SpringMVC 会直接作为响应体数据。
    // 情况②：复杂类型，SpringMVC 会将其转换为 JSON ，然后再作为响应体数据。需要 jackson 的支持。
    @PostMapping("/one")
    @ResponseBody
    public String one(
            // Ajax发过来的请求参数，对服务器端来说没有区别，还是和以前一样正常接收
            @RequestParam("userName") String userName,
            @RequestParam("password") String password) {

        logger.info("DemoHandler.one的请求参数是{}和{}", userName, password);

        // 服务器端给Ajax程序的响应数据通过handler方法的返回值提供
        return "实验一";
    }

    @PostMapping("/two")
    @ResponseBody
    public String two() {

        // 服务器端给Ajax程序的响应数据通过handler方法的返回值提供
        return "实验二";
    }

    @RequestMapping("/ajax")
    public String ajax(){
        //return "forward:/templates/ajax1.html";
        return "ajax1";
    }
}
