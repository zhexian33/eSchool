package top.zhexian.gateway.filter;


import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//@Component
//@Order(-1)
//@Slf4j
public class AuthorizeFilter implements GlobalFilter {

    static String[] paths = {
            "/user/save",
            "/user/login",
            "/common/verify",
            "/common/inVerify",
            "/user/repeat"
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 检查路径
        String path = String.valueOf(exchange.getRequest().getPath());
        if (!check(path) && !StpUtil.isLogin()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    private boolean check(String path) {
        if (path == null || path.equals("")) {
            return false;
        }
        for (String s : paths) {
            if (path.contains(s)) {
                return true;
            }
        }

        return false;
    }
}