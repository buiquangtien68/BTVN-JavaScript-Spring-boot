package com.example.demo.config;

import com.example.demo.entities.User;
import com.example.demo.model.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("AuthenticationInterceptor preHandle");
        //lấy user trong session
        User user = (User) request.getSession().getAttribute("user");

        System.out.println(user);
        //Nếu user == null thì báo lỗi 401
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //Kểm tra quyền của user
        if (!user.getRole().equals(UserRole.ADMIN)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        return true;
    }
}
