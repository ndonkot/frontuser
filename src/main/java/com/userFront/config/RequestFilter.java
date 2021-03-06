package com.userFront.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
	    HttpServletRequest request = (HttpServletRequest) req;

	        response.setHeader("Access-Control-Allow-Origin", "https://administratorportal.herokuapp.com");
	        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	        
	        
	        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
	            try {
	                chain.doFilter(req, res);
	            } catch(Exception e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Pre-flight");
	            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
	            response.setHeader("Access-Control-Max-Age", "3600");
	            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type," +
	                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
	            response.setStatus(HttpServletResponse.SC_OK);
	        }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
