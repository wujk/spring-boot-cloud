package com.wujk.spingcloudzuul;

import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String error() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getResponseBody();
    }
}
