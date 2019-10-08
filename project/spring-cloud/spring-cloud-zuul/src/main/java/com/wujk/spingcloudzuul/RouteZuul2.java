package com.wujk.spingcloudzuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

public class RouteZuul2 extends ZuulFilter {
    @Override
    public String filterType() {
        String filterType = FilterConstants.ROUTE_TYPE;
        System.out.println("RouteZuul2 filterType: " + filterType);
        return filterType;
    }

    @Override
    public int filterOrder() {
        int filterOrder = 1;
        System.out.println("RouteZuul2 filterOrder: " + filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        boolean shouldFilter = ctx.sendZuulResponse();
        System.out.println("RouteZuul2 shouldFilter: " + shouldFilter );
        return shouldFilter;
    }

    @Override
    public Object run() {
        System.out.println("RouteZuul2 run: ");
        return null;
    }
}
