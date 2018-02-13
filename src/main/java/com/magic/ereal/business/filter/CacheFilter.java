package com.magic.ereal.business.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CacheFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest)request;  
//		String requestURI = httpRequest.getRequestURI();
//		System.out.println(requestURI);

		HttpServletResponse httpResp = (HttpServletResponse)response;
		httpResp.addHeader("Cache-Control", "max-age=0, private, must-revalidate");
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}


