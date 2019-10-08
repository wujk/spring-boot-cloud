package com.wujk.spingcloudzuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

public class ErrorZuul2 extends ZuulFilter {
    @Override
    public String filterType() {
        String filterType = FilterConstants.ERROR_TYPE;
        System.out.println("ErrorZuul2 filterType: " + filterType);
        return filterType;
    }

    @Override
    public int filterOrder() {
        int filterOrder = -1;
        System.out.println("ErrorZuul2 filterOrder: " + filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        boolean shouldFilter = ctx.sendZuulResponse();
        System.out.println("ErrorZuul2 shouldFilter: " + shouldFilter );
        return shouldFilter;
    }

    @Override
    public Object run() {
        System.out.println("ErrorZuul2 run: ");
        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println(ctx.keySet());
        ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
        ctx.setResponseStatusCode(401);// 返回错误码
        ctx.setResponseBody("{\"result\":\"username is not correct!!!!\"}");// 返回错误内容
        return null;
    }
}
