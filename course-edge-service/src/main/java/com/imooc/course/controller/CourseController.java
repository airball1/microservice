package com.imooc.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.imooc.course.dto.CourseDTO;
import com.imooc.course.service.ICourseService;
import com.imooc.usermodel.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuzike
 * @Date 1/9/21
 **/
@RequestMapping("/course")
@Controller
public class CourseController {

    @Reference
    private ICourseService courseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseList(HttpServletRequest request) {

        UserInfo userInfo = (UserInfo) request.getAttribute("user");
        System.out.println("134234324" + userInfo.toString());

        return courseService.courseList();
    }
}
