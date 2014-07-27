package com.journaldev.servlet.filters;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
 
    private ServletContext context;
     
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }
    
    public static String getPostData(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            reader.mark(10000);

            String line;
            do {
                line = reader.readLine();
                if(line!=null)
                	sb.append(line).append("\n");
            } while (line != null);
            reader.reset();
            // do NOT close the reader here, or you won't be able to get the post data twice
        } catch(IOException e) {
            System.out.println("getPostData couldn't.. get the post data");  // This has happened if the request's reader is closed
            e.printStackTrace();
        }

        return sb.toString();
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    	// Log Request POST Payload
    	//String postData= getPostData(new HttpServletRequestWrapper(httpServletRequest));;
        //System.out.println("Recibido request: sysout [" + postData + "]");

        chain.doFilter(request, response);
    }
 
    public void destroy() {
        //we can close resources here
    }
 
}