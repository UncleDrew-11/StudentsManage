package jsj.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//过滤器
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //判断是否是敏感资源，如果是，获取session，获取不到，重定向到登录页面
        String uri = req.getServletPath();
        if (!uri.startsWith("/public/") && !uri.startsWith("/static") && !"/user/login".equals(uri)) {
            //获取session重定向
            HttpSession session = req.getSession(false);
            if (session == null) {
                //没有登陆不允许访问
                String schema = req.getScheme();//http
                String host = req.getServerName();//服务器ip
                int port = req.getServerPort();//端口号
                String contextPath = req.getContextPath();//获取项目部署名
                String basePath = schema+"://"+host+":"+port+contextPath;
                res.sendRedirect(basePath + "/public/index.html");
                return;
            }
        }
        chain.doFilter(request, response);//非敏感资源，或敏感资源登陆以后，继续调用后续的过滤器

    }

    @Override
    public void destroy() {

    }
}
