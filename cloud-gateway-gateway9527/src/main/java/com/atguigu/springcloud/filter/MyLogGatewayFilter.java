package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetEncoder;
import java.util.Date;

/**
 * @author Mr. Hao
 * @date 2021-07-28   14:59
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("**********come in MyLogGatewayFilter ******:   " + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null) {
            log.info("************用户名为空，非法用户！！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            String ss = "用户名为空，非法用户";
            String s = StringEscapeUtils.escapeHtml(ss);
            System.out.println(s);
            try {
                exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE);
                String gb2312 = new String(s.getBytes("gb2312"), "ISO8859-1");
                System.out.println(gb2312);
                exchange.getResponse().getHeaders().add("message", gb2312);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //过滤器执行的优先级，数值越小优先级越高
        return 0;
    }
}
