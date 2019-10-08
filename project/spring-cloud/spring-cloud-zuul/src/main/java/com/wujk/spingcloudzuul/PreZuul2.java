package com.wujk.spingcloudzuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

public class PreZuul2 extends ZuulFilter {

    @Override
    public String filterType() {
        String pre = FilterConstants.PRE_TYPE;
        System.out.println("PreZuul2 filterType: " + pre);
        return pre;
    }

    @Override
    public int filterOrder() {
        int filterOrder = 1;
        System.out.println("PreZuul2 filterOrder: " + filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        boolean shouldFilter = ctx.sendZuulResponse();
        System.out.println("PreZuul2 shouldFilter: " + shouldFilter );
        return shouldFilter;
    }

    @Override
    public Object run() {
        System.out.println("PreZuul2 run: ");
        /*try {
            if (true)
                throw new RuntimeException("adadasd");
        } catch (Exception e) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
        }*/
        if (true)
            throw new RuntimeException("adadasd");


        return null;
    }
}
