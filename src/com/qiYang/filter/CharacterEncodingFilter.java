package com.qiYang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String config;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req;
		req = (HttpServletRequest) request;
		HttpServletResponse res;
		res = (HttpServletResponse) response;
		req.setCharacterEncoding(config);
		res.setCharacterEncoding(config);
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config.getInitParameter("chars");

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
