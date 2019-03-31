package cn.grace.web.filter;

import cn.grace.pojo.Funs;
import cn.grace.pojo.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 权限过滤器
 */
public class SafeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String uri=request.getRequestURI();
        //对静态资源做放行处理
        if(uri.endsWith(".js")||uri.endsWith(".css")||uri.endsWith(".gif")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //对用户登录资源做放行
            if (uri.indexOf("login")!=-1||uri.indexOf("userLogin")!=-1){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                HttpSession session=request.getSession();
                Users user= (Users) session.getAttribute("user");
                List<Funs> funs=user.getFuns();
                //开关
                boolean flag=false;
                for(Funs f:funs){
                    //判断当前访问的uri是否在功能数据中包含
                    if (uri.indexOf(f.getFunurl())!=-1){
                        flag=true;
                        break;
                    }
                }
                //根据开关的值进行跳转
                if (flag){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    response.sendRedirect("roleerror");
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
