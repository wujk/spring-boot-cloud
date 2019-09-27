package com.wujk.springbootrestdocs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class RestdocsController {

    @RequestMapping("/hello")
    public Map<String, String> hello() {
        return Collections.singletonMap("hello", "wujk");
    }

    @RequestMapping("/restdocs")
    public String restdocs() {
        return "Hello Restdocs";
    }
}
