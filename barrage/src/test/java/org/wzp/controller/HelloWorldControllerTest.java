package org.wzp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldControllerTest {
	/**
	 * 不能写 @RequestMapping("/error")，会报错，错误页面应该用另外的方法写
	 * @return
	 */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    
}