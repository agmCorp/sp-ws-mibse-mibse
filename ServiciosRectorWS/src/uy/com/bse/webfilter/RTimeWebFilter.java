package uy.com.bse.webfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import uy.ibm.responseTimeLogger.RTimeLogger;

public class RTimeWebFilter implements Filter {
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException
	  {
		System.out.println("Filter dofilter");
	    HttpServletRequest httpRequest=(HttpServletRequest)request;
	    System.out.println("URI: " + httpRequest.getRequestURI());
	    //System.out.println("URL: " + httpRequest.getRequestURL());
		String transactionType = httpRequest.getRequestURI();
		String transactionId = transactionType+".a("+System.currentTimeMillis()+").b("+Thread.currentThread().getId()+")";
		RTimeLogger.initContext(transactionId, transactionType, true);

		RTimeLogger.registerStart(transactionType);
		chain.doFilter(request,response);
		RTimeLogger.registerStop(transactionType);
		System.out.println("Filter dofilter fin");
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
