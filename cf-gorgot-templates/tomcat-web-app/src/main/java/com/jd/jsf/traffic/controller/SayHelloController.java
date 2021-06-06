package com.jd.jsf.traffic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/17
 * @since todo
 */
@RestController
@RequestMapping("/sayhello")
public class SayHelloController {

    private static final Logger logger = LoggerFactory.getLogger(SayHelloController.class);

    @PostConstruct
    private void init() {
        System.out.println("");
    }

    @RequestMapping(value = "/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    public String sayHello(@PathVariable String name) {
        logger.info("name: {}", name);
        return "hello" + name;
    }
}
