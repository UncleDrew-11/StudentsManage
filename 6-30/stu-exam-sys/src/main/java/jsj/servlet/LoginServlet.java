package jsj.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jsj.dao.UserDao;
import jsj.model.Response;
import jsj.model.User;
import jsj.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/login")
public class LoginServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //解析http请求的用户名密码
        User user = JSONUtil.read(req.getInputStream(), User.class);
        //根据用户名密码查询用户
        User queryUser = UserDao.query(user);
        if (queryUser == null) {
            throw new RuntimeException("用户名密码校验失败");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", queryUser);
        return null;
    }

}
