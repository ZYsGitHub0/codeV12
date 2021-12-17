package com.happy233.web.fitler;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "TestFilter", urlPatterns = "/*")
public class TestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        log.info("测试过滤器");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        log.info("Filter 初始化完成");
    }

}
