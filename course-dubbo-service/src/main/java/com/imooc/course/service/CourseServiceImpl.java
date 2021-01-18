package com.imooc.course.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.imooc.course.dto.CourseDTO;
import com.imooc.course.service.com.imooc.course.mapper.CourseMapper;
import com.imooc.usermodel.TeacherDTO;
import com.imooc.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author liuzike
 * @Date 1/8/21
 **/
@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private CourseMapper courseMapper;

//    @Reference(url = "dubbo://localhost:7911", check = false)
    @Autowired
    private UserService userService;

    public List<CourseDTO> courseList() {

        List<CourseDTO> courseDTOS = courseMapper.listCourse();
        if (courseDTOS != null) {
            for (CourseDTO courseDTO : courseDTOS) {
                Integer teacherId = courseMapper.getCourseTeacher(courseDTO.getId());
                if (teacherId != null) {
                    TeacherDTO teacherDTO = userService.getTeacherById(teacherId);
                    courseDTO.setTeacher(teacherDTO);
                }
            }
        }
        return courseDTOS;
    }
}
