package jsj.dao;

import jsj.model.Classes;
import jsj.model.Course;
import jsj.model.Exam;
import jsj.model.User;
import jsj.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    public static User query(User user) {
        User queryUser = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            String sql ="select id, nickname from user where username=? and password=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                queryUser = user;
                queryUser.setId(rs.getInt("id"));
                queryUser.setNickname(rs.getString("nickname"));
            }
        } catch (Exception e) {
            throw new RuntimeException("用户登录操作出错", e);
        }
        return queryUser;
    }
}
