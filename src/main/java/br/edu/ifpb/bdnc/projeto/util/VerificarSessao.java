
package br.edu.ifpb.bdnc.projeto.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edilva
 */
public class VerificarSessao implements Filter{
    
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
        HttpServletRequest httpRequest = (HttpServletRequest) request;
     
        HttpSession sessao = httpRequest.getSession();
        
        if(sessao.getAttribute("usuario") != null){
            chain.doFilter(request, response);
        }else {
            dispatch(request, response, "/login.html");
        }
    }
    
    private void dispatch(ServletRequest request, ServletResponse response, String resource) throws ServletException, IOException{
        ServletContext context = config.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(resource);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        config = null;
    }
    
}
