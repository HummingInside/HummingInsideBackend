package com.backend.api.session;

import com.backend.application.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");

        if(ObjectUtils.isEmpty(loginUser)){
            response.getOutputStream().println("로그인 필요");
            //response.sendRedirect("/login");

            return false;
        }else{
            session.setMaxInactiveInterval(1800);

            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
