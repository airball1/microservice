package com.imooc.course.filter;

import com.imooc.user.client.LoginFilter;
import com.imooc.usermodel.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liuzike
 * @Date 1/9/21
 **/
public class CourseFilter extends LoginFilter {
    @Override
    protected void login(HttpServletRequest request, HttpServletRequest request1, UserInfo userInfo) {

        request.setAttribute("user", userInfo);
    }
}
