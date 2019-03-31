package cn.grace.web.filter;

import cn.grace.pojo.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 判断用户是否登录
 */
public class UserLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取用户访问的uri
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        String uri=request.getRequestURI();
        //判断当前访问的Uri是否是用户登录资源，如果是则放行
        if (uri.indexOf("login")!=-1||uri.indexOf("userLogin")!=-1){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //用户是否登录的判断
            HttpSession session=request.getSession();
            Users user= (Users) session.getAttribute("user");
            if(user!=null&&user.getUsername().length()>0){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                request.setAttribute("msg","请登录");
                request.getRequestDispatcher("/login").forward(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
