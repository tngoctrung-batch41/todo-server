//package com.exercise.todo.config;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//@Configuration
//public class CORSFilter implements Filter {
//    public void destroy() {
//
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        // Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
//        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
//            HttpServletRequest request = (HttpServletRequest) req;
//            HttpServletResponse response = (HttpServletResponse) res;
//
//            // Access-Control-Allow-Origin
//            String origin = request.getHeader("Origin");
//            response.setHeader("Access-Control-Allow-Origin", StringUtils.hasText(origin) ? origin : "*");
//            response.setHeader("Vary", "Origin");
//
//            // Access-Control-Max-Age - that is request time out is 3.6s
//            response.setHeader("Access-Control-Max-Age", "3600");
//
//            // Access-Control-Allow-Credentials
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//
//            // Access-Control-Allow-Methods
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
//
//            // Access-Control-Allow-Headers
//            response.setHeader("Access-Control-Allow-Headers",
//                    "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
//
//            System.out.println(new Date() + " --- " + ((HttpServletRequest) req).getMethod() + ":" + ((HttpServletRequest) req).getRequestURI());
//        }
//
//        chain.doFilter(req, res);
//    }
//
//    public void init(FilterConfig filterConfig) {
//    }
//}
